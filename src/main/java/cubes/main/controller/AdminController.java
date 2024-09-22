package cubes.main.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import cubes.main.dao.CategoryDAO;
import cubes.main.dao.CommentDAO;
import cubes.main.dao.MessageDAO;
import cubes.main.dao.PostDAO;
import cubes.main.dao.RoleDAO;
import cubes.main.dao.SliderDAO; 
import cubes.main.dao.TagDAO;
import cubes.main.dao.UserDAO;
import cubes.main.entity.Category;
import cubes.main.entity.ChangePassword;
import cubes.main.entity.Comment;
import cubes.main.entity.Message;
import cubes.main.entity.Post;
import cubes.main.entity.Role;
import cubes.main.entity.Slider;
import cubes.main.entity.Tag;
import cubes.main.entity.User;
import cubes.main.service.CategoryService;

@Controller
@RequestMapping(value="/admin")
public class AdminController {

//	@Autowired
//	private CategoryDAO categoryDAO;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private TagDAO tagDAO;
	@Autowired
	private PostDAO postDAO;
	@Autowired
	private MessageDAO messageDAO;
	@Autowired
	private SliderDAO sliderDAO;
	 
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RoleDAO roleDAO;
	@Autowired
	private CommentDAO commentDAO;
	
	
//	@Autowired	
//	ServletContext servletContext;
	
	
	
	
	
	// ------------------------ DASHBOARD --------------------------------------
		@RequestMapping(value={"/","dashboard"})
		public String getDashBoardPage(Principal principal, Model model) {  
			
			Boolean isAdmin = userDAO.getUser(principal.getName()).isAdmin();
			model.addAttribute("isAdmin", isAdmin);
			 
				long unreadMessagesCout= messageDAO.getUnreadMessagesCount();			
				model.addAttribute("unreadMessagesCount",unreadMessagesCout);
				
				List<Post> latest3Posts= postDAO.getLatest3Posts();
				model.addAttribute("latest3Posts", latest3Posts);
		
				
				
				
				// ako je admin sve, ako je bloger onda samo svoje posove:
				User loggedUser = userDAO.getUser(principal.getName());
				long unreadCommentsCount=0;				
				List<Post> mostViewed3Posts = null; 
			
				if(loggedUser.isAdmin()) {
					// admin:
					 unreadCommentsCount= commentDAO.getUnreadCommentsCount(); 	// broj komentara oznacenih kao neprocitani:
					 mostViewed3Posts = postDAO.getMostViewed3Posts();  
				}else {
					 // bloger:
					 unreadCommentsCount= commentDAO.getUnreadBloggerCommentsCount(loggedUser.getUsername());	
					 mostViewed3Posts = postDAO.getMostViewed3Posts(loggedUser.getUsername());  
				}
				model.addAttribute("unreadCommentsCount", unreadCommentsCount);
				model.addAttribute("mostViewed3Posts", mostViewed3Posts);
				
				 
				model.addAttribute("loggedUser",loggedUser);
				
				
			return "dashboard";
		}
		
		
		
		
		
	// ------------------------- CATEGORY ----------------------------- 
	
	@RequestMapping(value="/category-list")
	public String getCategoryList(Principal principal, Model model) { 
		 
		List<Category> list = categoryService.getCategoryList();		
		model.addAttribute("categories", list);

		 
			//  
			User loggedUser = userDAO.getUser(principal.getName()); 		
			Boolean isAdmin = loggedUser.isAdmin();
			model.addAttribute("isAdmin", isAdmin);
			model.addAttribute("loggedUser", loggedUser);
		
		return "category-list";
	}
	
	
	
	@RequestMapping(value="/category-form")
	public String getCategoryForm(Principal principal, Model model) { 
		 
		model.addAttribute("category", new Category());  
		 
			//  
			User loggedUser = userDAO.getUser(principal.getName()); 		
			Boolean isAdmin = loggedUser.isAdmin();
			model.addAttribute("isAdmin", isAdmin);
			model.addAttribute("loggedUser", loggedUser);
		
		return "category-form";
	}
	
	
	
