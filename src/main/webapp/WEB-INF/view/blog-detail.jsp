<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
  <head>
  
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Bootstrap Blog - B4 Template by Bootstrap Temple</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    
    
   	 <!-- Css: -->   	 
     <jsp:include page="include-front-css-links.jsp"/>
     
    <!-- Tweaks for older IEs--><!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
        
        
      	<!-- Font Awesome Icons -->
  	  	<link rel="stylesheet" href="${pageContext.request.contextPath}/admin/plugins/fontawesome-free/css/all.min.css">
     
     
  	</head>
  
  <body>
  
  
  	 	<!-- Header: -->
     	<jsp:include page="include-front-header.jsp"/>
     
     
     
     
    <div class="container">
      <div class="row">
      
      
        <!-- Latest Posts -->
        
        <main class="post blog-post col-lg-8"> 
          <div class="container">
            <div class="post-single">
              <div class="post-thumbnail">
              		<img src="${post.image}" alt="..." class="img-fluid"  />
              </div>
              	
              	
              <div class="post-details">
              
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="category">
	                   	
                  		<c:choose>
							    <c:when test="${post.category.name != 'Uncategorized'}"> 
							       <a href="/BlogProject/blog-category/${post.category.name}/${post.category.id}">${post.category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${post.category.name}</a> 
							        <br />
							    </c:otherwise>
						</c:choose>
										             
	                  </div>
	                </div>
	                <h1>${post.title}<a href="#"><i class="fa fa-bookmark-o"></i></a></h1>
	                
	                <div class="post-footer d-flex align-items-center flex-column flex-sm-row"> 
	                	<a href="/BlogProject/blog-user/${post.user.username}" class="author d-flex align-items-center flex-wrap">
	                    <div class="avatar">
	                    	<img src="${post.user.image}" alt="..." class="img-fluid"/>
	                    </div>
	                    <div class="title"><span>${post.user.name} ${post.user.surname}</span></div></a>
	                  <div class="d-flex align-items-center flex-wrap">       
	                    <div class="date"><i class="icon-clock"></i>${post.formattedDate}</div><!-- 2 months ago -->
	                    <div class="views"><i class="icon-eye"></i>${post.views}</div>
	                    <div class="comments meta-last"><a href="#post-comments"><i class="icon-comment"></i>${post.commentsCount}</a></div>
	                  </div>
	                </div>
	                
	                 <div class="post-body">
	                 	${post.description}
	                 </div>
	                 
	                <div class="post-body">
	                
	                	 	 ${post.content}
	                	
	                		<br/><br/><br/>
	                		 
							                  
		             </div>
	                
	                
	                <!-- Tags: -->
	                <div class="post-tags">
	                
	                	<c:forEach var="tag" items="${post.tags}"> 
	                		<a href="/BlogProject/blog-tag/${tag.title}/${tag.id}" class="tag">#${tag.title}</a>
						</c:forEach>
						                	 	                
	                </div>
	                
	                
	                
	             	<!--  next, previous: -->
	                <div class="posts-nav d-flex justify-content-between align-items-stretch flex-column flex-md-row">
	                
	                	<c:choose>
						    <c:when test="${previousPost.title == null}">						       
								<a href="#" class="prev-post text-left d-flex align-items-center"> 
				                    <div class="text"><strong class="text-primary"></strong>
				                      <h6>This is the first blogpost.</h6>
				                    </div>
			                    </a>
						        <br />						        
						    </c:when>    						    
						    <c:otherwise>	 
								<a href="/BlogProject/blog-detail/${previousPost.seoTitle}/${previousPost.id}" class="prev-post text-left d-flex align-items-center"> 
				                    <div class="icon prev"><i class="fa fa-angle-left"></i></div>
				                    <div class="text"><strong class="text-primary">Previous Post </strong>
				                      <h6>${previousPost.titleLimited}</h6>
				                    </div>
			                    </a>
						        <br /> 						        
						    </c:otherwise>
						</c:choose>
						
						<c:choose>
						    <c:when test="${nextPost.title == null}">						       
								<a href="#" class="next-post text-right d-flex align-items-center justify-content-end">								
				                    <div class="text"><strong class="text-primary"></strong>
				                      <h6>This is the last blogpost.</h6>
				                    </div> 
	                    		</a>
						        <br />						        
						    </c:when>    						    
						    <c:otherwise>						    
 						      	<a href="/BlogProject/blog-detail/${nextPost.seoTitle}/${nextPost.id}" class="next-post text-right d-flex align-items-center justify-content-end"> 
				                    <div class="text"><strong class="text-primary">Next Post </strong>
				                      <h6>${nextPost.titleLimited}</h6>
				                    </div>
				                    <div class="icon next"><i class="fa fa-angle-right">   </i>
				                    </div>
	                    		</a>
						        <br /> 						        
						    </c:otherwise>
						</c:choose>

	             
	                  
	                </div>
	                 <!--  next, previous. End. -->   
	                    
	                    
	                    
	                 <!-- Comments: -->      
	                <div class="post-comments" id="post-comments">
	                
		                  <header>
		                    <h3 class="h6">Post Comments<span class="no-of-comments">(${commentsCount})</span></h3>
		                  </header>
	                  
	                  
		                  <c:forEach var="comment" items="${post.comments}">
		                  
		                  	 <c:choose>
						    	<c:when test="${comment.status}">	
						    		
				                  <div class="comment">
				                    <div class="comment-header d-flex justify-content-between">
				                    
				                      <div class="user d-flex align-items-center">
				                      	
				                        <div class="image"> 
				                        	 <i class="fas fa-user"></i>
				                        </div>
				                        <div class="title"><strong>${comment.userName}</strong>
				                        	<span class="date">${comment.formattedDateSimpleStyle}</span>
				                        </div>
				                      </div>
				                    </div>
				                    <div class="comment-body">
				                      <p>${comment.body}</p>
				                    </div>
				                  </div>
			                  
			                  </c:when>
			                </c:choose>
			                  
		                  </c:forEach>
		                   
	                </div>
	                 <!-- Comments List end. -->      
	                
	                
	                
	                
	                
	                 <!-- Comments New: -->   
	                <div class="add-comment">
	                
		                  <header>
		                    <h3 class="h6">Leave a reply</h3>
		                  </header>
  
                        
                       <form:form action="/BlogProject/comment-save" modelAttribute="comment" role="form"
                       					class="comment-form">  
                       					 
	              			<form:hidden value="${post.id}" path="id"/>
	              			
                            <div class="row">
                            
	                            
	                            
	                            
				                                <div class="form-group col-md-6">
				                                    <form:input type="text" 
				                                    			name="userName" path="userName" 
				                                    						id="user-name" 
				                                    						placeholder="Your Name" 
				                                    						class="form-control"/>
				                                    <form:errors path="userName" cssClass="error" style="color: red;"/>
				                                </div>				                                
				                           		
				                            	
	                            	
	                            	
	                            	
	                            	
	                                <div class="form-group col-md-6">
	                                    <form:input type="email" name="email" 
	                                    		path="userEmail" id="user-email" placeholder="Email Address (will not be published)" class="form-control"/>
	                                     <form:errors path="userEmail" cssClass="error" style="color: red;"/>
	                                </div>
	                              
	                               	
	                                <div class="form-group col-md-12">
	                                    <form:textarea name="comment" 
	                                    		path="body" id="comment-body" placeholder="Type your comment" class="form-control" rows="20"></form:textarea>
	                                    <form:errors path="body" cssClass="error" style="color: red;"/>
	                                </div>

	                                 
                                 
                           
	                                <div class="form-group col-md-12">
	                                    <button type="submit" class="btn btn-secondary">Submit Your Comment</button>
	                                </div>
                                
                                
                            </div>
                        </form:form>
                        
                        
                         
	                  
	                  
	                  
	                </div>
	                 <!-- Comments new: End. -->      
	                
              </div>
               <!-- Post details End. -->   
                              
            </div>
          </div>
        </main>
        
        
        <aside class="col-lg-4">
        
        
        
          	  <!-- Widget [Search Bar Widget]-->        
	      	  <jsp:include page="include-front-widget-search.jsp"/>
            
           
           
	          <!-- Widget [ 3 Latest most viewed Posts Widget]        --> 
	          <jsp:include page="include-front-widget-posts.jsp"/>
	          
	          
	          
	          <!-- Widget [Categories Widget]-->
	          <jsp:include page="include-front-widget-categories.jsp"/>
	           
	           
	           
	          <!-- Widget [Tags Cloud Widget]-->
	          <jsp:include page="include-front-widget-tags.jsp"/>
          
          
         
          
        </aside>
      </div>
    </div>
      
      
    <!-- Page Footer-->
    <footer class="main-footer">
      <div class="container">
        <div class="row">
          <div class="col-md-4">
            <div class="logo">
              <h6 class="text-white">Bootstrap Blog</h6>
            </div>
            <div class="contact-details">
              <p>53 Broadway, Broklyn, NY 11249</p>
              <p>Phone: (020) 123 456 789</p>
              <p>Email: <a href="mailto:info@company.com">Info@Company.com</a></p>
              <ul class="social-menu">
                <li class="list-inline-item"><a href="#"><i class="fa fa-facebook"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-twitter"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-instagram"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-behance"></i></a></li>
                <li class="list-inline-item"><a href="#"><i class="fa fa-pinterest"></i></a></li>
              </ul>
            </div>
          </div>
          <div class="col-md-4">
            <div class="menus d-flex">
              <ul class="list-unstyled">
                <li> <a href="index.html">Home</a></li>
                <li> <a href="blog.html">Blog</a></li>
                <li> <a href="contact.html">Contact</a></li>
                <li> <a href="#">Login</a></li>
              </ul>
              <ul class="list-unstyled">
                <li> <a href="blog-category.html">Growth</a></li>
                <li> <a href="blog-category.html">Local</a></li>
                <li> <a href="blog-category.html">Sales</a></li>
                <li> <a href="blog-category.html">Tips</a></li>
              </ul>
            </div>
          </div>
          <div class="col-md-4">
            <div class="latest-posts"><a href="blog-post.html">
                <div class="post d-flex align-items-center">
                  <div class="image"><img src="${pageContext.request.contextPath}/front/img/small-thumbnail-1.jpg" alt="..." class="img-fluid"></div>
                  <div class="title"><strong>Hotels for all budgets</strong><span class="date last-meta">October 26, 2016</span></div>
                </div></a><a href="blog-post.html">
                <div class="post d-flex align-items-center">
                  <div class="image"><img src="${pageContext.request.contextPath}/front/img/small-thumbnail-2.jpg" alt="..." class="img-fluid"></div>
                  <div class="title"><strong>Great street atrs in London</strong><span class="date last-meta">October 26, 2016</span></div>
                </div></a><a href="blog-post.html">
                <div class="post d-flex align-items-center">
                  <div class="image"><img src="${pageContext.request.contextPath}/front/img/small-thumbnail-3.jpg" alt="..." class="img-fluid"></div>
                  <div class="title"><strong>Best coffee shops in Sydney</strong><span class="date last-meta">October 26, 2016</span></div>
                </div></a></div>
          </div>
        </div>
      </div>
      <div class="copyrights">
        <div class="container">
          <div class="row">
            <div class="col-md-6">
              <p>&copy; 2017. All rights reserved. Your great site.</p>
            </div>
            <div class="col-md-6 text-right">
              <p>Template By <a href="https://bootstrapious.com/p/bootstrap-carousel" class="text-white">Bootstrapious</a>
                <!-- Please do not remove the backlink to Bootstrap Temple unless you purchase an attribution-free license @ Bootstrap Temple or support us at http://bootstrapious.com/donate. It is part of the license conditions. Thanks for understanding :)                         -->
              </p>
            </div>
          </div>
        </div>
      </div>
    </footer>
    
    
    <!-- JavaScript files-->
    <script src="${pageContext.request.contextPath}/front/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/vendor/popper.js/umd/popper.min.js"> </script>
    <script src="${pageContext.request.contextPath}/front/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="${pageContext.request.contextPath}/front/vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/js/front.js"></script>
    
   	  <!-- validacija: -->
      <script src="${pageContext.request.contextPath}/front/js/jquery.validate.min.js" type="text/javascript"></script>        
       
		<script src="${pageContext.request.contextPath}/front/js/owl.carousel.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/front/js/main.js" type="text/javascript"></script>
    
     
           
        <script src="${pageContext.request.contextPath}/front/js/jquery.min.js" type="text/javascript"></script>
		 

		<script src="${pageContext.request.contextPath}/front/js/bootstrap.min.js" type="text/javascript"></script>	
    
    
    
    
    
 
    
    
  </body>
</html>