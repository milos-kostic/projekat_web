    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
      <!-- 5 Categories ordered by field Priority: -->
      <div class="widget categories">
        <header>
          <h3 class="h6">Categories</h3>
        </header> 
        	<c:forEach var="categoryForWidget" items="${categoriesForWidget}">
        		<div class="item d-flex justify-content-between"> 
					<a href="/BlogProject/blog-category/${categoryForWidget.name}/${categoryForWidget.id}"> 
        				${categoryForWidget.name}
        			</a>
        			<span>${categoryForWidget.postsCount}</span>
        		</div>
        	</c:forEach> 
       </div>
       