package cubes.main.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cubes.main.dao.CategoryDAO;
import cubes.main.dao.CommentDAO;
import cubes.main.dao.MessageDAO;
import cubes.main.dao.PostDAO;
import cubes.main.dao.SliderDAO; 
import cubes.main.dao.TagDAO;
import cubes.main.dao.UserDAO;
import cubes.main.entity.Category;
import cubes.main.entity.Comment;
import cubes.main.entity.Message;
import cubes.main.entity.Post; 
import cubes.main.entity.Slider;  
import cubes.main.entity.Tag;
import cubes.main.entity.User;
import cubes.main.service.CategoryService;


@Controller
public class FrontController {

	
		@Autowired
		private MessageDAO messageDAO;
	
		@Autowired
		private PostDAO postDAO;
		
		@Autowired
		private CategoryService categoryService;  
		
		@Autowired
		private SliderDAO sliderDAO;
 	
		@Autowired
		private TagDAO tagDAO; 
		
		@Autowired
		private UserDAO userDAO; 
		
		@Autowired
		private CommentDAO commentDAO; 
		
		
		
		
		//-------------- HOMEPAGE ------------------------------ 
		
		@RequestMapping(value={"/index","/homepage","","/"})
		public String getHomepage(Model model) {
			
			// za glavni deo, 3 najnovija sa homepage = 1:
			List<Post> posts = postDAO.getPostListForMainPage();	 
			// za slajder sa postovima, na dnu strane, 12 najnovijih, bez obzira na na homepage:
			List<Post> latest12Posts = postDAO.getLatest12PostsForMainPage();	 
			
			// 3 najnovija bez obzira na homepage, za footer:
			List<Post> postsForFooter = postDAO.getLatest3Posts(); // isto kao getLatest12PostsForMainPage samo 3 
			
			
			
			
			
            // List<Category> categories = categoryService.getCategoryList();  
			List<Category> categories = categoryService.getCategoryListForMainPage(); // cetiri kategorije

			List<Category> categoriesForWidget = categoryService.getCategoryListForWidget();
			model.addAttribute("categoriesForWidget", categoriesForWidget);
			
			List<Category> categoriesForFooter = categoryService.getCategoryListForFooter();
			model.addAttribute("categoriesForFooter", categoriesForFooter);
			
		 
			
			
			//
     		List<Slider> sliders = sliderDAO.getSliderListOrdered(); // by orderNumber. postoji i getSliderList()
 
     		//
			model.addAttribute("posts", posts); 
			model.addAttribute("latest12Posts", latest12Posts);		
			model.addAttribute("postsForFooter", postsForFooter);	
			
		    model.addAttribute("categories", categories);
					 
			model.addAttribute("sliders",sliders);
 
			
			 return "homepage"; 
		}
		
		
		
		
		
		
		
		//-------------- CONTACT ---------------
	
		@RequestMapping(value="/contact-form")
		public String getContactPage(Model model) {			
			
			
			// widget: latest3MostViewedPostsAtLastMonth:
			List<Post> latest3MostViewedPostsAtLastMonth = postDAO.getLatest3MostViewedPostsAtLastMonth(); 
			model.addAttribute("latest3MostViewedPostsAtLastMonth", latest3MostViewedPostsAtLastMonth);
			
			// 3 najnovija bez obzira na homepage, za footer:
			List<Post> postsForFooter = postDAO.getLatest3Posts(); 
			model.addAttribute("postsForFooter", postsForFooter);	
						
			
			
			// List<Category> categories = categoryService.getCategoryList();  
			List<Category> categories = categoryService.getCategoryListForMainPage(); // cetiri kategorije
			model.addAttribute("categories", categories);
			
			List<Category> categoriesForFooter = categoryService.getCategoryListForFooter();
			model.addAttribute("categoriesForFooter", categoriesForFooter);
						
			
			//
			model.addAttribute("message", new Message()); 	
			
			
			
			return "contact-form";
		}
		
		
		
		
		@RequestMapping(value="/contact-save") 
		public String getContactSavePage(@Valid @ModelAttribute Message message, BindingResult result, Model model) {
							 
			// validacija:
			if(result.hasErrors()) {				
				
				// widget: latest3MostViewedPostsAtLastMonth:
				List<Post> latest3MostViewedPostsAtLastMonth = postDAO.getLatest3MostViewedPostsAtLastMonth(); 
				model.addAttribute("latest3MostViewedPostsAtLastMonth", latest3MostViewedPostsAtLastMonth);
				
				// 3 najnovija bez obzira na homepage, za footer:
				List<Post> postsForFooter = postDAO.getLatest3Posts(); 
				model.addAttribute("postsForFooter", postsForFooter);	
							
				
				
				// List<Category> categories = categoryService.getCategoryList();  
				List<Category> categories = categoryService.getCategoryListForMainPage(); // cetiri kategorije
				model.addAttribute("categories", categories);
				
				List<Category> categoriesForFooter = categoryService.getCategoryListForFooter();
				model.addAttribute("categoriesForFooter", categoriesForFooter);
							 
				
				return "contact-form";
			}
			 
			
			
			
			messageDAO.saveMessage(message);
			
			return "redirect: contact-form";
		}
		
		
		
		
		  
		
		
		
		
		
		
		
		//--------------- BLOG ------------------------- 
				
