window.listApis = Vue.component('listApis', {
	data: function() {
        return {
           apisList: [],
           filterApi: '',
           createRequestSupport: {
        	   methods: ['GET','POST'],
               contentTypes: {
            	   JSON: 'application/json',
            	   XML: 'application/xml'
               },
               advancedOptions: false,
               responseCodes: [200, 404, 415, 500, 503]
           },
           apiRequest: {
        	   name: "Example API",
        	   path: "",
        	   body:"",
        	   headers : {
        		   contentType:"",
        		   method: "GET"
        	   },
        	   advancedOptions: {
        		   timeDelay:0,
        		   responseCode:"200"
        	   }
           }
        }
    },
    created: function() {
    	let allServices = window.baseUrl + '/getAllServices';
		axios.get(allServices).then((response)=>{
			this.apisList = response.data;
		}).catch((error)=>{
			console.error('error')
		})
    },
    computed: {
    	filteredApis : function(){
    		return this.apisList.filter((element) => {
    			return element.name.toUpperCase().match(this.filterApi.toUpperCase());
    		});
    	},
        serviceUrl : function(){
        	return restServiceGenerateBaseUrl + this.apiRequest.path;
        },
    },
    methods : {
    	createService : function(){
    		if( ! this.apiRequest.path ){
    			alert("Please add some Path.")
    			return;
    		}else if( this.apiRequest.path[0] == '/' ){
    			this.apiRequest.path = this.apiRequest.path.substring(1);
    		}
    		let flag = false;
    		let repeatedIndex = -1;
    		this.apisList.forEach((service, index)=>{
    			if(service.path == this.apiRequest.path && service.headers.method == this.apiRequest.headers.method){
    				if(!confirm('This service exists already with name '+service.name+'. Would you like to modify?')){
    					flag = true;
    				}else{
    					repeatedIndex = index;
    				}
    				return;
    			}
    		});
    		if(flag){
    			return;
    		}
    		if( ! this.apiRequest.name ){
    			this.apiRequest.name = 'Example API';
    		}
    		this.apiRequest.url = this.serviceUrl;
    		if(repeatedIndex >= 0){
    			this.apisList.splice(repeatedIndex,1);
    		}
    		let serviceClone = {};
     		for(let prop in this.apiRequest){
      			serviceClone[prop] = this.apiRequest[prop];
      		}
     		this.apisList.push(serviceClone);
       		axios.post(window.createServiceUrl, this.apiRequest).then((response)=>{
       			console.log(response);
       		}).catch((error)=>{
       			console.log(error);
       		})
    	},
    	formatRequestBody: function(){
    		if(this.apiRequest.body){
    			if(this.apiRequest.headers.contentType == this.createRequestSupport.contentTypes.JSON){
    				try{
    					let json = JSON.parse(this.apiRequest.body);
    					this.apiRequest.body = JSON.stringify(json, null, 4);
    				}catch(error){
    					alert('JSON not Valid');
    				}
    			}else if(this.apiRequest.headers.contentType == this.createRequestSupport.contentTypes.JSON){
    				
    			}
    		}
    		
    	},
    	renderApi: function(api){
    		for(let prop in api){
    			this.apiRequest[prop] = api[prop];
    		}
    	},
    	deleteApi: function(api){
    		console.log("delete function called");
    		this.apisList.splice(this.apisList.indexOf(api), 1);
    		axios({
    			url : window.restServiceDeleteUrl,
    			method : "DELETE",
    			data : api
    		}).then(response=>{
    			console.log(response);
    		}).catch(error=>{
    			console.log(error);
    		})
    	}
    },
	template : `
		<div class="row">
			<div name="listApis" class="col-3">
				<div class='heading'>All Services</div>
				<input class='col-12 clear' placeholder='filter' v-model='filterApi'>
				<div class='clear'>
					<span class='row servicesList' v-for="(api, index) in filteredApis">
						<span class='col-3' style="color:#c40957;">{{ api.headers.method }}</span>
						<span class='col-7'><a href="javascript:void(0);" @click="renderApi(api)">{{ api.name }}</a></span>
						<span class="col-2 child-servicesList fa fa-trash" @click="deleteApi(api)"></span>
					</span>
				</div>
			</div>
			<form name="createEdit" class="col-9">
				<div class='row clear'>
					<label for='serviceName' class='col-md-2 col-sm-3'>Service Name</label>
					<input class='col-md-3 col-sm-3' v-model='apiRequest.name'>
					<input class='col-md-7 col-sm-6' readonly='true' :value = 'serviceUrl'>
				</div>
				<div class='row clear'>
					<label for='path' class='col-md-2 col-sm-3'>Path</label>
					<input class='col-md-6 col-sm-5' v-model='apiRequest.path'>
				</div>
				<div class='row clear'>
					<label for='headers' class='col-md-2 col-sm-3'>Headers</label>
					<span class='row col-md-10 col-sm-9' style="padding:0px;">
						<div class='col-6'>
							<label>Method</label>
							<select name='Method' v-model='apiRequest.headers.method'>
								<option v-for='method in createRequestSupport.methods'>{{ method }}</option>
							</select>
						</div>
						<div class='col-6'>
							<label>Content-Type</label>
							<select name='Content-Type' v-model='apiRequest.headers.contentType'>
								<option v-for="(value, key) in createRequestSupport.contentTypes" :value='value'>
									{{ key }}
								</option>
							</select>
						</div>
					</span>
				</div>
				<div class = 'row clear col-12'>
					<a href="javascript:void(0);" @click='createRequestSupport.advancedOptions=!createRequestSupport.advancedOptions'>>Advance Options</a>
				</div>
				<div class='row clear' v-if='createRequestSupport.advancedOptions'>
					<label class='col-md-2 col-sm-3'></label>
					<span class='row col-md-10 col-sm-9' style="padding:0px;">
						<div class='col-6'>
							<label>Delay</label>
							<input v-model='apiRequest.advancedOptions.timeDelay' placeholder='Time delay in ms'>
						</div>
						<div class='col-6'>
							<label>Response Code</label>
							<select name='Response Code' v-model='apiRequest.advancedOptions.responseCode'>
								<option v-for='response in createRequestSupport.responseCodes'>{{ response }}</option>
							</select>
						</div>
					</span>
				</div>
				<textarea class='col-12 clear input-body' v-model='apiRequest.body'></textarea>
				<div class='row clear'>
					<button @click.prevent='createService'>Submit</button>
					<button v-if='this.apiRequest.headers.contentType==this.createRequestSupport.contentTypes.JSON' 
									@click.prevent='formatRequestBody'>Format JSON</button>
				</div>
			</form>
		</div>
	`
});
