package cubes.main.controller;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cubes.main.dao.CategoryDAO;
import cubes.main.dao.PostDAO;
import cubes.main.dao.TagDAO;
import cubes.main.entity.Category;
import cubes.main.entity.Post;
import cubes.main.entity.Tag;
import cubes.main.rest.CategoryException;
import cubes.main.rest.MessageResponse;
import cubes.main.rest.TagException;
import cubes.main.rest.request_body.PostsFilter;
import cubes.main.rest.response.BasicPostResponse;
import cubes.main.rest.response.SettingResponse;
//import cubes.main.rest.request_body.ProductFilter;
//import cubes.main.rest.response.BasicProductResponse;
//import cubes.main.rest.response.SettingResponse;
import cubes.main.service.CategoryService;


@RestController
@RequestMapping(value="/api")
public class MyRestController {

	@Autowired 
	private CategoryService categoryService;

	@Autowired
	private TagDAO tagDAO;
	
	@Autowired
	private PostDAO postDAO;
	
	
	
	
	@RequestMapping(value="/test")
	public String testMethod() {		
		return "Cubes";
	}
	
	
	
	
	
	
	// ------------------  CATEGORIES  ---------------------- 
	
	@Transactional 
	@GetMapping(value="/categories")
	public List<Category> getCategories(){	
		  
		return categoryService.getCategoryList(); // list;
	}
	
	
	
