<%@page import="com.gerarpowershell.enumerable.DiretorioSistemasEnum"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gerador de PowerShell</title>
	
	<link rel="stylesheet" href="shared/css/main.css"/>
	<link rel="stylesheet" href="shared/bootstrap/css/bootstrap.min.css"/>
	<script src="shared/js/main.js"></script>
</head>
<body>
	<div id="main">
		<form action="gerador" method="POST" onsubmit="return validaForm(this)" enctype='multipart/form-data'>
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
					<input type="submit" id="btnGerar" value="Gerar PowerShell"/>
				</div>
				<ul id="output"></ul>
			</div>
		</form>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="shared/bootstrap/js/bootstrap.min.js"></script>
</html>