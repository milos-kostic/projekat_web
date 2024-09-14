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
  </head>
  <body>
  
  
  		<!-- Header: -->
    	<jsp:include page="include-front-header.jsp" />
    
    
    
    <div class="container">
      <div class="row">
      
        <!-- Latest Posts -->
        <main class="posts-listing col-lg-8"> 
          <div class="container">
          
          
             	<h2 class="mb-3 author d-flex align-items-center flex-wrap">
		            <c:choose>
					    <c:when test="${authorImage!=null}">
					    
				              <div class="avatar">
				              	<img src="${authorImage}" style="
				              	max-height: 5vw; 
	    			 			min-height: 5vw; 
	     						max-width:5vw; 
	     						min-width: 5vw; 
	/*  			              	max-width: 70px;max-height: 70px; */  
				              	" alt="..." class="img-fluid rounded-circle">
				              </div>
				              <div class="title">
				                <span>${pageTitle}</span>
				              </div>
				           
					    </c:when>    
					    <c:otherwise>  
					    	<div class="title">
					    		<span>${pageTitle}</span> 
					        </div>
					    </c:otherwise>
					</c:choose>
				</h2>	 					           
          
            
            <br/>
            
            
          
            <div class="row">
            
            
            
	              <c:forEach var="post" items="${posts}">
	              
	              
	              
			             <!-- post -->
		              
			                <div class="post col-xl-6">
			                <div class="post-thumbnail"> 
								<a href="/BlogProject/blog-detail/${post.seoTitle}/${post.id}">
			                		<img src="${post.image}" alt="..." class="img-fluid"  		 
			                			width="337" height="337"  > 
			                	</a>
			                </div>		                
			                
			                <div class="post-details">
			                  <div class="post-meta d-flex justify-content-between">
			                    <div class="date meta-last">${post.formattedDate}</div><!--  20 May | 2016  -->
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
								</div><a href="/BlogProject/blog-detail/${post.seoTitle}/${post.id}">
			                    <h3 class="h4">${post.titleLimited}</h3></a>
			                  <p class="text-muted">${post.description}</p>
			                  <footer class="post-footer d-flex align-items-center"> 
						  	 <a href="/BlogProject/blog-user/${post.user.username}" class="author d-flex align-items-center flex-wrap"> 						   
			                      <div class="avatar"><img src="${post.user.image}" alt="..." class="img-fluid"></div>
			                      <div class="title"><span>${post.user.name} ${post.user.surname}</span></div></a>
			                    <div class="date"><i class="icon-clock"></i>${post.monthsDifference}</div><!-- 2 months ago -->
			                    <div class="comments meta-last"><i class="icon-comment"></i>${post.commentsCount}</div>
			                  </footer>
			                </div>
			                 
		              </div>
		              
	              </c:forEach>
	          
              
            </div>
            
            
            
            
	            
	              <!--  paginacija pocetak: -->
	               	
			          <nav aria-label="Page navigation example">
			          		<ul class="pagination pagination-template d-flex justify-content-center">
			           
				             <div id="pagination" >		
							      
								    <c:url value="/blog-search" var="prev">
								        <c:param name="text" value="${text}"/>
								        <c:param name="page" value="${page-1}"/>
								    </c:url>
								    <c:if test="${page > 1}">
								        <a href="<c:out value="${prev}"  />" class="pn prev"> 
								        	<i class="fa fa-angle-left"> </i> Prev...
								        </a>
								    </c:if>
								
								    <c:forEach begin="1" end="${maxPages}" step="1" varStatus="i">
								     
								        <c:choose>
								         
								              <c:when test="${page == i.index}"> 
								            	<c:if test="${maxPages>1}">
<%-- 								                	<span>${i.index}</span>  --%>
														<span 
															style="
															border-radius: 50%;
														    width: 25px;
														    height: 25px;
														    padding: 6px;
														
														    background: #fff;
														    border: 1px solid #AAA;
 														    color: #777; 
														    text-align: center;
														
														    font: 12px Arial, sans-serif;">${i.index}</span>
								                </c:if>
								            </c:when>
								            <c:otherwise>
								                  <c:url value="/blog-search" var="url">
								                    <c:param name="text" value="${text}"/>
								                    <c:param name="page" value="${i.index}"/>								                   
								                </c:url>
								                <a href='<c:out value="${url}" />'>${i.index}</a>
								            </c:otherwise>
								            
								        </c:choose>
								       
								    </c:forEach>
								     <c:url value="/blog-search" var="next">
								         <c:param name="text" value="${text}"/>
								        <c:param name="page" value="${page + 1}"/>
								    </c:url>
								    <c:if test="${page + 1 <= maxPages}">
								        <a href='<c:out value="${next}" />' class="pn next">
								        	 ...Next <i class="fa fa-angle-right"> </i></a>
								    </c:if>
								
		                       </div>
	                  
		                   </ul>  
	                    </nav>
	                 
	                  <!--  paginacija -->
                        
                        
                        
                         
            
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
     	<jsp:include page="include-front-footer.jsp" />
      
    
    
    
    <!-- JavaScript files-->
    <script src="${pageContext.request.contextPath}/front/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/vendor/popper.js/umd/popper.min.js"> </script>
    <script src="${pageContext.request.contextPath}/front/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="${pageContext.request.contextPath}/front/vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/js/front.js"></script>
  </body>
</html>