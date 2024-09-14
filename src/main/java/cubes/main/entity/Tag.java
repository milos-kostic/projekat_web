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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OrderBy;

@Entity
@Table(name="tags")

public class Tag implements java.io.Serializable  {


	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	@Size(min=5, max=100, message="Polje Title je obavezno, 5 do 100 znakova.")
	@NotNull
	private String title;
	@Column
	private String color;
	
	
	
	
	@ManyToMany(
			cascade= {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
		}
			
		  , fetch = FetchType.EAGER  
					 
			) 
	
	@JoinTable(  
			name="posts_tags", 
			joinColumns = @JoinColumn(name="tag_id"),
			inverseJoinColumns = @JoinColumn(name="post_id"))   
	// @javax.persistence.OrderBy("id desc") // 
	private List<Post> posts; 
	
	
	
	
	
	
	
	public Tag() {
		
	}
	public Tag (String title, String color) {
		this.title = title;
		this.color=color;
	}
	public Tag (String id) {
		this.id = Integer.valueOf(id);
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	
	
	
	@Override
	public String toString() { 
		return title+"-"+color+"-"+id;
	}
}
