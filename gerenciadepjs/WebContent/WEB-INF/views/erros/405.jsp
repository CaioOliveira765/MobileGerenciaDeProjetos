<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta http-equiv="X-UA-Compatible" content="ie=edge" charset="UTF-8">

<style>
.TeladeErro {background-color: #005fa3;}
.page-wrap {min-height: 100vh;}
</style>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">	
<link href="https://fonts.googleapis.com/css?family=Domine" rel="stylesheet">

<title>Erro 401!</title>
</head>
<body class="TeladeErro">
	<div class="page-wrap d-flex flex-row align-items-center">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-12 text-center">
					<span class="display-1 d-block" style="color: white;">405</span>
					<div class="mb-4 lead" style="color: white">Método não permitido.</div>
					<a href="/gerenciadepjs/index" class="btn btn-link" style="color: white;">Tela de login</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>