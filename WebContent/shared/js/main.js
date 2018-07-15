/**
 * 
 */
window.onload = function(){
	var nomeSistema = document.getElementById("nomeSistema");
	var numeroChamado = document.getElementById("numeroChamado");
	var numeroTask = document.getElementById("numeroTask");
	var selSistema = document.getElementById("selectSistema");
	var dirSistema = document.getElementById("diretorioSistema");
	var dirPacote = document.getElementById("diretorioPacote");
		
	var dirOutro = document.getElementById("diretoriosOutro");
	var fileInput = document.getElementById("fileInput");
	var output = document.getElementById("output");
	
	//validaForm(nomeSistema, numeroChamado, numeroTask, selSistema, dirSistema, dirPacote);
	
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
	
	fileInput.addEventListener("change", function(event){
		var files = event.target.files;
		
		for(var i = 0; i < files.length; i++){
			var item = document.createElement("li");
			item.innerHTML = files[i].webkitRelativePath;
			output.appendChild(item);
		}

	}, false);
}
function validaForm(formItem){
	//debugger;
	var nomeSistema = formItem["nomeSistema"].value;
	var numeroChamado = formItem["numeroChamado"].value;
	var numeroTask = formItem["numeroTask"].value;
	var selSistema = formItem["selectSistema"].value;
	var dirSistema = formItem["diretorioSistema"].value;
	var dirPacote = formItem["diretorioPacote"].value;
	
	if(nomeSistema != "" && numeroChamado != "" && numeroTask != "" && 
			(selSistema != "" && selSistema != "Selecione o Sistema")){
		if(selSistema != "OUTRO"){
			return true;
		}else{
			if(dirSistema != "" && dirPacote != ""){
				return true;
			}
		}		
	}else {
		alert("Prencher todos os campos!");
	}
	return false;
}