	@GetMapping(value="/categories/{id}") // primer poziva:   http://localhost:8080/BlogProject/api/categories/2
	public Category getCategoryById(@PathVariable int id) { // da vrati jednu kategoriju
		
	System.out.println("id: "+id);	
		 
		Category cat = categoryService.getCategoryById(id);  
		if(cat==null) {
			throw new CategoryException("Kategorija sa id: "+id+" ne postoji u bazi podataka."); // RuntimeException();
		}
		 
		return  categoryService.getCategoryById(id);  
	}
	
	
	
	
	
	
	
	 
	@PostMapping(value="/categories")    
	public MessageResponse createCategory(    
				
				@RequestBody Category category			// trazi da se prosledi kategorija je u RequestBody u formatu JSON	`
				
							// testiran primer:
							//
							//		{
							//		    "name": "Test categ",
							//		     "image": "https://footwearnews.com/wp-content/uploads/2022/06/standing-shoes.jpg?w=700&h=437&crop=1",
							//		    "homepage": "1",   
							//		     "priority": -1
							//		}
				
			) { 
		
		category.setId(0);  
		categoryService.saveCategory(category);   		
			 
		
		return new MessageResponse(
				HttpStatus.OK.value(), // 200 
				"Uspesno je sacuvana kategorija: "+category,
				System.currentTimeMillis()
				);  
		
	}  
	
	
	
	
	
	 
	@PutMapping(value="/categories")   
	public MessageResponse updateCategory(
			
					@RequestBody Category category
			
			
					//  primer testiran:
			//					{
			//					    "id": 23,
			//					    "name": "Test categ II",
			//					     "image": "https://footwearnews.com/wp-content/uploads/2022/06/standing-shoes.jpg?w=700&h=437&crop=1",
			//					    "homepage": "1",   
			//					     "priority": -1
			//					}
			
			) {
				 
		Category oldCat = categoryService.getCategoryById(category.getId());  
	
		categoryService.saveCategory(category);
		 
		
		return new MessageResponse(
				200,
				"Uspesno je izmenjena kategorija: "+category,
				System.currentTimeMillis()
				);  
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@DeleteMapping(value="/categories/{id}") //  primer testiran: http://localhost:8080/BlogProject/api/categories/22 
	public MessageResponse deleteCategory(@PathVariable int id) {
		
		Category category = categoryService.getCategoryById(id);
		categoryService.deleteCategory(id);
		
		 
		return new MessageResponse(HttpStatus.OK.value(),	
					"Uspesno je izbrisana kategorija: " + category,
					System.currentTimeMillis() 
					);		
		
	}
	
	
	
	
	
	

	
	
	
	
	// ------------------  Tags  ----------------- 
	
	// Get:
	@Transactional   
	@GetMapping(value="/tags")
	public List<Tag> getTags(){	
		
		List<Tag> list = tagDAO.getTagList();	  
	 
				for(Tag t : list) {
					Hibernate.initialize(t.getPosts());
				}
			 
	 
		return   list;  
	}
	
	// Get by Id:
	@GetMapping(value="/tags/{id}")
	public Tag getTagById(@PathVariable int id) {
			  
		Tag tag = tagDAO.getTagById(id);  
		 
		if(tag==null) {
			throw new TagException("Tag sa id: "+id+" ne postoji u bazi podataka.");  
		}
		
		return tagDAO.getTagById(id);
	}
	
	
	
	
	
	
	// Post: 
	@PostMapping(value="/tags")
	public MessageResponse saveTag(
			
			@RequestBody Tag tag
			
			// primer testiran:
//			{ 
//		        "title": "Business",
//		        "color": "#aa26A8"
//		    }
			
			) {
		tag.setId(0); // za svaki slucaj
		tagDAO.saveTag(tag);
		
		return new MessageResponse(HttpStatus.OK.value(), 
				"Uspesno ste sacuvali tag: "+ tag,
				System.currentTimeMillis()
				);
				
	}
	
	// Put, radi:
	@PutMapping(value="/tags")
	public MessageResponse updateTag(
			
			@RequestBody Tag tag
			
			// primer uspesno testiran:
//			 { 
//			     "id": 19,
//			        "title": "Test tag",
//			        "color": "#aa26A8"
//			 }
			
			) { 
		tagDAO.saveTag(tag);
		return new MessageResponse(HttpStatus.OK.value(), 
				"Uspesno ste izmenili tag: "+ tag,
				System.currentTimeMillis()
				);
				
	}
	
	// Delete:  primer poziva: http://localhost:8080/BlogProject/api/tags/19
	@DeleteMapping(value="/tags/{id}")
	public MessageResponse deleteTag(@PathVariable int id) { 
		Tag Tag = tagDAO.getTagById(id);
		tagDAO.deleteTag(id);
		return new MessageResponse(HttpStatus.OK.value(), 
				"Uspesno ste obrisali tag: "+ Tag,
				System.currentTimeMillis()
				);
				
	}
	
	
	
	
	
	
	// ------------------------------- SETTINGS ---------------------------- 
	 
	@GetMapping(value="/settings")
	public  SettingResponse getSettings() {
		 
		SettingResponse settings = new SettingResponse();
		 
		 
		settings.setCategories(categoryService.getCategoryList()); // getCategoriesWithPosts()); 
		 
		settings.setTags(tagDAO.getTagList()); // getTagsWithPosts()); // 
		
		
		
		return  settings;
	}
	
	
	
	// -------------------------- POSTS --------------------------  
	@GetMapping (value="/posts")
	public List<Post> getPosts(){ 
		List<Post> posts = postDAO.getPostListWithTags();
		return posts; 
	}
	 
	@GetMapping(value="/basicposts")  // primer poziva: http://localhost:8080/BlogProject/api/basicposts
	public List<BasicPostResponse> getBasicPosts(){
		
		List<BasicPostResponse> basicsList = new ArrayList<BasicPostResponse>();
		
		List<Post> posts = postDAO.getPostListWithTags();  
		
		for(Post p:posts) {
			basicsList.add(new BasicPostResponse(p));
		}
		
		return basicsList;
	}
	
	
	
	// post 
	@PostMapping(value="/posts")
	public MessageResponse savePost(
			
			@RequestBody Post post
			
			 // primer requesta:   izbacena kategorija i tagovi:
//			{ 
//		        "title": "Test post novi",
//		        "description": "For over 45 years, our mission has been simple: to design, build and support the finest luxury liveaboard sailing yachts, capable of taking you anywhere in the world in comfort and safety. ", 	        
//		        "image": "https://static3.depositphotos.com/1010261/264/i/950/depositphotos_2647262-stock-photo-yachting-2.jpg",
//		        "content": "neki sadrzaj" 
//		    }
			
			// ili ako se doda kategorija: rucno umetnuta iz requesta za kategorije: 
//		    {
//		         "title": "Test post novifgdfgdf",
//				        "description": "For over 45 years, our mission has been simple: to design, build and support the finest luxury liveaboard sailing yachts, capable of taking you anywhere in the world in comfort and safety. ", 	        
//				        "image": "https://static3.depositphotos.com/1010261/264/i/950/depositphotos_2647262-stock-photo-yachting-2.jpg",
//				        "content": "neki sadrzaj",		
//					"category":{
//						"id": 5,
//						"name": "Motor boats",
//						"image": null,
//						"homepage": true
//					  } 
//				    }
			
			// moze i samo sa id za kategoriju: 
			// "category":{
			//      "id": 7
			//  }
			
			
			) {
		
		post.setId(0);  
		postDAO.savePost(post);
		
		return new MessageResponse(
					HttpStatus.OK.value(),
					"Uspesno ste sacuvali post: "+post,
					System.currentTimeMillis()
				);  
		
	}
	
	
	
	
	
	
		// put  
		@PutMapping(value="/posts")
		public MessageResponse updatePost(@RequestBody Post post) {
			 
			postDAO.savePost(post);
			
			return new MessageResponse(
						HttpStatus.OK.value(),
						"Uspesno ste izmenili post: "+post,
						System.currentTimeMillis()
					);  
			
		}
		
		
		
	
		// delete:
		// primer uspesnog requesta: http://localhost:8080/BlogProject/api/posts/57
		@DeleteMapping(value="/posts/{id}")
		public MessageResponse deletePost(@PathVariable int id) {
			 
			Post p= postDAO.getPostById(id);
				
			postDAO.deletePost(id);
			
			return new MessageResponse(
						HttpStatus.OK.value(),
						"Uspesno ste obrisali post: "+p,
						System.currentTimeMillis()
					);  
			
		}
	
		 
		@GetMapping(value="/filter-posts")  
		public List<Post> getPostListFilter(
				
					@RequestBody PostsFilter filter    // nova klasa u paketu /request_body
					
					
					// primer requesta:
//					{ 
//						  "category": 8
//						}
					
					// ili:
//					{  
//						  "tags": [1,2,3] 
//						}
					// ili:
//					{  
//				    "category":2,
//				    "tags": [1,2,3] 
//				  }
					// ili:
//					{  
//				    "category":2,
//				    "tags": [1,2,3] 
//				  }
					
				){ 
			
			List<Post> list = postDAO.getPostList(filter.getCategory(),filter.getTags(), filter.getUser()); //  filter.getPrice(), 
			
			return list;
		}
		
	
	
}
