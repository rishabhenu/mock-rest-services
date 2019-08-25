new Vue({
			el : '#app',
			data : {
				name : "Rishabh Sharma",
				link : "${context}/get-all-services",
				url : ''
			},
			methods :{
				getUrl : function(){
					return location.host+'/'+this.link;
				}
			}
		});