<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Gerador de PowerShell</title>

	<link rel="stylesheet" href="shared/bootstrap/css/bootstrap.min.css"/>
	<link rel="stylesheet" href="shared/css/main.css"/>
</head>
<body>
	<div id="main">
		<div class="panel panel-success">
		  <div class="panel-heading"><span class="glyphicon glyphicon-ok" aria-hidden="true"></span> ${requestScope["message"]}</div>
		  <div class="panel-body">
		    O arquivo .zip com o pacote você encontra nesse caminho:<b> ${requestScope["outputZipFile"]} </b>
		  </div>
		</div>
		<div id="voltar">
			<a href="/GerarPowerShell/index.jsp">VOLTAR</a>
		</div>
	</div>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
<script src="shared/bootstrap/js/bootstrap.min.js"></script>
</html>