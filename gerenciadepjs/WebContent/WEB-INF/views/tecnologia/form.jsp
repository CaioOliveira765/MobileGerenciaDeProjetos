<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:url value="../../assets/vendor" var="vendor" />
<c:url value="../../assets/js" var="js" />
<c:url value="../../assets/imagens" var="img" />

<c:url value="/app/tecnologia/salvar" var="urlSalvar" />

<head>
    <!-- Title Page-->
    <title>${tecnologia.id == null ? 'Cadastro' : 'Edição'} de Tecnologia ${tecnologia.nome}</title>
    <c:import url="../templates/head2.jsp"/>

</head>

<c:import url="../templates/header2.jsp"/>

            <!-- MAIN CONTENT-->
            <div class="main-content">
                <div class="section__content section__content--p30">
                    <div class="container-fluid">                    
                    <button>
                    	<a href="/gerenciadepjs/app/tecnologia">
                         	<img src="${img}/voltar.png" title="Voltar" style="color:#005fa3; margin-top:-20px; width: 45px;" alt="voltar para a página anterior" >
						</a>                     
                     </button>                    
                        <div class="row d-flex justify-content-center">
                            <div class="col-lg-12">
                                <div class="card card-tecnologias">
                                    <div class="card-header card-header-tecnologias">
                                        <h2 class="title-1" style="font-size: 20px; color: #fff;">Cadastro de
                                            Tecnologias</h2>
                                    </div>
                                    <div class="card-body card-block">
                                        <form:form modelAttribute="tecnologia" action="${urlSalvar}" method="post" class="form-horizontal" id="formulario">
											<form:hidden path="id" />
                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="text-input" class="form-control-label">Nome<b class="obrigatorio">*</b></label>
                                                </div>
                                                <div class="col-12 col-md-9">
                                                    <form:input path="nome" type="text" id="text-input" name="text-input" placeholder="Nome"
                                                        class="form-control" htmlEscape="true"/>
                                                        
                                                        <span class="obrigatorio"><form:errors path="nome"/></span>

                                                </div>
                                            </div>

                                            <div class="row form-group">
                                                <div class="col col-md-3">
                                                    <label for="textarea-input" class=" form-control-label">Descrição</label>
                                                </div>
                                                <div class="col-12 col-md-9">
                                                    <form:textarea path="descricao" name="textarea-input" id="textarea-input" rows="9"
                                                        placeholder="Descrição..." class="form-control"/>
                                                        <span class="obrigatorio"><form:errors path="descricao"/></span>
                                                </div>
                                            </div>
                                       
                                    </div>
                                    <div class="card-footer">
                                    
                                    <button type="reset" class="btn btn-danger btn-sm" onclick="limparFormulario()">
                                            <i class="fa fa-ban"></i> ${tecnologia.id != null ? 'Reiniciar' : 'Limpar'}
                                        </button>
                                    
                                       <button type="submit" class="btn btn-secundary btn-tecnologias btn-sm" data-toggle="modal"
                                        data-target="#staticModal">
                                            <i class="fa fa-dot-circle-o"></i> Salvar           
                                        </button>
          
                                    </div>  
                                    </form:form>                                 
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
   <!-- modal static -->
			<div class="modal fade" id="staticModal" tabindex="-1" role="dialog" aria-labelledby="staticModalLabel" aria-hidden="true"
            data-backdrop="static">
               <div class="modal-dialog modal-sm" role="document">
                   <div class="modal-content">
                       <div class="modal-header">
                          
                           <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                               <span aria-hidden="true">&times;</span>
                           </button>
                       </div>
                       <div class="modal-body">
                           <p>
                              Sua tecnologia foi ${tecnologia.id == null ? 'cadastrada' : 'editada'} com sucesso!
                           </p>
                       </div>
                       <div class="modal-footer">
                           
                           <button type="button" class="btn btn-primary cad" style=" color: #fff; background-color:   #005fa3;">Fechar</button>
                       </div>
                   </div>
               </div>
           </div>
           <!-- end modal static -->

    <!-- Jquery JS-->
    <script src="${vendor}/jquery-3.2.1.min.js"></script>
    <!-- Bootstrap JS-->
    <script src="${vendor}/bootstrap-4.1/popper.min.js"></script>
    <script src="${vendor}/bootstrap-4.1/bootstrap.min.js"></script>
    <!-- Vendor JS       -->
    <script src="${vendor}/slick/slick.min.js">

    </script>
    <script src="${vendor}/wow/wow.min.js"></script>
    <script src="${vendor}/animsition/animsition.min.js"></script>
    <script src="${vendor}/bootstrap-progressbar/bootstrap-progressbar.min.js">
    
    </script>
    <script src="${vendor}/counter-up/jquery.waypoints.min.js"></script>
    <script src="${vendor}/counter-up/jquery.counterup.min.js">

    </script>
    <script src="${vendor}/circle-progress/circle-progress.min.js"></script>
    <script src="${vendor}/perfect-scrollbar/perfect-scrollbar.js"></script>
    <script src="${vendor}/chartjs/Chart.bundle.min.js"></script>
    <script src="${vendor}/select2/select2.min.js">

    </script>

    <!-- Main JS-->
    <script src="${js}/main.js"></script>

</body>

</html>
<!-- end document-->