	@RequestMapping(value="/category-save")
	public String getCategorySave(
			
			Principal principal, 			
			@Valid @ModelAttribute Category category,
			BindingResult result,
			Model model
			
			) { 
		
		
		
			
					// validacija:
					if(result.hasErrors()) {	 
						// 
						User loggedUser = userDAO.getUser(principal.getName()); 		
						Boolean isAdmin = loggedUser.isAdmin();
						model.addAttribute("isAdmin", isAdmin);		
						model.addAttribute("loggedUser",loggedUser);
				  
					return "category-form";
					}
				 
		
		 
 

       
		categoryService.saveCategory(category);
		
		
		
//		if(userDAO.getUser(principal.getName()).isAdmin()) {
//			return "redirect: category-list";
//		}else {
//			return "redirect: category-form";
//		}
		
		
		
		  return "redirect: category-list"; 
	}
	
	 
	
	
	
	
	
	@RequestMapping(value="/category-update")
	public String getCategoryUpdate(Principal principal, @RequestParam int id, Model model) {  
		
		Category category = categoryService.getCategoryById(id);
		
		model.addAttribute("category", category);
		 
		
		model.addAttribute("isAdmin", userDAO.getUser(principal.getName()).isAdmin()); 
	  
		
		return "category-form"; 
	}
	 
			 
	
	
	
	
	
	
	
	@RequestMapping(value="/category-delete")
	public String getCategoryDelete(@RequestParam int id) {  
		 
		categoryService.deleteCategory(id);
		
		return "redirect: category-list"; 
	}
	 
	
	
	
	
	
	// ------------------------------ TAG --------------------------- 
	
	@RequestMapping(value="/tag-list")
	public String getTagList(Principal principal, Model model) {
	 
		
		List<Tag> list = tagDAO.getTagList();		
		model.addAttribute("tags", list);
		 
			//  
			User user = userDAO.getUser(principal.getName()); 		
			Boolean isAdmin = user.isAdmin();
			model.addAttribute("isAdmin", isAdmin);		
			model.addAttribute("loggedUser",user);
		
		
		return "tag-list";
	}
	
	
	@RequestMapping(value="/tag-form")
	public String getTagForm(Principal principal, Model model) { 
		 
		model.addAttribute("tag",new Tag());  
		 
				//  
				User user = userDAO.getUser(principal.getName()); 		
				Boolean isAdmin = user.isAdmin();
				model.addAttribute("isAdmin", isAdmin);		
				model.addAttribute("loggedUser",user);
				
				
		return "tag-form";
	}
	
	
	@RequestMapping(value="/tag-save")
	public String getTagSave(
			
			Principal principal, 	
			@Valid @ModelAttribute Tag tag,
			BindingResult result,
			Model model
			
			) { 
		
		
						// validacija:
						if(result.hasErrors()) {						
							
									 
								//  
								User loggedUser = userDAO.getUser(principal.getName()); 		
								Boolean isAdmin = loggedUser.isAdmin();
								model.addAttribute("isAdmin", isAdmin);		
								model.addAttribute("loggedUser",loggedUser);
				 
							// ----------- 					
							return "tag-form";
						}
						 
 
						
		
		tagDAO.saveTag(tag);
		
		return "redirect: tag-list"; 
	} 
	
	
	@RequestMapping(value="/tag-update")
	public String getTagUpdate(@RequestParam int id, Model model) {  
		
		Tag tag = tagDAO.getTagById(id);
		
		model.addAttribute("tag", tag);
	 
		return "tag-form"; 
	}
	
