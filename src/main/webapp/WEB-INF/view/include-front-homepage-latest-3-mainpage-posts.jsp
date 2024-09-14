     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
  
     <section class="featured-posts no-padding-top">
	   <div class="container">
	   
	   
	   
	   
	   
        <!-- Post 1 -->
        <div class="row d-flex align-items-stretch">
          <div class="text col-lg-7">
            <div class="text-inner d-flex align-items-center">
              <div class="content">
                <header class="post-header">
                     <div class="category">
	                  	<c:choose>
						    <c:when test="${posts[0].category.name != 'Uncategorized'}"> 
						        <a href="/BlogProject/blog-category/${posts[0].category.name}/${posts[0].category.id}">${posts[0].category.name}</a>
						        <br />
						    </c:when>    
						    <c:otherwise>
						       <a href="#" style="pointer-events: none; cursor: default;">${posts[0].category.name}</a> 
						        <br />
						    </c:otherwise>
						</c:choose>
					</div>		
                  <a href="/BlogProject/blog-detail/${posts[0].seoTitle}/${posts[0].id}">
                    <h2 class="h4">${posts[0].title}</h2>
                  </a>
                </header>
                	<p>${posts[0].description}</p>
                <footer class="post-footer d-flex align-items-center"> 
                 <a href="blog-user/${posts[0].user.username}" class="author d-flex align-items-center flex-wrap">
                    <div class="avatar">
                    	<img src="${posts[0].user.image}" alt="..." class="img-fluid"></div>
                    <div class="title"><span>${posts[0].user.name} ${posts[0].user.surname}</span></div></a>
                  <div class="date"><i class="icon-clock"></i>${posts[0].monthsDifference}</div><!--  2 months ago -->
                  <div class="comments"><i class="icon-comment"></i>${posts[0].commentsCount}</div>
                </footer>
              </div>
            </div>
          </div>
          <div class="image col-lg-5">
            <a href="blog-detail/${posts[0].seoTitle}/${posts[0].id}">
              <img 
                  src="${posts[0].image}" 
                  alt="..."
                  >
            </a>
          </div>
        </div>
        
        
        
        
        
        
        <!-- Post  2      -->
        <div class="row d-flex align-items-stretch">
          <div class="image col-lg-5">
          	    <a href="blog-detail/${posts[1].seoTitle}/${posts[1].id}">
          			<img src="${posts[1].image}" alt="...">
          		</a>
          </div>
          <div class="text col-lg-7">
            <div class="text-inner d-flex align-items-center">
              <div class="content">
                <header class="post-header">
                 <div class="category">
	                  	<c:choose>
						    <c:when test="${posts[1].category.name != 'Uncategorized'}">
						       <a href="/BlogProject/blog-category/${posts[1].category.name}/${posts[1].category.id}">${posts[1].category.name}</a>
						        <br />
						    </c:when>    
						    <c:otherwise>
						       <a href="#" style="pointer-events: none; cursor: default;">${posts[1].category.name}</a> 
						        <br />
						    </c:otherwise>
						</c:choose>
					</div>		
                  <a href="blog-detail/${posts[1].seoTitle}/${posts[1].id}">
                    <h2 class="h4">${posts[1].title}</h2></a>
                </header>
                <p>${posts[1].description}</p>
                <footer class="post-footer d-flex align-items-center">
                	<a href="blog-user/${posts[0].user.username}" class="author d-flex align-items-center flex-wrap">
                    <div class="avatar">
                    <img src="${posts[1].user.image}" alt="..." class="img-fluid"></div>
                    <div class="title"><span>${posts[1].user.name} ${posts[1].user.surname}</span></div></a>
                  <div class="date"><i class="icon-clock"></i>${posts[1].monthsDifference}</div>
                  <div class="comments"><i class="icon-comment"></i>${posts[1].commentsCount}</div>
                </footer>
              </div>
            </div>
          </div>
        </div>
        <!-- Post  3 -->
        <div class="row d-flex align-items-stretch">
          <div class="text col-lg-7">
            <div class="text-inner d-flex align-items-center">
              <div class="content">
                <header class="post-header">
                 <div class="category">
	                  	<c:choose>
						    <c:when test="${posts[2].category.name != 'Uncategorized'}">
						       <a href="/BlogProject/blog-category/${posts[2].category.name}/${posts[2].category.id}">${posts[2].category.name}</a>
						        <br />
						    </c:when>    
						    <c:otherwise>
						       <a href="#" style="pointer-events: none; cursor: default;">${posts[2].category.name}</a> 
						        <br />
						    </c:otherwise>
						</c:choose>
					</div>		
                  <a href="blog-detail/${posts[2].seoTitle}/${posts[2].id}">
                    <h2 class="h4">${posts[2].title}</h2></a>
                </header>
                <p>${posts[2].description}</p>
                <footer class="post-footer d-flex align-items-center">
                	<a href="blog-user/${posts[2].user.username}" class="author d-flex align-items-center flex-wrap">
                    <div class="avatar">
                    <img src="${posts[2].user.image}" alt="..." class="img-fluid"></div>
                    <div class="title"><span>${posts[2].user.name} ${posts[2].user.surname}</span></div></a>
                  <div class="date"><i class="icon-clock"></i>${posts[2].monthsDifference}</div>
                  <div class="comments"><i class="icon-comment"></i>${posts[2].commentsCount}</div>
                </footer>
              </div>
            </div>
          </div>
          <div class="image col-lg-5">
          	    <a href="blog-detail/${posts[2].seoTitle}/${posts[2].id}">
          			<img src="${posts[2].image}" alt="...">
          		</a>
          </div>
        </div>
        
          </div>
    	</section>