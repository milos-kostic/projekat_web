  <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
  <section class="latest-posts"> 
	    
	      <div class="container">
	      
	        <header> 
	          <h2>Latest from the blog</h2>
	          <p class="text-big">Lorem ipsum dolor sit amet, consectetur adipisicing elit.</p>
	        </header>
	        
	        
	        <div class="owl-carousel" id="latest-posts-slider">
	         
	         <div class="row" >    
	            <div class="post col-md-4">  
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[0].seoTitle}/${latest12Posts[0].id}">
	              		<img src="${latest12Posts[0].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[0].formattedDate}</div>
	                   <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[0].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[0].category.id}">${latest12Posts[0].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[0].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[0].seoTitle}/${latest12Posts[0].id}">
	                  <h3 class="h4">${latest12Posts[0].title}</h3></a>
	                <p class="text-muted">${latest12Posts[0].description}</p>
	              </div>              
	            </div>            
	            <div class="post col-md-4">
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[1].seoTitle}/${latest12Posts[1].id}"><img src="${latest12Posts[1].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[1].formattedDate}</div>
	                 <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[1].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[1].category.id}">${latest12Posts[1].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[1].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[1].seoTitle}/${latest12Posts[1].id}">
	                  <h3 class="h4">${latest12Posts[1].title}</h3></a>
	                <p class="text-muted">${latest12Posts[1].description}</p>
	              </div>
	            </div>
	            <div class="post col-md-4">
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[2].seoTitle}/${latest12Posts[2].id}"><img src="${latest12Posts[2].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[2].formattedDate}</div>
	                <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[2].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[2].category.id}">${latest12Posts[2].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[2].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[2].seoTitle}/${latest12Posts[2].id}">
	                  <h3 class="h4">${latest12Posts[2].title}</h3></a>
	                <p class="text-muted">${latest12Posts[2].description}</p>
	              </div>
	            </div> 
	          </div>
	          
	          <div class="row">    
	            <div class="post col-md-4">  
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[3].seoTitle}/${latest12Posts[3].id}"><img src="${latest12Posts[3].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[3].formattedDate}</div>
	                   <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[3].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[3].category.id}">${latest12Posts[3].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[3].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[3].seoTitle}/${latest12Posts[3].id}">
	                  <h3 class="h4">${latest12Posts[3].title}</h3></a>
	                <p class="text-muted">${latest12Posts[3].description}</p>
	              </div>              
	            </div>            
	            <div class="post col-md-4">
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[4].seoTitle}/${latest12Posts[4].id}"><img src="${latest12Posts[4].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[4].formattedDate}</div>
	                   <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[4].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[4].category.id}">${latest12Posts[4].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[4].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[4].seoTitle}/${latest12Posts[4].id}">
	                  <h3 class="h4">${latest12Posts[4].title}</h3></a>
	                <p class="text-muted">${latest12Posts[4].description}</p>
	              </div>
	            </div>
	            <div class="post col-md-4">
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[5].seoTitle}/${latest12Posts[5].id}"><img src="${latest12Posts[5].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[5].formattedDate}</div>
	                   <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[5].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[5].category.id}">${latest12Posts[5].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[5].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[5].seoTitle}/${latest12Posts[5].id}">
	                  <h3 class="h4">${latest12Posts[5].title}</h3></a>
	                <p class="text-muted">${latest12Posts[5].description}</p>
	              </div>
	            </div> 
	          </div>
	          
	          <div class="row">    
	            <div class="post col-md-4">  
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[6].seoTitle}/${latest12Posts[6].id}"><img src="${latest12Posts[6].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[6].formattedDate}</div>
	                   <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[6].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[6].category.id}">${latest12Posts[6].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[6].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[6].seoTitle}/${latest12Posts[6].id}">
	                  <h3 class="h4">${latest12Posts[6].title}</h3></a>
	                <p class="text-muted">${latest12Posts[6].description}</p>
	              </div>              
	            </div>            
	            <div class="post col-md-4">
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[7].seoTitle}/${latest12Posts[7].id}"><img src="${latest12Posts[7].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[7].formattedDate}</div>
	                   <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[7].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[7].category.id}">${latest12Posts[7].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[7].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[7].seoTitle}/${latest12Posts[7].id}">
	                  <h3 class="h4">${latest12Posts[7].title}</h3></a>
	                <p class="text-muted">${latest12Posts[7].description}</p>
	              </div>
	            </div>
	            <div class="post col-md-4">
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[8].seoTitle}/${latest12Posts[8].id}"><img src="${latest12Posts[8].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[8].formattedDate}</div>
	                   <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[8].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[8].category.id}">${latest12Posts[8].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[8].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[8].seoTitle}/${latest12Posts[8].id}">
	                  <h3 class="h4">${latest12Posts[8].title}</h3></a>
	                <p class="text-muted">${latest12Posts[8].description}</p>
	              </div>
	            </div> 
	          </div>
	          
	            <div class="row">    
	            <div class="post col-md-4">  
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[9].seoTitle}/${latest12Posts[9].id}"><img src="${latest12Posts[9].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[9].formattedDate}</div>
	                  <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[9].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[9].category.id}">${latest12Posts[9].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[9].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[9].seoTitle}/${latest12Posts[9].id}">
	                  <h3 class="h4">${latest12Posts[9].title}</h3></a>
	                <p class="text-muted">${latest12Posts[9].description}</p>
	              </div>              
	            </div>            
	            <div class="post col-md-4">
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[10].seoTitle}/${latest12Posts[10].id}"><img src="${latest12Posts[10].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[10].formattedDate}</div>
	                  <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[10].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[10].category.id}">${latest12Posts[10].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[10].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[10].seoTitle}/${latest12Posts[10].id}">
	                  <h3 class="h4">${latest12Posts[10].title}</h3></a>
	                <p class="text-muted">${latest12Posts[10].description}</p>
	              </div>
	            </div>
	            <div class="post col-md-4">
	              <div class="post-thumbnail"><a href="/BlogProject/blog-detail/${latest12Posts[11].seoTitle}/${latest12Posts[11].id}"><img src="${latest12Posts[11].image}" alt="..." class="img-fluid"></a></div>
	              <div class="post-details">
	                <div class="post-meta d-flex justify-content-between">
	                  <div class="date">${latest12Posts[11].formattedDate}</div>
	                  <div class="category">
		                  	<c:choose>
							    <c:when test="${latest12Posts[11].category.name != 'Uncategorized'}">
							       <a href="/BlogProject/blog-list/?category=${latest12Posts[11].category.id}">${latest12Posts[11].category.name}</a>
							        <br />
							    </c:when>    
							    <c:otherwise>
							       <a href="#" style="pointer-events: none; cursor: default;">${latest12Posts[11].category.name}</a> 
							        <br />
							    </c:otherwise>
							</c:choose>
						</div>		
	                </div><a href="/BlogProject/blog-detail/${latest12Posts[11].seoTitle}/${latest12Posts[11].id}">
	                  <h3 class="h4">${latest12Posts[11].title}</h3></a>
	                <p class="text-muted">${latest12Posts[11].description}</p>
	              </div>
	            </div> 
	          </div>
	               
	          
	        </div>
	      </div>
	    </section>