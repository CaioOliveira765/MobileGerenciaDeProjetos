<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:url value="/" var="raiz" />
<c:url value="/assets/imagens/" var="imagens" />
<c:url value="/assets/vendor/" var="vendor" />
<c:url value="/assets/css/" var="css" />
<c:url value="/assets/js/" var="js" />

<c:url value="/usuario/enviarsenha" var="enviarSenha" />

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="au theme template">
    <meta name="author" content="Hau Nguyen">
    <meta name="keywords" content="au theme template">
    <link rel="icon" href="${img}/favicon.png" type="image/png">
    <!-- Title Page-->
    <title>Recuperar senha</title>
    <!-- Fontfaces CSS-->
    <link href="${css}font-face.css" rel="stylesheet" media="all">
    <link href="${vendor}font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
    <link href="${vendor}font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
    <link href="${vendor}mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <!-- Bootstrap CSS-->
    <link href="${vendor}bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">
    <!-- Vendor CSS-->
    <link href="${vendor}animsition/animsition.min.css" rel="stylesheet" media="all">
    <link href="${vendor}bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
    <link href="${vendor}wow/animate.css" rel="stylesheet" media="all">
    <link href="${vendor}css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
    <link href="${vendor}slick/slick.css" rel="stylesheet" media="all">
    <link href="${vendor}select2/select2.min.css" rel="stylesheet" media="all">
    <link href="${vendor}perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">
    <!-- Main CSS-->
    <link href="${css}theme.css" rel="stylesheet" media="all">
</head>
<body>
    <div class="page-wrapper">
        <div class="page-content--bge5" style="background-color:#005fa3;">
            <div class="container">
                <div class="login-wrap">
                    <div class="login-content">
                        <div class="login-logo">
                            <a href="#">
                                <img src="${imagens}/logo-azul.png" style="width:300px;" alt="CoolAdmin">
                            </a>
                        </div>
                        <div class="login-form">
                            <form:form modelAttribute="usuario" action="${enviarSenha}" method="post">
                                <div class="form-group">
                                    <label>Email</label>
                                    <form:input path="email" class="au-input au-input--full" type="email" placeholder=""/>                                                                    
                                    
                                    <small>Digite o seu email para recuperar a sua senha</small>
                                </div>
                                
                                <c:set var="emailTemErro">
									<form:errors path="email"/>
								</c:set>
                                
                                <c:if test="${not empty emailTemErro}">
                                	<div class="alert alert-danger" role="alert" style="text-align:center; background-color: #f8d7da; color: #721c24; border-color: #f5c6cb">
                                   	 	<form:errors path="email"/>
                               		</div>
                                </c:if>
                                
                                <button class="au-btn au-btn--block au-btn--green m-b-20" style="background-color:#009cde; "
                                    type="submit">Enviar</button>                                
                                
                                <!--
                                <div class="alert alert-primary" role="alert" style="text-align:center; background-color: #dff0d8; color: #3c763d; border-color: #d6e9c6;">
                                    Seu e-mail foi enviado com sucesso!
                                </div>                               
                                 -->
                                                             
                            </form:form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Jquery JS-->
    <script src="${vendor}jquery-3.2.1.min.js"></script>
    <!-- Bootstrap JS-->
    <script src="${vendor}bootstrap-4.1/popper.min.js"></script>
    <script src="${vendor}bootstrap-4.1/bootstrap.min.js"></script>
    <!-- Vendor JS       -->
    <script src="${vendor}slick/slick.min.js"></script>
    <script src="${vendor}wow/wow.min.js"></script>
    <script src="${vendor}animsition/animsition.min.js"></script>
    <script src="${vendor}bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
    <script src="${vendor}counter-up/jquery.waypoints.min.js"></script>
    <script src="${vendor}counter-up/jquery.counterup.min.js"></script>
    <script src="${vendor}circle-progress/circle-progress.min.js"></script>
    <script src="${vendor}perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="${vendor}chartjs/Chart.bundle.min.js"></script>
    <script src="${vendor}select2/select2.min.js"></script>
    <!-- Main JS-->
    <script src="${js}main.js"></script>
</body>
</html>