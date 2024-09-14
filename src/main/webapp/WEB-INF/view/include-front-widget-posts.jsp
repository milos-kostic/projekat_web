    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     <div class="widget latest-posts">
          
            <header>
              <h3 class="h6">Latest Posts</h3>
            </header>
            
            <div class="blog-posts">
            
            	<c:forEach var="latestPostThisMonth" items="${latest3MostViewedPostsAtLastMonth}">
	            	<a href="/BlogProject/blog-detail/${latestPostThisMonth.seoTitle}/${latestPostThisMonth.id}">
		                <div class="item d-flex align-items-center">
		                  <div class="image"><img src="${latestPostThisMonth.image}" alt="..." class="img-fluid"></div>
		                  <div class="title"><strong>${latestPostThisMonth.title}</strong>
		                    <div class="d-flex align-items-center">
		                      <div class="views"><i class="icon-eye"></i> ${latestPostThisMonth.views}</div>
		                      <div class="comments"><i class="icon-comment"></i>${latestPostThisMonth.commentsCount}</div>
		                    </div>
		                  </div>
		                 </div>
	                 </a>
                </c:forEach>                
                
                </div>
          </div>
          