	@RequestMapping(value="/tag-delete")
	public String getTagDelete(@RequestParam int id) {  
		 
		tagDAO.deleteTag(id);
		
		return "redirect: tag-list"; 
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// ---------------------------- POST ---------------------- 
	
	
	@RequestMapping(value="/post-list")  // sve
	public String getPostList(
			
			Principal principal, 
			
			@RequestParam(required = false) Integer page, // paginacija
			
			Model model
			
			) {
			
		
		
		List<Post> list;
		
		User user = userDAO.getUser(principal.getName()); 		
		Boolean isAdmin = user.isAdmin();
		
		if(isAdmin) {
			list = postDAO.getPostListOrdered();		  // admin vidi sve
		}else {
			list = postDAO.getPostListByUser(user.getUsername());	// bloger samo svoje postove
		}
		
		
		model.addAttribute("posts", list);
		model.addAttribute("isAdmin", isAdmin);
		
		 
		
		// Pretraga:
		// sve kategorije saljem za padajucu listu pretrage:
		List<Category> allCategories = categoryService.getCategoryList();
		model.addAttribute("categories", allCategories);		
		
		// svi korisnici, lista za pretragu:
		List<User> allUsers = userDAO.getAllUsers();
		model.addAttribute("allUsers", allUsers);		
				
		 	 
		
				//    <> principal
				model.addAttribute("loggedUser",user);
				
				
		
				
				
				

				
				// PAGINACIJA:
				   // List<Post> posts = postDAO.getPostList();				        
			        PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(list);			        
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
			 
	         
			        
			        
		  return "post-list"; 
	}
	
	
	
	
	
	@RequestMapping(value="/post-search")
	public String getPostSearchPage(
			
			@RequestParam(required = false) String title,  // po Naslovu
			
			@RequestParam(required = false) Integer categoryId,  // Kategoriji, id (moze i String name)
			 
			@RequestParam(required = false) String username, // Korisniku
			
			@RequestParam(required = false) Boolean status, // Status: enabled/disabled 
		
			
			//	@RequestParam(required = false, value="tag") Integer[] tagsArray, // value -pod kojim nazivom vodi niz (?)
			
		@RequestParam(required = false) Integer page, // paginacija
		 
		//	@RequestParam String text, //   sve u jednom stringu  
		  
		    Principal principal, 
		    
			Model model
			
			) {
		 
		
		List<Post> posts = null;
		
		
		// admin vidi sve, bloger samo svoje postove, nema parametar username:		
		User loggedUser = userDAO.getUser(principal.getName()); 		
		Boolean isAdmin = loggedUser.isAdmin();		
		if(isAdmin) {
			// metoda koja radi celu pretragu: 
			posts = postDAO.getPostList(title,categoryId,username,status); 			 // admin  
		}else {
			posts = postDAO.getPostList(title,categoryId,loggedUser.getUsername(),status); 		// bloger  
		}   
		model.addAttribute("posts", posts);
		
		
		
		// opciono, za ispis poruke:
		String pageMessage="";
		if(title!=null || categoryId!=null || username!=null || status!=null) {  
			pageMessage="Search result: ";
		}		
		model.addAttribute("searchTerm", pageMessage);
		
		
		
		// Pretraga:
		// sve kategorije saljem za padajucu listu za pretragu:
		List<Category> allCategories = categoryService.getCategoryList();
		model.addAttribute("categories", allCategories);	
		
		// svi korisnici, lista za pretragu:
		List<User> allUsers = userDAO.getAllUsers();
		model.addAttribute("allUsers", allUsers);	
				
		 
				//  
				model.addAttribute("isAdmin", isAdmin);		
				model.addAttribute("loggedUser",loggedUser);
				
				 
				
				
					// PAGINACIJA pocetak:
					  // List<Post> posts = postDAO.getPostList();				        
				        PagedListHolder<Post> pagedListHolder = new PagedListHolder<>(posts);			        
				        pagedListHolder.setPageSize(12);			      
				        
				        // modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
				        model.addAttribute("maxPages", pagedListHolder.getPageCount());
			 
				        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
			
				        // modelAndView.addObject("page", page);
				        model.addAttribute("title", title);
				        model.addAttribute("categoryId", categoryId);
				        model.addAttribute("username", username);
				        model.addAttribute("status", status);
				        model.addAttribute("page", page); // paginacija
				        
				        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
				        	
				            pagedListHolder.setPage(0); 
				            model.addAttribute("posts", pagedListHolder.getPageList());
				            
				        }
				        else if(page <= pagedListHolder.getPageCount()) {				        	
				            pagedListHolder.setPage(page-1); 
				            model.addAttribute("posts", pagedListHolder.getPageList());				            
				        }				        
				    
				
		 
		// return "post-list";
	    return "post-search";
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="post-form")
	public String getPostForm(Principal principal, Model model) { 
		 
		List<Category> categories = categoryService.getCategoryList();
		List<Tag> tags = tagDAO.getTagList();
	 
		model.addAttribute("post",new Post());  
		model.addAttribute("categories", categories); 
		model.addAttribute("tags", tags); 
		 
		model.addAttribute("user", userDAO.getUser(principal.getName()));
		
		    // 
			User loggedUser = userDAO.getUser(principal.getName()); 		
			Boolean isAdmin = loggedUser.isAdmin();
			model.addAttribute("isAdmin", isAdmin);		
			model.addAttribute("loggedUser",loggedUser);
		
		
		return "post-form";
	}


	
	
	
	
	
	@RequestMapping(value="/post-save") 
	public String getPostSave(
			
			Principal principal, 
			@Valid @ModelAttribute Post post, 
			BindingResult result 
						
		, Model model  
			
			) {
		
					// validacija:
					if(result.hasErrors()) {
						 
									List<Category> categories = categoryService.getCategoryList();
									List<Tag> tags = tagDAO.getTagList();
								  
									model.addAttribute("categories", categories); 
									model.addAttribute("tags", tags); 
									 
									model.addAttribute("user", userDAO.getUser(principal.getName()));
									
									    //  
										User loggedUser = userDAO.getUser(principal.getName()); 		
										Boolean isAdmin = loggedUser.isAdmin();
										model.addAttribute("isAdmin", isAdmin);		
										model.addAttribute("loggedUser",loggedUser);					
								
										
						return "post-form";
					}
					
								
					
					
		Category category = categoryService.getCategoryById(post.getCategory().getId());
		
		List<Tag> tags = new ArrayList<Tag>();		
		for(Tag tag : post.getTags()) {			
			Tag tempTag = tagDAO.getTagById(tag.getId());
			tags.add(tempTag);			
		}
				
		post.setCategory(category);		
		post.setTags(tags);			
		post.setUser(userDAO.getUser(principal.getName()));
	
		postDAO.savePost(post);
		
		  
					
		return "redirect: post-list"; 
	}
	
	
	
	
	