		// BLOG-LIST: 
		@RequestMapping(value="/blog-list")
		public String getShopListPage(
				
						@RequestParam(required = false) Integer category,   
						
						@RequestParam(required = false, value="tag") Integer[] tagsArray,  
					
						@RequestParam(required = false) String user,
					
					@RequestParam(required = false) Integer page, // paginacija


				//	@RequestParam(required = false)  String text, // search spojena akcija sa blog-list a ne posebna kao MyShop - proba
					
						Model model) {
		 
			
			// FILTER: 
			List<Post> posts = postDAO.getPostList(category, tagsArray, user); // category je int Id. 
			model.addAttribute("posts", posts);
			 
			
			
			// ostalo:
			List<Post> latest3Posts = postDAO.getLatest3Posts(); 
			model.addAttribute("latest3Posts", latest3Posts);

			// widget: latest3MostViewedPostsAtLastMonth:
			List<Post> latest3MostViewedPostsAtLastMonth = postDAO.getLatest3MostViewedPostsAtLastMonth(); 
			model.addAttribute("latest3MostViewedPostsAtLastMonth", latest3MostViewedPostsAtLastMonth);
			
			// 3 najnovija bez obzira na homepage, za footer:
			List<Post> postsForFooter = postDAO.getLatest3Posts(); // isto kao getLatest12PostsForMainPage samo 3 
			model.addAttribute("postsForFooter", postsForFooter);
			
			
			
			
			
			// kategorije:
			List<Category> categories = categoryService.getCategoryList();
			model.addAttribute("categories", categories);
			//List<Category> categoriesForMainPage = categoryService.getCategoryListForMainPage();
			//model.addAttribute("categories", categories);
			  
			// categories for widget:
			List<Category> categoriesForWidget = categoryService.getCategoryListForWidget();
			model.addAttribute("categoriesForWidget", categoriesForWidget);
			
			// categories for footer:			
			List<Category> categoriesForFooter = categoryService.getCategoryListForFooter();
			model.addAttribute("categoriesForFooter", categoriesForFooter);
			
			
			
			
			
			// za Widget: Vise na vise:			
			List<Tag> tagsOrderedByUsage = tagDAO.getTagsOrderedByUsage();
			model.addAttribute("tagsMostUsed", tagsOrderedByUsage);
		
			  
			
			
			if(category!=null) {
				model.addAttribute("categorySelected",category); // kategoriju koja je izabrana pod drugim nazivom
				model.addAttribute("pageTitle","Category \""+ categoryService.getCategoryById(category).getName()+"\"");  
			} 
			
			
			 
			if(tagsArray!=null) { //  Integer[] tagsArray
				model.addAttribute("tagsSelected", Arrays.asList(tagsArray));  // pretvara u listu
				 
			}
			
			 
			
			if(user!=null) {
				model.addAttribute("userSelected",user);
				model.addAttribute("authorImage", userDAO.getUser(user).getImage());  
				model.addAttribute("pageTitle","Posts by author \""+ userDAO.getUser(user).getName()+"\"");  
			}
			 
			
			
					  // PAGINACIJA pocetak:
					  // List<Post> posts = postDAO.getPostList();				        
				        PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(posts);			        
				        pagedListHolder.setPageSize(12);			      
				        
				        // modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
				        model.addAttribute("maxPages", pagedListHolder.getPageCount());
			 
				        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
			
				        // modelAndView.addObject("page", page);
				        model.addAttribute("page", page);
				        
				        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
				        	
				            pagedListHolder.setPage(0); 
				            model.addAttribute("posts", pagedListHolder.getPageList());
				            
				        }
				        else if(page <= pagedListHolder.getPageCount()) {				        	
				            pagedListHolder.setPage(page-1); 
				            model.addAttribute("posts", pagedListHolder.getPageList());				            
				        }				        
				        
			
			
