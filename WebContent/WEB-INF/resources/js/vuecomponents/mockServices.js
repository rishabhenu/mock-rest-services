//getScript( baseUrlVueComponents + '/listApis.js');
import * as listApis from './listApis.js';

Vue.component('mockServices', {
	    data: function() {
	        return {
	            greet: window.constants.greet,
	            additionalMsg: window.constants.additionalMsg
	        }
	    },
	    computed: {
	    	
	    },
	    components : {
	    	listApis: window.listApis
	    },
	    template: `
			<div>
				<div class='clear'><h1 class='heading'> {{ greet }} </h1></div>
				<hr>
				<div class='clear'><h2 style="text-align: center;transition: all;color: #4545df;">{{ additionalMsg }}</h2></div>
				<list-apis></list-apis>
			</div>
		`
	});


new Vue({
    el: '#app',
    data: {
       
    }
});