	@RequestMapping(value="/post-update")
	public String getPostUpdate(@RequestParam int id, Model model) {  
		
		Post post = postDAO.getPostById(id);	
		List<Category> categories = categoryService.getCategoryList();
		List<Tag> tags = tagDAO.getTagList();
		
		model.addAttribute("post", post);
		model.addAttribute("categories", categories);
		model.addAttribute("tags", tags);
		 
		return "post-form"; 
	}
	
	
	
	
	
	@RequestMapping(value="post-enabled")
	public String getPostEnabled(
			  
			@RequestParam int id 
			
			){ 
		 
		Post post = postDAO.getPostById(id);  
		
		post.setEnabled(!post.getEnabled()); // postavi stanje suprotno od postojeceg
		   
		postDAO.savePost(post);
		
		return "redirect: post-list";
	}
	
	
	
	
	@RequestMapping(value="/post-delete")
	public String getPostDelete(@RequestParam int id) {  
		 
		postDAO.deletePost(id);
		
		return "redirect: post-list"; 
	}
	
	
	
	@RequestMapping(value="post-detail")
	public String getPostDetail(@RequestParam int id, Model model) {  
		 
		Post p = postDAO.getPostById(id);
		model.addAttribute("post",p);
		 
		return "post-detail"; 
	}


	

	
	
	
	
	
	
	
	
	
	
	
	// ----------------- MESSAGES -------------------- 
	
	
	@RequestMapping(value="message-list")
	public String getMessageList(
			
			Principal principal, 
			
			@RequestParam(required = false) Integer page, // paginacija
			
			Model model) {
		
		List<Message> list = messageDAO.getAllMessages();
		model.addAttribute("messages", list); 
					 
			//  
			User loggedUser = userDAO.getUser(principal.getName()); 		
			Boolean isAdmin = loggedUser.isAdmin();
			model.addAttribute("isAdmin", isAdmin);		
			model.addAttribute("loggedUser",loggedUser);
		
			
			

			
			// PAGINACIJA:
			  // List<Post> posts = postDAO.getPostList();				        
		        PagedListHolder<Message> pagedListHolder = new PagedListHolder<>(list);			        
		        pagedListHolder.setPageSize(12);			      
		        
		        // modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
		        model.addAttribute("maxPages", pagedListHolder.getPageCount());
	 
		        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
	
		        // modelAndView.addObject("page", page);
		        model.addAttribute("page", page);
		        
		        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
		        	
		            pagedListHolder.setPage(0); 
		            model.addAttribute("messages", pagedListHolder.getPageList());
		            
		        }
		        else if(page <= pagedListHolder.getPageCount()) {				        	
		            pagedListHolder.setPage(page-1); 
		            model.addAttribute("messages", pagedListHolder.getPageList());				            
		        }				        
		  
		        
		        
			 
			
			
		return "message-list";  
	}
		
	
	@RequestMapping(value="message-seen")
	public String getMessageSeenPage(@RequestParam int id) {

		Message message = messageDAO.getMessageById(id);  
		message.setIsSeen(true);
		
		messageDAO.saveMessage(message);
		
		return "redirect: message-list";  
	}
	
	
	
	
	
	// ------------------- SLIDER ------------------------------ 
	
	@RequestMapping(value="slider-list")
	public String getSliderList(Principal principal, Model model) {  
		
		model.addAttribute("sliders", sliderDAO.getSliderList());
		
		
					//  
					User loggedUser = userDAO.getUser(principal.getName()); 		
					Boolean isAdmin = loggedUser.isAdmin();
					model.addAttribute("isAdmin", isAdmin);		
					model.addAttribute("loggedUser",loggedUser);
				
					
					
		return "slider-list";
	}
	
	
	@RequestMapping(value="slider-form")
	public String getSliderForm(Principal principal, Model model) { 
		
		model.addAttribute("slider",new Slider());
	  
			//  
			User loggedUser = userDAO.getUser(principal.getName()); 		
			Boolean isAdmin = loggedUser.isAdmin();
			model.addAttribute("isAdmin", isAdmin);		
			model.addAttribute("loggedUser",loggedUser);
	
		
		return "slider-form";
	}
	
	
	@RequestMapping(value="slider-update")
	public String getSliderUpdate(@RequestParam int id, Model model) { 
		
		Slider s = sliderDAO.getSliderById(id);
		
		model.addAttribute("slider",s);
		
		return "slider-form"; // vracam u istu stranicu
	}
	
	
	@RequestMapping(value="slider-save")
	public String getSliderSave(
			
			Principal principal, 
			@Valid @ModelAttribute Slider slider,
			BindingResult result,
			Model model
			
			) {
		
		
			
					// validacija:
					if(result.hasErrors()) {						
						  
									//  
									User loggedUser = userDAO.getUser(principal.getName()); 		
									Boolean isAdmin = loggedUser.isAdmin();
									model.addAttribute("isAdmin", isAdmin);		
									model.addAttribute("loggedUser",loggedUser);
					  				
						return "slider-form";
					}
					 
			 
					
					
					
		sliderDAO.saveSlider(slider);
		
		return "redirect: slider-list";
	}
	
  

        
	
	 
	
	
	
	@RequestMapping(value="slider-delete")
	public String getDeleteSlider(@RequestParam int id) {
		
		sliderDAO.deleteSlider(id);
		
		return "redirect: slider-list";
	}
	
	
	  
	
	

	
	// ----------------- USERS PAGES --------------------------------- 
		
	@RequestMapping(value="user-list")
	public String getUserListPage(Principal principal, Model model) { 
		
		model.addAttribute("users", userDAO.getAllUsers());
		
		
			//  
			User loggedUser = userDAO.getUser(principal.getName()); 		
			Boolean isAdmin = loggedUser.isAdmin();
			model.addAttribute("isAdmin", isAdmin);		
			model.addAttribute("loggedUser",loggedUser);
			
		
		return "user-list";			
	}
	
	@RequestMapping(value="user-enabled")
	public String getUserEnabled(@RequestParam String username){ // username je id korisnika 		
		User user = userDAO.getUser(username);  	
		user.setEnabled(!user.getEnabled()); 
		userDAO.saveUser(user);		
		return "redirect: user-list";
	}
	
	
	
	@RequestMapping(value="user-delete")
	public String getUserDelete(@RequestParam String username) {		
		userDAO.deleteUser(username);		
		return "redirect: user-list";
	}
	
	
	
	
	
	
	
	@RequestMapping(value="user-form")
	public String getUserForm(Principal principal, Model model) { 		
		
		User user = new User();		
		model.addAttribute("user", user); 
		model.addAttribute("roles", userDAO.getUserRoles());  
		
					//  
					User loggedUser = userDAO.getUser(principal.getName()); 		
					Boolean isAdmin = loggedUser.isAdmin();
					model.addAttribute("isAdmin", isAdmin);		
					model.addAttribute("loggedUser",loggedUser);
					
					
		return "user-form";
	}
	
	
	
	
	
	
	
	
	
	@RequestMapping(value="user-save")
	public String getUserSave(
				
				Principal principal, 
				
				@Valid @ModelAttribute User user,
				
				BindingResult result 
				
				, Model model  
				
				, @RequestParam(required = false) String photo  // Upload
			
			) {
		
		 
					
					// validacija:
					if(result.hasErrors()) {						
						  
						List<Role> roles = roleDAO.getRoleList();
						model.addAttribute("userRoles", roles);
						User loggedUser = userDAO.getUser(principal.getName()); 		
						Boolean isAdmin = loggedUser.isAdmin();
						model.addAttribute("isAdmin", isAdmin);		
						model.addAttribute("loggedUser",loggedUser);
								 	 				
						return "user-form-update";
					}
				 
			
					
			// Status:
			user.setEnabled(true);  
			

			// default role:
			if(user.getRoles().isEmpty() || user.getRoles().size()==0) {		
				user.addRole(roleDAO.getRoleByAuthority("ROLE_blogger"));
			}
		
			
			
			// Kriptovanje Lozinke: 
			try { 
				user.generateBCryptPassword(); // pretvaramo string poslat sa forme - u enkriptovan niz 
			}catch (Exception e) {
				System.out.println("\n *** Greska: "+e);
			}
			
			
			 
			
			// Role:
			List<Role> roles = new ArrayList<Role>();		
			for(Role role : user.getRoles()) {			
				Role tempRole = roleDAO.getRoleByAuthority(role.getAuthority());
				roles.add(tempRole);			
			}		
			user.setRoles(roles);	
			
			 
			userDAO.saveUser(user);
			
			
			if(userDAO.getUser(principal.getName()).isAdmin()) {
				return "redirect: user-list";
			}else {
				return "redirect: user-myupdate";
			}
				
	}
	
	
	
	
	
	
	
	
	@RequestMapping(value="user-update")
	public String getUserUpdate(
			
				Principal principal, 
				
				@RequestParam String username, 
				 
				Model model
				
		 
			) {
					// username korisnik ne menja 
		
		 
						
				model.addAttribute("user",  userDAO.getUser(username));		
				
				model.addAttribute("userRoles", userDAO.getUserRoles());// da moze da menja rolu		
				
				model.addAttribute("isAdmin", userDAO.getUser(principal.getName()).isAdmin()); 
			
				    
		return "user-form-update";   
	}
			 
	
	
	
	
	
	
	
	@RequestMapping(value="user-myupdate") //  YourProfile
	public String getUserMyUpdate(Principal principal, Model model) {
				 
		model.addAttribute("user", userDAO.getUser(principal.getName()));  
		model.addAttribute("isAdmin", userDAO.getUser(principal.getName()).isAdmin());  
		
		return "user-form-update"; 
	}
	
	
	
	
	
	
	
	@RequestMapping(value="user-change-password")
	public String getChangePasswordPage(Principal principal, Model model) { // lozinku menja onaj ko je ulogovan.
				 
		model.addAttribute("changePassword", new ChangePassword()); // ChangePassword pravimo obicnu klasu 
		  
			// 
			User loggedUser = userDAO.getUser(principal.getName()); 		
			Boolean isAdmin = loggedUser.isAdmin();
			model.addAttribute("isAdmin", isAdmin);		
			model.addAttribute("loggedUser",loggedUser);
		
		
		return "user-form-change-password";  
	}
	
	
	@RequestMapping(value="user-save-change-password")
	public String getChangePasswordSave(@ModelAttribute ChangePassword changePassword, Principal principal, Model model) {
		   
		User user = userDAO.getUser(principal.getName()); 
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();  
				  
		if(!changePassword.getNewPassword().equalsIgnoreCase(changePassword.getConfirmPassword())) {  
			// ako se ne poklapa lozinka i potvrda:
			model.addAttribute("message", "Nova lozinka i potvrda nove lozinke se ne poklapaju.");
			model.addAttribute("changePassword", changePassword); // saljem lozinku ponovo na stranicu da se ne zagubi
			   
		}else if(!encoder.matches(changePassword.getOldPassword(), user.getEncodedPassword())) { // ako stara lozinka ne odgovara korisniku
				  
			model.addAttribute("message", "Stara lozinka nije odgovarajuca.");
			model.addAttribute("changePassword", changePassword);  
			
		}else { 
			
			// sve je u redu: 
			user.setPassword(changePassword.getNewPassword()); // napravi novi
			user.generateBCryptPassword(); // enkriptuj
			userDAO.saveUser(user); 
			
			model.addAttribute("message", "Uspesno ste promenili lozinku.");
			model.addAttribute("changePassword", new ChangePassword()); // saljem prazan, sve null ako hoce da pravi ponovo
						   
		}
					

		return "user-form-change-password";
		
	}


	
	

	
	
	
	
	
	// -------------------- COMMENTS ------------------- 
	
	
	@RequestMapping(value="comment-list")   
	public String getCommentList(
			
			Principal principal, 
			
			@RequestParam(required = false) Integer page, // paginacija
			
			Model model
			
			) {
		
		
		User loggedUser = userDAO.getUser(principal.getName());		
		List<Comment> comments = null;
		if(loggedUser.isAdmin()) {
			// admin vidi sve:
			comments = commentDAO.getAllComments();	
		}else {
			// bloger vidi samo komentare svojih postova:
			comments = commentDAO.getBloggerCommentList(loggedUser.getUsername());				
		}		
		model.addAttribute("comments", comments);
		
		 
		
			//  
			model.addAttribute("isAdmin", loggedUser.isAdmin());		
			model.addAttribute("loggedUser",loggedUser);
		 

			
			// PAGINACIJA pocetak:
			  // List<Post> posts = postDAO.getPostList();				        
		        PagedListHolder<Comment> pagedListHolder = new PagedListHolder<>(comments);			        
		        pagedListHolder.setPageSize(12);			      
		        
		        // modelAndView.addObject("maxPages", pagedListHolder.getPageCount());
		        model.addAttribute("maxPages", pagedListHolder.getPageCount());
	 
		        if(page==null || page < 1 || page > pagedListHolder.getPageCount())page=1;
	
		        // modelAndView.addObject("page", page);
		        model.addAttribute("page", page);
		        
		        if(page == null || page < 1 || page > pagedListHolder.getPageCount()){
		        	
		            pagedListHolder.setPage(0); 
		            model.addAttribute("messages", pagedListHolder.getPageList());
		            
		        }
		        else if(page <= pagedListHolder.getPageCount()) {				        	
		            pagedListHolder.setPage(page-1); 
		            model.addAttribute("messages", pagedListHolder.getPageList());				            
		        }				        
		 
		         
		        
		
		return "comment-list";
	}
	
	
	
	@RequestMapping(value="comment-enabled")
	public String getCommentEnabled(
			  
			@RequestParam int id 
			
			){ 
		
	// System.out.println(id);
	
		Comment comment = commentDAO.getCommentById(id);  
		
		comment.setEnabled(!comment.getEnabled()); 
		  
		commentDAO.saveComment(comment);
		
		return "redirect: comment-list";
	}
	
	
	
	
	@RequestMapping(value="/comment-search")
	public String getCommentSearchPage(
		 
			@RequestParam(required = false) String postId, // Integer postId,   
			  
			Principal principal, 
			
			Model model) {
		 
		 
		
		
		
		List<Comment> comments=null;
	 	 
	 	if(postId==null || postId.isEmpty()) {
	 		comments = commentDAO.getAllCommentsOrdered(); // sve 	
		}else { 
		 	try {
		 		int postIdParam = Integer.valueOf(postId);
		 		comments = commentDAO.getCommentList(postIdParam); 		
		 	}catch (Exception e) {
		 		 
		 		comments = null;			
			}
		}
	 
	 	 
	 	
	 	
			
		  
		 
		model.addAttribute("comments", comments);
		 
					// 
					User loggedUser = userDAO.getUser(principal.getName()); 		
					Boolean isAdmin = loggedUser.isAdmin();
					model.addAttribute("isAdmin", isAdmin);		
					model.addAttribute("loggedUser",loggedUser);
					
		
		return "comment-list";
		
	}
	
	
	@RequestMapping(value="comment-seen")
	public String getCommentSeenPage(@RequestParam int id) {
 
		Comment comment = commentDAO.getCommentById(id);  
		 
		comment.setIsSeen(!comment.getIsSeen());
		
		commentDAO.saveComment(comment);
		
		return "redirect: comment-list";  
	}
	
	 
	
	
	
	
}
