<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


 	 <!-- Admin CSS LINKS: -->
 	 <jsp:include page="include-admin-css-links.jsp"></jsp:include>
 		


</head>
<body class="hold-transition sidebar-mini">
<div class="wrapper">
 
 		<!-- Admin Header: -->
 		<jsp:include page="include-admin-header-profile.jsp"></jsp:include>
 		
 		
  <!-- Main Sidebar Container -->
  <aside class="main-sidebar sidebar-dark-primary elevation-4">
    
     
	    <!-- Admin Menu: -->
	    <jsp:include page="include-admin-menu.jsp"/>
	  
	     
  	</aside>



  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <div class="container-fluid">
        <div class="row mb-2">
          <div class="col-sm-6">
            <h1>Comments</h1>
          </div>
        </div>
      </div><!-- /.container-fluid -->
    </section>
 
       
    <!-- Main content -->
    <section class="content">
      <div class="container-fluid">
        <div class="row">
          <div class="col-md-12">
            <div class="card">
              <div class="card-header">
              	
              	 
		              
		              <!-- Search: --> 
		              <div class="widget search">	   
		                 <form class="input-group search" method="GET" id="searchCommentsForm"
		                 		action="comment-search">
		                 		
		                   <div class="card-body">										                
										                
							 <div class="row">                             
				                	<div class="col-md-3 form-group">
				                	   <label>Post Id</label>    
				                    	<input class="form-control" type="text" placeholder="Enter Post-Id Number" aria-label="Search" 
				                    			name="postId">
				                    </div>		
				                    	
				                    <div class="col-md-3 form-group" style="position: relative;top: 50%;transform: translateY(46%);">
				                        <button type="submit" name="search-form" value="search"class="btn btn-primary">
				                        	<i class="icon-search"></i>
				                        </button> 
				                    </div> 
		                     </div> 
		                   </div>
		                   
		                 </form>
		              </div>  
			               
 
              </div>
 


              <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-bordered">
                
                  <thead>                  
                    <tr>
                      <th class="text-center">ID</th>
                      <th class="text-center">Body</th>
                      <th class="text-center">Author</th> 
                      <th class="text-center">Author email</th>
                      
                      <th class="text-center">Post</th>     
                      
					  <th class="text-center">Created</th>                   
                      <th class="text-center">Actions</th>
                    </tr>
                  </thead>
                  
                  <tbody>
                  
                    
                     <c:choose>                     
                       	 <c:when test="${isAdmin}">
                       	 </c:when> 
                       	 <c:otherwise>
                       	 </c:otherwise>
                     </c:choose>
                     	
                    
                    
                  	<c:forEach var="comment" items="${comments}">
                  	
                  		<c:choose>
                  		 
                  			 <c:when test="${!comment.isSeen}">
			                    <tr>
			                      <td><strong>${comment.id}</strong></td>
			                      <td><strong>${comment.body}"</strong></td>	                      
			                      <td><strong>${comment.userName}</strong></td> 
			                      <td><strong>${comment.userEmail}</strong></td> 
			                      <td><strong>#${comment.post.id} - ${comment.post.title}</strong></td> 	                       
			                      <td><strong>${comment.createdAtFormatted}</strong></td> 
			                      
				                      <!-- Actions: -->
				                      <td class="text-center">
				                        <div class="btn-group"> 
				                          
				                          <!-- enable - disable: -->
			                              <c:choose>
			                          		<c:when test="${comment.status}">
			                          			<a href="comment-enabled?id=${comment.id}" class="btn btn-info">
			                          			<!-- ?username= umesto ?id= -->
			                          		  	<i class="fas fa-check"></i>
			                          		  	</a>
			                          		</c:when>
			                          		<c:otherwise>
			                          			<a href="comment-enabled?id=${comment.id}" class="btn btn-danger"> 
			                          		 	 <i class="fas fa-minus-circle"></i>
			                          		 	 </a>
			                          		</c:otherwise>
			                          	  </c:choose>
			                          		
			                          		
			                          	    <!-- isSeen: -->
			                          	    <c:choose>
			                          		<c:when test="${comment.isSeen}">
			                          			<a href="comment-seen?id=${comment.id}" class="btn btn-info">
			                          			<!-- ?username= umesto ?id= -->
			                          		  	<i class="fas fa-eye"></i>
			                          		  	</a>
			                          		</c:when>
			                          		<c:otherwise>
			                          			<a href="comment-seen?id=${comment.id}" class="btn btn-danger"> 
			                          		 	 <i class="fas fas fa-hand-pointer-o"></i>
			                          		 	 </a>
			                          		</c:otherwise>
			                          	  </c:choose>
			                          	  
				                        </div>
				                      </td>
			                      
			                    	</tr>
			                    
                    	</c:when>
                    	<c:otherwise>
	                    	  <tr>
			                      <td>${comment.id}</td>
			                      <td>${comment.body}"</td>	                      
			                      <td>${comment.userName}</td> 
			                      <td>${comment.userEmail}</td> 
			                      <td><strong>#${comment.post.id}</strong> - ${comment.post.title}</td> 	                       
			                      <td>${comment.createdAtFormatted}</td> 
			                      
				                      <!-- Actions: -->
				                      <td class="text-center">
				                        <div class="btn-group"> 
				                          
				                          <!-- enable - disable: -->
			                              <c:choose>
			                          		<c:when test="${comment.status}">
			                          			<a href="comment-enabled?id=${comment.id}" class="btn btn-info">
			                          			<!-- ?username= umesto ?id= -->
			                          		  	<i class="fas fa-check"></i>
			                          		  	</a>
			                          		</c:when>
			                          		<c:otherwise>
			                          			<a href="comment-enabled?id=${comment.id}" class="btn btn-danger"> 
			                          		 	 <i class="fas fa-minus-circle"></i>
			                          		 	 </a>
			                          		</c:otherwise>
			                          	  </c:choose>
			                          		
			                          		
			                          	    <!-- isSeen: -->
			                          	    <c:choose>
			                          		<c:when test="${comment.isSeen}">
			                          			<a href="comment-seen?id=${comment.id}" class="btn btn-info">
			                          			<!-- ?username= umesto ?id= -->
			                          		  	<i class="fas fa-eye"></i>
			                          		  	</a>
			                          		</c:when>
			                          		<c:otherwise>
			                          			<a href="comment-seen?id=${comment.id}" class="btn btn-danger"> 
			                          		 	 <i class="fas fas fa-hand-pointer-o"></i>
			                          		 	 </a>
			                          		</c:otherwise>
			                          	  </c:choose>
			                          	  
				                        </div>
				                      </td>
			                      
			                    	</tr>
			                    
	                    	
	                    	
	                    </c:otherwise>	                    
	                </c:choose>
	                    
	                    
	                    	<!-- Modal: -->
	                     <div class="modal fade" id="delete-modal-${comment.id}">
					      <div class="modal-dialog">
					        <div class="modal-content">
					          <div class="modal-header">
					            <h4 class="modal-title">Delete Comment</h4>
					            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					              <span aria-hidden="true">&times;</span>
					            </button>
					          </div>
					          <div class="modal-body">
					            <p>Are you sure you want to delete comment ${comment.body}?</p>
					            <strong></strong>
					          </div>
					          <div class="modal-footer justify-content-between">
					            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					            <a href="comment-delete?id=${comment.id}">
					            	<button type="button" class="btn btn-danger">Delete</button>
					            </a>
					          </div>
					        </div>
					        <!-- /.modal-content -->
					      </div>
					      <!-- /.modal-dialog -->
					    </div>
				    <!-- /.modal -->
				    
                    </c:forEach>
                    
                    
                    
                  </tbody>
                </table>
              </div>
              
              
              
              
                 <!--  paginacija pocetak: -->	               	
			          <nav aria-label="Page navigation example">
			          		<ul class="pagination pagination-template d-flex justify-content-center">
			           
				             <div id="pagination" >		
							      
								    <c:url value="/admin/comment-list" var="prev">
								        <c:param name="page" value="${page-1}"/>
								    </c:url>
								    <c:if test="${page > 1}">
								        <a href="<c:out value="${prev}"  />" class="pn prev"> 
								        	<i class="fa fa-angle-left"> </i> Previous page...
								        </a>
								    </c:if>
								
								    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
								     
								        <c:choose>
								        
								            <c:when test="${page == i.index}">
								                <span>${i.index}</span>
								            </c:when>
								            
								            <c:otherwise>
								                  <c:url value="/admin/comment-list" var="url">
								                    <c:param name="page" value="${i.index}"/>
								                </c:url>
								                <a href='<c:out value="${url}" />'>${i.index}</a>
								            </c:otherwise>
								            
								        </c:choose>
								       
								    </c:forEach>
								     <c:url value="/admin/comment-list" var="next">
								        <c:param name="page" value="${page + 1}"/>
								    </c:url>
								    <c:if test="${page + 1 <= maxPages}">
								        <a href='<c:out value="${next}" />' class="pn next">
								        	<i class="fa fa-angle-right"> </i>... Next page</a>
								    </c:if>
								
		                       </div>
	                  
		                   </ul>  
	                    </nav>
	                 
	                  <!--  paginacija: kraj. ***************************** -->
                        
                        
              
              <!-- /.card-body -->
              <div class="card-footer clearfix">
                
              </div>
            </div>
            <!-- /.card -->
          </div>
          <!-- /.col -->
        </div>
        <!-- /.row -->
      </div><!-- /.container-fluid -->
    </section>
    <!-- /.content -->

	  <div class="modal fade" id="delete-modal">
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-header">
            <h4 class="modal-title">Delete Comment</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p>Are you sure you want to delete comment?</p>
            <strong></strong>
          </div>
          <div class="modal-footer justify-content-between">
            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
            <button type="button" class="btn btn-danger">Delete</button>
          </div>
        </div>
        <!-- /.modal-content -->
      </div>
      <!-- /.modal-dialog -->
    </div>
	    <!-- /.modal -->
	    
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
