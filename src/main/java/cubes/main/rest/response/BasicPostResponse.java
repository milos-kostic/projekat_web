package cubes.main.rest.response;

import cubes.main.entity.Post;
//import cubes.main.entity.Product;

//   kad nam treba manje podataka, da ne saljemo klijentu sve informacije:  
public class BasicPostResponse {

	
	// sta zelim da vratim u response kao json:
	private int id;
	private String title;
	private String description;
	private String category; 
	private String image;
	 
	public BasicPostResponse() {
		
	}
	public BasicPostResponse(Post post) {
		this.id = post.getId();
		this.title=post.getTitle();
		this.description = post.getDescription();
		this.category = post.getCategory().getName(); // jer je string;
		this.image=post.getImage();
	 
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	} 
	
	 
	
	
}
