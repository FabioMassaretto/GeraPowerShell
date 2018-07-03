<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="com.gerarpowershell.enumerable.DiretorioSistemas"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script type="text/javascript">
window.onload = function(){
	var folder = document.getElementById("folder");
	var output = document.getElementById("output");
	var selSistema = document.getElementById("selectSistema");
	var dirSistema = document.getElementById("diretorioSistema");
 	dirSistema.setAttribute("style", "display:none");

	selSistema.addEventListener("change", function(e){
		//debugger;
		if(e.target.value == "OUTRO"){
			dirSistema.setAttribute("style", "display:block");
			dirSistema.value = "";
		}else{
 			dirSistema.setAttribute("style", "display:none");
 			dirSistema.value = e.target.value;
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
</script>
</head>
<body>
	<label for="nomeProjeto">Nome do Projeto: </label> <input type="text" width="80px"/>
	<label for="numeroChamado">N�mero do Chamado: </label> <input type="text" width="80px"/>
	<label for="numeroTask">N�mero da Task: </label> <input type="text" width="80px"/>
	<label for="sistema">Sistema: </label>
	<select id="selectSistema">
		<option>Selecione o Sistema</option>
		<%	
			for(DiretorioSistemas diretorioSistema : DiretorioSistemas.values()){
		%>
				<option value="<% out.print(diretorioSistema.getDiretorio()); %>"><% out.print(diretorioSistema.getSistema()); %></option>
		<%
			}
		%>
	</select>
	<input type="text" id="diretorioSistema">
	<label for="pastaArquivos">Pasta dos Arquivos: </label> <input type="file" id="folder" width="80px" webkitdirectory multiple/>
	
	<ul id="output"></ul>
</body>
</html>