       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
     
       <!-- Tags: -->
       <div class="widget tags">       
           <header>
             <h3 class="h6">Tags</h3>
           </header>
           <ul class="list-inline">            
           	<c:forEach var="tag" items="${tagsMostUsed}">
             		<li class="list-inline-item"> 
              		<a href="/BlogProject/blog-tag/${tag.title}/${tag.id}" class="tag">
              			${tag.title}
              		</a>
             		</li>
               </c:forEach>                
           </ul>
         </div>
         
             