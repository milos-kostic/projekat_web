package cubes.main.rest.request_body;


public class PostsFilter {

	// u ProductDAOImpl: 
	//  public List<Product> getProductList(Integer category, Integer price, Integer[] stickers) {:
	private Integer category;
	private String user;
	private Integer[] tags;
	
	
	 
	
	public Integer getCategory() {
		return category;
	}
	public void setCategory(Integer category) {
		this.category = category;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Integer[] getTags() {
		return tags;
	}
	public void setTags(Integer[] tags) {
		this.tags = tags;
	}
	
	
}
