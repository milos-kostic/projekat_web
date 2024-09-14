<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta http-equiv="x-ua-compatible" content="ie=edge">

    <title>Cubes school - Blog project</title>


  <!-- Font Awesome Icons -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/fontawesome-free/css/all.min.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/admin/dist/css/adminlte.min.css">
  <!-- Google Font: Source Sans Pro -->
  <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">
</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">

 			<jsp:include page="include-admin-header-profile.jsp"></jsp:include>
  

  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    
      
	  <jsp:include page="include-admin-menu.jsp"/>
	  
  <!-- Main Sidebar Container -->
<!--   <aside class="main-sidebar sidebar-dark-primary elevation-4"> -->
   
   
 
  </aside>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Post Detail</h1>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>


    <!-- Main content -->
  
      <!-- Default box -->
      <div class="card card-solid">
        <div class="card-body">
          <div class="row">
            <div class="col-12 col-sm-6">
              <h3 class="d-inline-block d-sm-none">
              		${post.title}
              </h3>
              <div class="col-12">
                <img src="${post.image}" class="post-image" alt="Post Image">
              </div>
            
            </div>
            <div class="col-12 col-sm-6">
              <h3 class="my-3">${post.title}</h3>
                <p>
					${post.category.name}
				</p>
              <hr>
              <h4>Post Tags</h4>
              <div class="btn-group btn-group-toggle" data-toggle="buttons">
              	
	              	<c:forEach var="tag" items="${post.tags}">
		                <label class="btn btn-default text-center active">
		                  <input type="radio" name="color_option" id="color_option1" autocomplete="off" checked="">	                  		
		                  		${tag.title}	                  		
		                  	<br>
		                  <i class="fas fa-circle fa-2x" style="color: ${tag.color}"></i>
		                </label>
	                </c:forEach>
                
              </div>
    
    			
            </div>
          </div>
          
          <div class="content" style="max-width: 70%;">
          	  <div class="col-12">
          			${post.content}
          	  </div>
          </div>
          
          <div class="row mt-4">
            <nav class="w-100">
              <div class="nav nav-tabs" id="post-tab" role="tablist">
               
                <a class="nav-item nav-link active" id="post-desc-tab" data-toggle="tab" href="#post-desc" role="tab" aria-controls="post-desc" aria-selected="true">Description</a>
               
                <a class="nav-item nav-link" id="post-comments-tab" data-toggle="tab" href="#post-comments" role="tab" aria-controls="post-comments" aria-selected="false">Comments</a>
                
              </div>
            </nav>
            <div class="tab-content p-3" id="nav-tabContent">
              <div class="tab-pane fade show active" id="post-desc" role="tabpanel" aria-labelledby="post-desc-tab">
              		${post.description}
			  </div>
			  
              <div class="tab-pane fade" id="post-comments" role="tabpanel" aria-labelledby="post-comments-tab"> 
<%--              		${post.comments} --%>
				Neki komentari<br>
				Razni<br>
				
			  </div>
              
            </div>
          </div>
        </div>
        <!-- /.card-body -->
      </div>
      <!-- /.card -->

    
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  

  <!-- Main Footer -->
  <footer class="main-footer">
    <!-- To the right -->
    <div class="float-right d-none d-sm-inline">
      Java
    </div>
    <!-- Default to the left -->
    <strong>Copyright &copy; 2019 <a href="https://cubes.edu.rs">Cubes School</a>.</strong> All rights reserved.
  </footer>
</div>
<!-- ./wrapper -->

<!-- REQUIRED SCRIPTS -->

<!-- jQuery -->
<script src="${pageContext.request.contextPath}/admin/plugins/jquery/jquery.min.js"></script>
<!-- Bootstrap 4 -->
<script src="${pageContext.request.contextPath}/admin/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- AdminLTE App -->
<script src="${pageContext.request.contextPath}/admin/dist/js/adminlte.min.js"></script>
</body>
</html>

