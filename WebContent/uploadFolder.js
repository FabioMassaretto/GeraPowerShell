/**
 * 
 */

window.onload = function(){
	var folder = document.getElementById("folder");
	var output = document.getElementById("output");
	var selSistema = document.getElementById("selectSistema");
	var dirOutro = document.getElementById("diretoriosOutro");
	
	var dirSistema = document.getElementById("diretorioSistema");
	var dirPacote = document.getElementById("diretorioPacote");
	
	dirOutro.setAttribute("style", "visibility:hidden");

	selSistema.addEventListener("change", function(e){
		//debugger;
		let selectedSystem = e.target.value;
		selectedSystem = selectedSystem.split("/");
		
		if(selectedSystem[0] == "OUTRO"){
			dirOutro.setAttribute("style", "visibility:visible");
			dirSistema.value = "";
			dirPacote.value = "";
		}else{
			dirOutro.setAttribute("style", "visibility:hidden");			
			dirSistema.value = selectedSystem[0];
			dirPacote.value = selectedSystem[1];
		}
	}, false);
	
	folder.addEventListener("change", function(event){
		var files = event.target.files;
		
		for(var i = 0; i < files.length; i++){
			var item = document.createElement("li");
			item.innerHTML = files[i].webkitRelativePath;
			output.appendChild(item);
		}

	}, false);	
}