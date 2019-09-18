window.baseUrl = location.origin + '/mock-services';
window.baseUrlResources = baseUrl + '/resources';
window.baseUrlVueComponents = baseUrlResources + '/js/vuecomponents';
window.createServiceUrl = baseUrl + '/rest/create';
window.restServiceGenerateBaseUrl = baseUrl + '/rest/generate/';
window.restServiceDeleteUrl = baseUrl + '/rest/delete';

function getScript(target){
	let script = document.createElement('script');
	script.src = target;
	script.type = 'module';
	script.async = false;
	document.getElementsByTagName('head')[0].appendChild(script);
}

function loadConstants(){
	let target = baseUrl + '/resources/properties/constants.js';
	getScript(target);
	target = baseUrl + '/resources/js/head/properties.js';
	getScript(target);
	target = baseUrl + '/resources/js/vuecomponents/mockServices.js'
	getScript(target);
}
document.onload = loadConstants();