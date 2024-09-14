package cubes.main.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.Hibernate;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name="categories")
public class Category {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	@Size(min=5, max=150, message="Polje Name je obavezno, 5 do 150 znakova.")
	@NotNull
	private String name;
	@Column
	@Size(min=5, max=250, message="Polje Image je obavezno, 5 do 250 znakova.")
	@NotNull
	private String image;
	@Column 
	private Boolean homepage;
	@Column 
	private int priority;
	
	
	//  Posts:
	@OneToMany( 
	 
			cascade= {
				CascadeType.DETACH,
				CascadeType.MERGE,
				CascadeType.PERSIST,
				CascadeType.REFRESH
		}
			
		, fetch = FetchType.EAGER   
			
			)	  
	 @JoinColumn(name="category_id") 	
	// 	@LazyCollection(LazyCollectionOption.EXTRA) // da omoguci .size() na LAZY	
	 private List<Post> posts;
	
	
	
	
	
	public Category() {
		
	}
	public Category(String name) {
		this.name = name;
	}
	
	
	
	
	
	
	public List<Post> getPosts() {
		 
		return posts;
	}
	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}
	public int getPostsCount() {
		   
		return posts.size();
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}	
	public Boolean getHomepage() {
		return homepage;
	}
	public void setHomepage(Boolean homepage) {
		this.homepage = homepage;
	}
	
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	 
	
	
	
	@Override
	public String toString() {
		return name+"-"+image+"-"+id;
	}
	
	
}
