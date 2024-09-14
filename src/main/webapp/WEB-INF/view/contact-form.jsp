<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Bootstrap Blog - B4 Template by Bootstrap Temple</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="robots" content="all,follow">
        
        
        
        <!-- Front Css: -->
        <jsp:include page="include-front-css-links.jsp"/> 
        
        
        <!-- Tweaks for older IEs--><!--[if lt IE 9]>
            <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
            <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script><![endif]-->
    </head>
    <body>
    
    
     
     	 <!-- Header: -->
     	 <jsp:include page="include-front-header.jsp"/>
     
     
     
     
        <!-- Hero Section -->
        <section style="background: url(${pageContext.request.contextPath}/front/img/hero.jpg); background-size: cover; background-position: center center" class="hero">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        <h1>Have an interesting news or idea? Don't hesitate to contact us!</h1>
                    </div>
                </div>
            </div>
        </section>
        
        
        <div class="container">
            <div class="row">
                
                <!-- Latest Posts -->
                <main class="col-lg-8"> 
                    <div class="container">
                     
                     
                    
                       <form:form action="contact-save" modelAttribute="message" 
                        			class="contact-form">
                          
                            <div class="row">
                            
                                <div class="form-group col-md-6">
                                    <form:input type="text" name="name" 
                                    	path="name" id="contact-name" 
                                    	placeholder="Your Name" class="form-control"/>
                                    	
<!--                                     <div class="error-msg"></div> -->
									<form:errors path="name" cssClass="error" style="color: red;"/>
									
                                </div>
                              
                        
                        
                                <div class="form-group col-md-6">
                                    <form:input type="text" name="surname" path="surname" id="contact-surname" placeholder="Your Surname" class="form-control"/>
                                    <form:errors path="surname" cssClass="error" style="color: red;"/>
                                </div>
                                <div class="form-group col-md-6">
                                    <input type="email" name="email" path="email" id="contact-email" placeholder="Email Address (will not be published)" class="form-control"/>
                                    	<form:errors path="email" cssClass="error" style="color: red;"/>
                                </div>
                                <div class="form-group col-md-12">
                                    <textarea name="message" path="message" id="contact-message" placeholder="Type your message" class="form-control" rows="20"></textarea>
                                    	<form:errors path="message" cssClass="error" style="color: red;"/>
                                </div>
                                
                                <div class="form-group col-md-12">
                                    <button type="submit" class="btn btn-secondary">Submit Your Message</button>
                                </div>
                                
                                
                            </div>
                        </form:form>
                        
                        
                    </div>
                </main>
                
                <aside class="col-lg-4">
                
                    <!-- Widget [Contact Widget]-->
                    <div class="widget categories">
                        <header>
                            <h3 class="h6">Contact Info</h3>
                            <div class="item d-flex justify-content-between">
                                <span>15 Yemen Road, Yemen</span>
                                <span><i class="fa fa-map-marker"></i></span>
                            </div>
                            <div class="item d-flex justify-content-between">
                                <span>(020) 123 456 789</span>
                                <span><i class="fa fa-phone"></i></span>
                            </div>
                            <div class="item d-flex justify-content-between">
                                <span>info@company.com</span>
                                <span><i class="fa fa-envelope"></i></span>
                            </div>
                        </header>

                    </div>
                    
                     
                     <!-- Widget Latest Posts: -->
                     <jsp:include page="include-front-widget-posts.jsp"/>
                    	   
                    
                </aside>
            </div>
        </div>
        
        
          <!-- Page Footer-->
          <jsp:include page="include-front-footer.jsp"/> 
        
        
        <!-- JavaScript files-->
  
	<script src="${pageContext.request.contextPath}/front/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/vendor/popper.js/umd/popper.min.js"> </script>
    <script src="${pageContext.request.contextPath}/front/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="${pageContext.request.contextPath}/front/vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/js/front.js"></script>
    
    
      

    </body>
</html>