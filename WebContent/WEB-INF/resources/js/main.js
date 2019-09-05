console.log(location.hostname);
console.log(location.host);
console.log(location.origin);
new Vue({
	el : '#app',
	data : {
		constants : {
			
		},
		showConstants : false
	},
	methods : {
		loadConstants(data){
			this.constants.baseUrl = location.origin + data.context;
			console.log("loadConstants called " + this.constants.baseUrl);
		}
	},
	computed : {
		allServicesLink : function(){
			return this.constants.baseUrl + '/getallservices';
		},
		isHomePage : function(){
			let baseUrl = location.origin+'/mock-services';
			return ( baseUrl == document.URL || baseUrl+'/' == document.URL)?true:false;
		}
	}
});