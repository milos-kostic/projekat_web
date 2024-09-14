<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 
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
            <h1>Posts</h1>
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
				           <div> 
	
							   <div class="widget search">
									   
									   
								         <header>
								           <h3 class="h6">Search posts</h3>
								         </header>		
								         
								         			 
								         			
											<!--  Pretraga: --> 
										    <form action="/BlogProject/admin/post-search"  role="form" >
										    
										                <div class="card-body">
										                 
										                  <div class="row">
										                     
										                      <!--   Title: -->
										                      <div class="col-md-3 form-group">									                      
											                        <label>Title</label> 
			   														<input type="text" class="form-control" placeholder="Enter title"  
			 								                        		name="title" /> <!-- path="title" -->	 								                        		
			 								                  </div>       		
			 								                
			 								                 
			 								                  <!-- po username samo za admin rolu, blogger vidi samo svoje postove: -->
										                     <c:choose>
										                       	 <c:when test="${isAdmin}">			       
 	 								                        		
			 								                        <div class="col-md-2 form-group">
									                                    <label>User</label>
									                                    <select class="form-control" name="username">
									                                        <option value="">--Choose User --</option> 
									                                        <c:forEach var="user" items="${allUsers}">
									                                        	<option value="${user.username}">${user.username}</option>
									                                      	</c:forEach>
									                                    </select>
									                                </div>  
									                                		
										                       </c:when>										                       
										                      </c:choose>
										                       
										                       
										                       <!--   Category: -->
										                       <div class="col-md-2 form-group">
								                                    <label>Category</label>
								                                    <select class="form-control" name="categoryId">
								                                        <option value="">--Choose Category --</option>
								                                        <c:forEach var="category" items="${categories}">
								                                        	<option value="${category.id}">${category.name}</option>
								                                      	</c:forEach>
								                                    </select>
								                                </div>  		
								                                
			                      
										                       <!--   Status: -->
										                       <div class="col-md-1 form-group">
								                                    <label>Status</label>
								                                    <select class="form-control" name="status">
								                                        <option value="">-- All --</option>
								                                        <option value="1">Enabled</option>
								                                        <option value="0">Disabled</option>
								                                    </select>
								                               </div>
                                
                                
                                       				  <!-- Dugme Search: -->  
												      <div class="col-md-3 form-group" style="position: relative;top: 50%;transform: translateY(46%);">
												         	<button type="submit" class="btn btn-primary"><i class="icon-search"></i></button>														         					                   	 
												      </div>
								                                  	
											       </div>
										    
									           </div>
										         
						       </div>
          
					       </div>
					        
              	  
                	<!-- Add New: -->
	                <div class="card-tools">
	                  <a href="post-form" class="btn btn-success">
	                    <i class="fas fa-plus-square"></i>
	                    Add new Post
	                  </a>
	                </div>
	                  
              </div>
 


              <!-- /.card-header -->
              <div class="card-body">
                <table class="table table-bordered">
                
                  <thead>                  
                    <tr>
                      <th class="text-center">ID</th>
                      <th class="text-center">Thumb</th>
                      <th class="text-center">Title</th>
                     <!--  <th class="text-center">Description</th> --> <!-- na dugme Detalji -->
                      <th class="text-center">Status</th>
                       <th class="text-center">Homepage</th>
                      <th class="text-center">Category</th>                    
<!--                       <th class="text-center">Image</th> -->
					  <th class="text-center">Comments</th>   
					  <th class="text-center">Views</th>   
					  <th class="text-center">Author</th>   
					  <th class="text-center">Created</th>   
                      <th class="text-center">Description</th>                      
                      <th class="text-center">Actions</th>
                    </tr>
                  </thead>
                  
                  <tbody>
                  
                  
                  	<c:forEach var="post" items="${posts}">
                  	
	                    <tr>
	                      <td>
	                      	${post.id}
	                      </td>
	                      <td class="text-center">
	                        <img src="${post.image2}" style="max-width: 80px;max-height: 80px;">
	                      </td>
	                      <td>
	                        <strong>${post.title}</strong>
	                      </td>
	                      <td>
	                         <c:choose>
							    <c:when test="${post.enabled}">								    					     
  									<font color="green">Enabled</font>  									
							    </c:when>    
							    <c:otherwise>  							    	 
							    	<font color="red">Disabled</font>							    	 	
							    </c:otherwise>
							</c:choose>		
	                      </td> 
	                      <td>	                      
	                         <c:choose>
							    <c:when test="${post.homepage}">								    					     
  									<font color="green">Yes</font>  									
							    </c:when>    
							    <c:otherwise>  							    	 
							    	<font color="red">No</font>							    	 	
							    </c:otherwise>
							</c:choose>		
	                      </td> 
	                      <td>
	                        ${post.category.name}
	                      </td> 
	                      <td>
	                        ${post.commentsCount}
	                      </td> 
  						  <td>
	                        ${post.views}
	                      </td>
	                      <td>
	                        <strong>${post.user.username}</strong>
	                      </td>
	                      <td>
	                        ${post.createdAtFormatted}
	                      </td>
	                      <td>
	                          ${post.descriptionLimited}
	                      </td> 
	                      
	                      <td class="text-center">
	                        <div class="btn-group"> 
	                          <a href="post-update?id=${post.id}" class="btn btn-info">
	                            <i class="fas fa-edit"></i>
	                          </a>
	                          
	                              
               
                     <c:choose>
                       	 <c:when test="${isAdmin}">
