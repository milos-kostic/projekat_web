<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
	 	<!-- Brand Logo -->
	    <a href="http://localhost:8080/BlogProject/admin/" class="brand-link">
	      <img src="${pageContext.request.contextPath}/admin/dist/img/AdminLTELogo.png" alt="Cubes School Logo" class="brand-image img-circle elevation-3"
	           style="opacity: .8">
	      <span class="brand-text font-weight-light">Ilić rasadnik</span>
	    </a>
	    
	    
	    
        <div class="sidebar">
      <!-- Sidebar Menu -->
      <nav class="mt-2">
        <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
          <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
               
              
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Post
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="post-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Post list</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="post-form" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add Post</p>
                </a>
              </li>
            </ul>
          </li>
            
           
	          <li class="nav-item has-treeview">
	            <a href="#" class="nav-link">
	              <i class="nav-icon far fa-plus-square"></i>
	              <p>
	                Categories
	                <i class="right fas fa-angle-left"></i>
	              </p>
	            </a>
	            <ul class="nav nav-treeview">
	              <li class="nav-item">
	                <a href="category-list" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Categories list</p>
	                </a>
	              </li>
	              <li class="nav-item">
	                <a href="category-form" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Add Category</p>
	                </a>
	              </li>
	            </ul>
	          </li>
        
          
          <li class="nav-item has-treeview">
            <a href="#" class="nav-link">
              <i class="nav-icon far fa-plus-square"></i>
              <p>
                Tags
                <i class="right fas fa-angle-left"></i>
              </p>
            </a>
            <ul class="nav nav-treeview">
              <li class="nav-item">
                <a href="tag-list" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Tags list</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="tag-form" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>Add Tag</p>
                </a>
              </li>
            </ul>
          </li>
          
          
          
	         <!-- pristup samo za admin rolu: -->
	         <sec:authorize access="hasRole('admin')">    
	          
	           <li class="nav-item has-treeview">
	            <a href="#" class="nav-link">
	              <i class="nav-icon far fa-plus-square"></i>
	              <p>
	                Users
	                <i class="right fas fa-angle-left"></i>
	              </p>
	            </a>
	            <ul class="nav nav-treeview">
	              <li class="nav-item">
	                <a href="user-list" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Users list</p>
	                </a>
	              </li>
	              <li class="nav-item">
	                <a href="user-form" class="nav-link">
	                  <i class="far fa-circle nav-icon"></i>
	                  <p>Add User</p>
	                </a>
	              </li>
	            </ul>
	          </li>
	          </sec:authorize>
           
       
                 
                 <li class="nav-item has-treeview">
		            <a href="#" class="nav-link">
		              <i class="nav-icon far fa-plus-square"></i>
		              <p>
		                Other
		                <i class="right fas fa-angle-left"></i>
		              </p>
		            </a>
		            <ul class="nav nav-treeview">
		            
		            <!-- pristup samo za admin rolu: -->
         			<sec:authorize access="hasRole('admin')">
         			    
			              <li class="nav-item">
			                <a href="message-list" class="nav-link">
			                  <i class="far fa-circle nav-icon"></i>
			                  <p>Message list</p>
			                </a>
			              </li> 
			              <li class="nav-item">
			                <a href="slider-list" class="nav-link">
			                  <i class="far fa-circle nav-icon"></i>
			                  <p>Slider list</p>
			                </a>
			              </li>
			              
			         </sec:authorize>
			              
			              <li class="nav-item">
			                <a href="comment-list" class="nav-link">
			                  <i class="far fa-circle nav-icon"></i>
			                  <p>Comment list</p>
			                </a>
			              </li>
			               
		            </ul>
		          </li>
		         
		          
		           
		          
		          
        </ul>
      </nav>
      <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->