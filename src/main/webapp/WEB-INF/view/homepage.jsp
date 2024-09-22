<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
 

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Rasadnik Ilić</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="robots" content="all,follow">
    
    
    <!-- Front Css links: -->
    <jsp:include page="include-front-css-links.jsp"/>
    
    
    
  </head>
  
  
  
  
  <body>
   
   
		<!-- Header -->
  		<jsp:include page="include-front-header.jsp"/>



    	<!-- Sliders: Hero Section-->
    	<div id="index-slider" class="owl-carousel">     
		   	 <c:forEach var="slide" items="${sliders}">
			     <section style="background: url(${slide.image}); background-size: cover; background-position: center center" class="hero">
			        <div class="container">
			          <div class="row">
			            <div class="col-lg-7">
			              <h1 style="text-shadow: 1px 1px 2px black;">${slide.title}</h1>
			              <a href="${slide.buttonUrl}" target="_blank" class="hero-link">${slide.buttonTitle}</a>
			            </div>
			          </div>
			        </div>
			      </section>
		      </c:forEach>     
   		 </div>





    	<!-- Intro Section-->
    	<section class="intro">
		      <div class="container">
		        <div class="row">
		          <div class="col-lg-8">
		            <h2 class="h3">Some great intro here</h2>
		            <p class="text-big">Place a nice <strong>introduction</strong> here <strong>to catch reader's attention</strong>.</p>
		          </div>
		        </div>
		      </div>
	 	</section>
	    
	    
	   
        	<!-- Tri najnovija vazna posta: 
        	poslednja tri Cekirana posta za index page: -->
  			<jsp:include page="include-front-homepage-latest-3-mainpage-posts.jsp"/>
         
    
    
    
	    <!-- Divider Section-->
	    <section style="background: url(${pageContext.request.contextPath}/front/img/divider-bg.jpg); background-size: cover; background-position: center bottom" class="divider">
	      <div class="container">
	        <div class="row">
	          <div class="col-md-7">
	            <h2>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua</h2>
	            <a href="contact-form" class="hero-link">Contact Us</a>
	          </div>
	        </div>
	      </div>
	    </section>
    
    
			    	<!-- Latest Posts -->
			    	<jsp:include page="include-front-homepage-latest-12-posts.jsp"/> 
			        
	    
	    
 	    <!-- static Gallery Section-->
 	    <jsp:include page="include-front-homepage-static-gallery.jsp"/> 
   		
    
    
        <!-- Page Footer-->
        <jsp:include page="include-front-footer.jsp"/> 
     
    
    
    
    
    
    <!-- JavaScript files-->
    <script src="${pageContext.request.contextPath}/front/vendor/jquery/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/vendor/popper.js/umd/popper.min.js"> </script>
    <script src="${pageContext.request.contextPath}/front/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/vendor/jquery.cookie/jquery.cookie.js"> </script>
    <script src="${pageContext.request.contextPath}/front/vendor/@fancyapps/fancybox/jquery.fancybox.min.js"></script>
    <script src="${pageContext.request.contextPath}/front/js/front.js"></script>


    <script src="${pageContext.request.contextPath}/front/plugins/owl-carousel2/owl.carousel.min.js"></script>
    <script>
      $("#index-slider").owlCarousel({
        "items": 1,
        "loop": true,
        "autoplay": true,
        "autoplayHoverPause": true
      });

      $("#latest-posts-slider").owlCarousel({
        "items": 1,
        "loop": true,
        "autoplay": true,
        "autoplayHoverPause": true
      });
    </script>
    
  </body>
</html>