<!--                        Role: kopirano iz user-form: -->
							      <!-- enable - disable: -->
	                              <c:choose>
	                          		<c:when test="${post.enabled}">
	                          			<a href="post-enabled?id=${post.id}" class="btn btn-info">
	                          			<!-- ?username= umesto ?id= -->
	                          		  	<i class="fas fa-eye"></i>
	                          		  	</a>
	                          		</c:when>
	                          		<c:otherwise>
	                          			<a href="post-enabled?id=${post.id}" class="btn btn-danger"> 
	                          		 	 <i class="fas fa-eye-slash"></i>
	                          		 	 </a>
	                          		</c:otherwise>
	                          	  </c:choose>
                      		   
                       	 </c:when>
                       	 <c:otherwise>
                       	  		
                       	 </c:otherwise>
                       </c:choose>
                   
                    
                          	  
                          	  
	                          <button type="button" class="btn btn-info" data-toggle="modal" data-target="#delete-modal-${post.id}">
	                            <i class="fas fa-trash"></i>
	                          </button>
	                          
 
	                        </div>
	                      </td>
	                    </tr>
	                    
	                    
	                    
	                    	<!-- Modal: -->
	                     <div class="modal fade" id="delete-modal-${post.id}">
					      <div class="modal-dialog">
					        <div class="modal-content">
					          <div class="modal-header">
					            <h4 class="modal-title">Delete Post</h4>
					            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
					              <span aria-hidden="true">&times;</span>
					            </button>
					          </div>
					          <div class="modal-body">
					            <p>Are you sure you want to delete blogpost ${post.title}?</p>
					            <strong></strong>
					          </div>
					          <div class="modal-footer justify-content-between">
					            <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					            <a href="post-delete?id=${post.id}">
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
							      
								    <c:url value="/admin/post-search" var="prev">
								   		<c:param name="title" value="${title}"/>
								   		<c:param name="categoryId" value="${categoryId}"/>
								   		<c:param name="username" value="${username}"/>
								   		<c:param name="status" value="${status}"/> 
								   		
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
								            	<c:if test="${maxPages>1}">
								               		 <span>${i.index}</span>
								               	</c:if>
								            </c:when>
								            
								            <c:otherwise>
								                 <c:url value="/admin/post-search" var="url">
								                 		<c:param name="title" value="${title}"/>
								   						<c:param name="categoryId" value="${categoryId}"/>
								   						<c:param name="username" value="${username}"/>
								   						<c:param name="status" value="${status}"/> 
								   						
								                    	<c:param name="page" value="${i.index}"/>
								                 </c:url>
								                 <a href='<c:out value="${url}" />'>${i.index}</a>
								            </c:otherwise>
								            
								        </c:choose>
								       
								    </c:forEach>
								     <c:url value="/admin/post-search" var="next">
								     	<c:param name="title" value="${title}"/>
								   		<c:param name="categoryId" value="${categoryId}"/>
								   		<c:param name="username" value="${username}"/>
								   		<c:param name="status" value="${status}"/> 
								   		
								        <c:param name="page" value="${page + 1}"/>
								    </c:url>
								    <c:if test="${page + 1 <= maxPages}">
								        <a href='<c:out value="${next}" />' class="pn next">
								        	... Next page <i class="fa fa-angle-right"> </i></a>
								    </c:if>
								
		                       </div>
	                  
		                   </ul>  
	                    </nav>	                 
	                    <!--  paginacija -->
                        
                         
                         
                         
                         
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
            <h4 class="modal-title">Delete Post</h4>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p>Are you sure you want to delete blogpost?</p>
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
