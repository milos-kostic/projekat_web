 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
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
            
              <!-- Links: -->
              <ul class="list-unstyled">
                <li> <a href="/BlogProject/">Home</a></li>
                <li> <a href="/BlogProject/blog-list/">Blog</a></li>
                <li> <a href="/BlogProject/contact-form">Contact</a></li>
           		<li> <a href="#">Login</a></li>
              </ul>
              
              
              <!-- Categories: -->
              <ul class="list-unstyled">              
	              <c:forEach var="categoryForFooter" items="${categoriesForFooter}">

	                	<!-- SEO friendly: -->
	                	<li> <a href="/BlogProject/blog-category/${categoryForFooter.name}/${categoryForFooter.id}">${categoryForFooter.name}</a></li> 
	                	
	              </c:forEach>               
              </ul>
              
              
              
            </div>
          </div>
          
          
        	  <!-- Footer Latest Post List: --> 
         		<div class="col-md-4">
		            <div class="latest-posts">
		            
		            	<c:forEach var="postForFooter" items="${postsForFooter}"> 
			            	<a href="/BlogProject/blog-detail/${postForFooter.seoTitle}/${postForFooter.id}">    
				                <div class="post d-flex align-items-center">
				                  	<div class="image"><img src="${postForFooter.image}" alt="..." class="img-fluid"></div>
				                  	<div class="title"><strong>${postForFooter.title}</strong>
				                  		<span class="date last-meta">${postForFooter.formattedDateOldStyle}</span> 
				                  	</div>
				                </div>
			                </a>
			            </c:forEach>
			     
		              </div>
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