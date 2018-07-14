<%@ page import="com.gerarpowershell.enumerable.DiretorioSistemasEnum" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gerador de PowerShell</title>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
	<link rel="stylesheet" href="shared/css/main.css"/>

<script type="text/javascript">
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
</script>
</head>
<body>
	<div id="main">
	<form action="GeraPowershellServlet" method="POST" onsubmit="return validaForm(this)" enctype='multipart/form-data'>
			<div class="form-group row">
				<label for="nomeSistema" class="col-sm-2 col-form-label">Nome do Sistema: </label> 
				<div class="col-sm-10">
					<input type="text" name="nomeSistema" id="nomeSistema" class="form-control" placeholder="Ex.: MCS" required/>
				</div>
				<label for="numeroChamado" class="col-sm-2 col-form-label">Número do Chamado: </label> 
				<div class="col-sm-10">	
					<input type="text" name="numeroChamado" id="numeroChamado" class="form-control" placeholder="Ex.: C0001234" required/>
				</div>
				<label for="numeroTask" class="col-sm-2 col-form-label">Número da Task: </label> 
				<div class="col-sm-10">	
					<input type="text" name="numeroTask" id="numeroTask" class="form-control" placeholder="Ex.: T0001234" required/>
				</div>
				<label for="sistema" class="col-sm-2 col-form-label">Diretório Inetpub do Sistema: </label>
				<div class="col-sm-10">
					<select name="selectSistema" id="selectSistema" class="form-control">
						<option>Selecione o Sistema</option>
						<%
							for(DiretorioSistemasEnum diretorioSistema : DiretorioSistemasEnum.values()){
						%>
								<option value="<% out.print(diretorioSistema.getDiretorio()); %>/<% out.print(diretorioSistema.getDirPacote()); %>">
									<% out.print(diretorioSistema.getSistema()); %> (<% out.print(diretorioSistema.getDiretorio()); %>)
								</option>
						<%
							}
						%>
					</select>
					<div id="diretoriosOutro">
						<input type="text" name="diretorioSistema" id="diretorioSistema" class="form-control" placeholder="Informe o caminho inetpub do sistema (Ex.: D:\inetpub\MCS\MapfreConnectSite)" required/>
						<input type="text" name="diretorioPacote" id="diretorioPacote" class="form-control" placeholder="Informe o caminho do pacote (Ex.: D:\temp)" required/>
					</div>
				</div>
				<label for="pastaArquivos" class="col-sm-2 col-form-label">Pasta do Pacote: </label> 
				<div class="col-sm-10">
					<input type="file" name="fileInput" id="fileInput" class="form-control" webkitdirectory multiple required/>
				</div>
				<div class="col-sm-10">
					<input type="submit" id="btnGerar" onclick="ValidaForm" value="Enviar"/>
				</div>
				<ul id="output"></ul>
			</div>
		</form>
	</div>
</body>
</html>