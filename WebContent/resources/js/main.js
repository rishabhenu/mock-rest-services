jQuery(document).ready(function() {
		renderPage();
	});

function renderPage(){
	let html = "<div>Context is ${context}</div>";
	jQuery("#app").append(html);
}