			return "blog-list";
		}
		
		
		
	
		
		
		
		
		@RequestMapping(value="/blog-search")
		public String getBlogSearchPage(
				
					@RequestParam String text, 
					
					@RequestParam(required = false) Integer page, // paginacija
					
					Model model
					
				) {
			 
			List<Post> posts = postDAO.getPostList(text); 			
			model.addAttribute("posts", posts);
			model.addAttribute("pageTitle", "Search result for \"" + text + "\"");
			
			
			// za Widget latest Posts: 
			// widget: latest3MostViewedPostsAtLastMonth:
			List<Post> latest3MostViewedPostsAtLastMonth = postDAO.getLatest3MostViewedPostsAtLastMonth(); 
			model.addAttribute("latest3MostViewedPostsAtLastMonth", latest3MostViewedPostsAtLastMonth);
			
			// widget Category:
			List<Category> categoriesForWidget = categoryService.getCategoryListForWidget();
			model.addAttribute("categoriesForWidget", categoriesForWidget);
			
			// za Widget Tags: Vise na vise:			
			List<Tag> tagsOrderedByUsage = tagDAO.getTagsOrderedByUsage();
			model.addAttribute("tagsMostUsed", tagsOrderedByUsage);
						
			 
			
			
			// 3 najnovija bez obzira na homepage, za footer:
			List<Post> postsForFooter = postDAO.getLatest3Posts(); // isto kao getLatest12PostsForMainPage samo 3 
			model.addAttribute("postsForFooter", postsForFooter);
			
			
			
			
			  // PAGINACIJA:
			  // List<Post> posts = postDAO.getPostList();				        
		        PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(posts);			        
		        pagedListHolder.setPageSize(12);			      
		        
		        // modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
		        model.addAttribute("maxPages", pagedListHolder.getPageCount());
	 
		        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
	
		        // modelAndView.addObject("page", page);
		        model.addAttribute("page", page);
		        model.addAttribute("text", text);
		        
		        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
		        	
		            pagedListHolder.setPage(0); 
		            model.addAttribute("posts", pagedListHolder.getPageList());
		            
		        }
		        else if(page <= pagedListHolder.getPageCount()) {				        	
		            pagedListHolder.setPage(page-1); 
		            model.addAttribute("posts", pagedListHolder.getPageList());				            
		        }				        
		        
		        
			
			 
			// return "blog-list";
		        return "blog-search";
		}
		
		
		
		
		
		 
		
		
		
		
		 
		// -------------- BLOG-DETAIL:		
		
		@RequestMapping(value="/blog-detail/{title}/{id}")  // za SEO title  
		public String getBlogDetailPage(  				 
				  	
					 @PathVariable int id, 
					 @PathVariable String title,
					 
		  			 Model model
		  			 
				){
			
								 
			Post post = postDAO.getPostById(id);
			model.addAttribute("post", post);				
			
			
			
			// za deo pri dnu koji prikazuje recently viewed, listamo proizvode iz kategorije: 
			List<Post> postsFromCategory = postDAO.getPostList(post.getCategory().getId(),null,null); // cat, tags, user 
			model.addAttribute("posts",postsFromCategory);			 
			
			// widget: latest3MostViewedPostsAtLastMonth:
			List<Post> latest3MostViewedPostsAtLastMonth = postDAO.getLatest3MostViewedPostsAtLastMonth(); 
			model.addAttribute("latest3MostViewedPostsAtLastMonth", latest3MostViewedPostsAtLastMonth);
			
			 
			// 3 najnovija bez obzira na homepage, za footer:
			List<Post> postsForFooter = postDAO.getLatest3Posts(); 
			model.addAttribute("postsForFooter", postsForFooter);
			
			
			
			 
			
			
			List<Category> categoriesForWidget = categoryService.getCategoryListForWidget();
			model.addAttribute("categoriesForWidget", categoriesForWidget);
			
			List<Category> categoriesForFooter = categoryService.getCategoryListForFooter();
			model.addAttribute("categoriesForFooter", categoriesForFooter);
			
			
			 
			

			List<Tag> tagsOrderedByUsage = tagDAO.getTagsOrderedByUsage();
			model.addAttribute("tagsMostUsed", tagsOrderedByUsage);
			
			
			long commentsCount = commentDAO.getCommentsCount(id);
			model.addAttribute("commentsCount", commentsCount);
			
			
			// New Comment: 
			model.addAttribute("comment", new Comment()); 			
			
			
			
			
			Post prevPost = postDAO.getPreviousPostById(id);
			model.addAttribute("previousPost", prevPost);	
			
			Post nextPost = postDAO.getNextPostById(id);
			model.addAttribute("nextPost", nextPost);	
			 
			postDAO.incrementViews(id);
 
			
		        
			
			return "blog-detail";
		}
 	
		
	
		

		
		 
		@RequestMapping(value="/blog-category/{name}/{id}")  // za SEO title 
		public String getBlogsCategoryPage(  				 
				 	
					 @PathVariable int id, 
					 @PathVariable String name,  // categoryName
					  
						@RequestParam(required = false) Integer page, // paginacija
							
						
		  			 Model model
		  			 
				){
			 
	
			// po kategoriji:
			List<Post> posts = postDAO.getPostListByCategoryId(id);
			model.addAttribute("posts", posts);				
			model.addAttribute("pageTitle","Category \""+name+"\"");  
			
			
			// tri posta za Widget: 
			// widget: latest3MostViewedPostsAtLastMonth:
			List<Post> latest3MostViewedPostsAtLastMonth = postDAO.getLatest3MostViewedPostsAtLastMonth(); 
			model.addAttribute("latest3MostViewedPostsAtLastMonth", latest3MostViewedPostsAtLastMonth);
			// kategorije za widget:
			List<Category> categoriesForWidget = categoryService.getCategoryListForWidget();
			model.addAttribute("categoriesForWidget", categoriesForWidget);
			// tagovi za Widget: Vise na vise:			
			List<Tag> tagsOrderedByUsage = tagDAO.getTagsOrderedByUsage();
			model.addAttribute("tagsMostUsed", tagsOrderedByUsage);
						
			
			
			// 3 najnovija bez obzira na homepage, za footer:
			List<Post> postsForFooter = postDAO.getLatest3Posts(); 
			model.addAttribute("postsForFooter", postsForFooter);
			 
			List<Category> categoriesForFooter = categoryService.getCategoryListForFooter();
			model.addAttribute("categoriesForFooter", categoriesForFooter);
			
			
			 
			
			 
			
			
			
			  // PAGINACIJA pocetak:
			  // List<Post> posts = postDAO.getPostList();				        
		        PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(posts);			        
		        pagedListHolder.setPageSize(12);			      
		        
		        // modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
		        model.addAttribute("maxPages", pagedListHolder.getPageCount());
	 
		        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
	
		        // modelAndView.addObject("page", page);
		        model.addAttribute("page", page);
		        
		        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
		        	
		            pagedListHolder.setPage(0); 
		            model.addAttribute("posts", pagedListHolder.getPageList());
		            
		        }
		        else if(page <= pagedListHolder.getPageCount()) {				        	
		            pagedListHolder.setPage(page-1); 
		            model.addAttribute("posts", pagedListHolder.getPageList());				            
		        }				        
		       
			
			
			return "blog-list";
		}
 	
		
		
		
		 
				@RequestMapping(value="/blog-user/{username}")  // za SEO title 
				public String getBlogsUserPage(  				 
						 
							 @PathVariable String username,   
							  
								@RequestParam(required = false) Integer page, // paginacija
									
								
				  			 Model model
				  			 
						){
					 
			
					// po kategoriji:
					List<Post> posts = postDAO.getPostListByUserName(username);
					model.addAttribute("posts", posts);				
					model.addAttribute("pageTitle","User \""+username+"\"");  
					
					model.addAttribute("authorImage",userDAO.getUser(username).getImage());
					
					
					
					// tri posta za Widget: 
					// widget: latest3MostViewedPostsAtLastMonth:
					List<Post> latest3MostViewedPostsAtLastMonth = postDAO.getLatest3MostViewedPostsAtLastMonth(); 
					model.addAttribute("latest3MostViewedPostsAtLastMonth", latest3MostViewedPostsAtLastMonth);
					// kategorije za widget:
					List<Category> categoriesForWidget = categoryService.getCategoryListForWidget();
					model.addAttribute("categoriesForWidget", categoriesForWidget);
					// tagovi za Widget: Vise na vise:			
					List<Tag> tagsOrderedByUsage = tagDAO.getTagsOrderedByUsage();
					model.addAttribute("tagsMostUsed", tagsOrderedByUsage);
								
					
					
					// 3 najnovija bez obzira na homepage, za footer:
					List<Post> postsForFooter = postDAO.getLatest3Posts(); 
					model.addAttribute("postsForFooter", postsForFooter);
					 
					List<Category> categoriesForFooter = categoryService.getCategoryListForFooter();
					model.addAttribute("categoriesForFooter", categoriesForFooter);
					
					
					  
					 
					
					
					  // PAGINACIJA pocetak:
					  // List<Post> posts = postDAO.getPostList();				        
				        PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(posts);			        
				        pagedListHolder.setPageSize(12);			      
				        
				        // modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
				        model.addAttribute("maxPages", pagedListHolder.getPageCount());
			 
				        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
			
				        // modelAndView.addObject("page", page);
				        model.addAttribute("page", page);
				        
				        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
				        	
				            pagedListHolder.setPage(0); 
				            model.addAttribute("posts", pagedListHolder.getPageList());
				            
				        }
				        else if(page <= pagedListHolder.getPageCount()) {				        	
				            pagedListHolder.setPage(page-1); 
				            model.addAttribute("posts", pagedListHolder.getPageList());				            
				        }				        
				        
					
					
					return "blog-list";
				}
		 	
		
		
		
		
 
						@RequestMapping(value="/blog-tag/{title}/{id}")  // za SEO title 
						public String getBlogsTagPage(  				 
								 	
									 @PathVariable int id, 
									 @PathVariable String title,   
									  
										@RequestParam(required = false) Integer page, // paginacija
											
										
						  			 Model model
						  			 
								){
							
											 
							// po kategoriji:
							List<Post> posts = postDAO.getPostListByTagId(id);
							model.addAttribute("posts", posts);				
							model.addAttribute("pageTitle","Tag \""+title+"\"");  
							
							
							
							// tri posta za Widget: 
							// widget: latest3MostViewedPostsAtLastMonth:
							List<Post> latest3MostViewedPostsAtLastMonth = postDAO.getLatest3MostViewedPostsAtLastMonth(); 
							model.addAttribute("latest3MostViewedPostsAtLastMonth", latest3MostViewedPostsAtLastMonth);
							// kategorije za widget:
							List<Category> categoriesForWidget = categoryService.getCategoryListForWidget();
							model.addAttribute("categoriesForWidget", categoriesForWidget);
							// tagovi za Widget: Vise na vise:			
							List<Tag> tagsOrderedByUsage = tagDAO.getTagsOrderedByUsage();
							model.addAttribute("tagsMostUsed", tagsOrderedByUsage);
										
							
							
							// 3 najnovija bez obzira na homepage, za footer:
							List<Post> postsForFooter = postDAO.getLatest3Posts();  
							model.addAttribute("postsForFooter", postsForFooter);
							 
							List<Category> categoriesForFooter = categoryService.getCategoryListForFooter();
							model.addAttribute("categoriesForFooter", categoriesForFooter);
							
							
							 
							 
							
							
							  // PAGINACIJA pocetak:
							  // List<Post> posts = postDAO.getPostList();				        
						        PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(posts);			        
						        pagedListHolder.setPageSize(12);			      
						        
						        // modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
						        model.addAttribute("maxPages", pagedListHolder.getPageCount());
					 
						        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
					
						        // modelAndView.addObject("page", page);
						        model.addAttribute("page", page);
						        
						        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
						        	
						            pagedListHolder.setPage(0); 
						            model.addAttribute("posts", pagedListHolder.getPageList());
						            
						        }
						        else if(page <= pagedListHolder.getPageCount()) {				        	
						            pagedListHolder.setPage(page-1); 
						            model.addAttribute("posts", pagedListHolder.getPageList());				            
						        }				        
						       
							
							
							return "blog-list";
						}
				 	
				
				
		
		
						
		
		// --------------------- COMMENTS -----------------------
		
		@RequestMapping(value="/comment-save")
		public String getCommentSavePage(
				
				
				 @Valid @ModelAttribute Comment comment, 
				    
				 BindingResult result,
				 
				 	Principal principal,
					
					@RequestParam(required = false) Integer id  
					
			, Model model  
				
			
				) {
			
			 
				// validacija:
				if(result.hasErrors()) {
					  
							Post post = postDAO.getPostById(id);
							model.addAttribute("post", post);				
							
							// za deo pri dnu koji prikazuje recently viewed, mi listamo proizvode iz kategorije: 
							List<Post> postsFromCategory = postDAO.getPostList(post.getCategory().getId(),null,null); // cat, tags, user
							// 	getProductList(category,price,tag)  jer nam je to jedina metoda koja vraca proizvode po kateg.
							model.addAttribute("posts",postsFromCategory);			 
							
							// widget: latest3MostViewedPostsAtLastMonth:
							List<Post> latest3MostViewedPostsAtLastMonth = postDAO.getLatest3MostViewedPostsAtLastMonth(); 
							model.addAttribute("latest3MostViewedPostsAtLastMonth", latest3MostViewedPostsAtLastMonth);
							
							 
							// za footer:
							List<Post> postsForFooter = postDAO.getLatest3Posts(); 
							model.addAttribute("postsForFooter", postsForFooter);
							
							
							List<Category> categoriesForWidget = categoryService.getCategoryListForWidget();
							model.addAttribute("categoriesForWidget", categoriesForWidget);
							
							List<Category> categoriesForFooter = categoryService.getCategoryListForFooter();
							model.addAttribute("categoriesForFooter", categoriesForFooter);
							 
		
							List<Tag> tagsOrderedByUsage = tagDAO.getTagsOrderedByUsage();
							model.addAttribute("tagsMostUsed", tagsOrderedByUsage);
							
							
							long commentsCount = commentDAO.getCommentsCount(id);
							model.addAttribute("commentsCount", commentsCount);
							 
							
							Post prevPost = postDAO.getPreviousPostById(id);
							model.addAttribute("previousPost", prevPost);	
							
							Post nextPost = postDAO.getNextPostById(id);
							model.addAttribute("nextPost", nextPost);	
							  
					
					
					 return "blog-detail";					
				}
						
						 
			Comment newComment = new Comment(comment.getUserName(), comment.getUserEmail(), comment.getBody(), true);
			
			newComment.setPost(postDAO.getPostById(id));  
				 
			commentDAO.saveComment(newComment);
			 
			
			return "redirect: /BlogProject/blog-detail/"+postDAO.getPostById(id).getTitle()+"/"+id;
			 
		}

		// drugi nacin:
		//  	 vraca tip klase ModelAndView. Radi isto sto i metoda iznad: getBlogSearchPage
		@RequestMapping(value="/blog-search-test")
		public ModelAndView getShopSearchPageTest(@RequestParam String text) {
			
			List<Post> list = postDAO.getPostList(text);
			
		System.out.println("Lista: " + list.toString());
		
			ModelAndView mv =  new ModelAndView();  
			
			mv.setViewName("blog-search"); 
			
			mv.addObject("posts", list);  
									
			return mv;
			
		}
		 
		
		
		
		
		
		
		// -----------------  REST API: ------------------ 
		
		@RequestMapping(value="/blog-search-text")
		@ResponseBody 
		public String getBlogSearchPageText(@RequestParam String text) { 
			  
			 List<Post> list = postDAO.getPostList(text);
			  
			 return list.toString();  
			 
		}
		
		
		
}
