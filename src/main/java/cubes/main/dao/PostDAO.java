package cubes.main.dao;

import java.security.Principal;
import java.util.List;

import cubes.main.entity.Category;
import cubes.main.entity.Post;

public interface PostDAO {
	

	public List<Post> getPostList(); // all
		
	public List<Post> getPostList(Integer category, Integer[] tags, String user); 
	 
	public List<Post> getPostList(String text); // za search
	
	public List<Post> getPostList(String searchTextTitle, Integer searchCategoryId, String searchUser); // za front
	
	// za SEO:
	public List<Post> getPostListByCategoryId(Integer categoryId);
	public List<Post> getPostListByUserName(String  userName);
	public List<Post> getPostListByTagId(Integer tagId);
	
	
	// Rest api:
	public List<Post> getPostListWithTags();
	
	
	public List<Post> getPostListOrdered();
	
	// admin:
	public List<Post> getPostList(String searchTextTitle, String searchTextCategory); // za admin, search  
	public List<Post> getPostList(String searchTextPostTitle , Integer category,  String user, Boolean status);  
	public List<Post> getPostListByUser(String username);
	
	
	public List<Post> getPostListForMainPage(); // latest 3 
	
	public List<Post> getLatest12PostsForMainPage();
	
	public List<Post> getLatest3Posts();  
	 
		
	public List<Post> getLatest3MostViewedPostsAtLastMonth(); // za Widget desno 
	
	public List<Post> getMostViewed3Posts(); // za admin dashboard
	public List<Post> getMostViewed3Posts(String username); // za admin dashboard, bloger vidi samo svoje postove
	
	 
	public Post getPreviousPostById(int id);
	public Post getNextPostById(int id);	
	public Post getPostById(int id);
	
	
	
	
	public void savePost(Post post);
	
	public void deletePost(int id);
	
	
	public void  incrementViews(int id);
	
}
