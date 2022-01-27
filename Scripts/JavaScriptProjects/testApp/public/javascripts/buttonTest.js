
//make sure document is ready to recieve input 
//

var name = "ryan";
$(document).ready(function(){
	console.log('function is ready');
	$("#startGameButton").click(function(){ 

		console.log(name === "ryan");
		console.log(name == "ryan");

	});


	if ( $( '#startGameButton' ).length > 0 ){
		console.log('element exists');
	}
});


