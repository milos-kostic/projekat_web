package cubes.main.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="comments")
public class Comment {
 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	
	@Column
	@NotNull(message="Unesite komentar.")
	@Size(min=2,max=255, message="Kometar treba da ima 2 do 255 karaktera.")
	private String body;
	
	@Column (name="user_name")
	@NotNull 
	@Size(min=2,max=30, message="Ime treba da ima 2 do 30 karaktera.") 
	private String userName;   	

	@Column(name="user_email")
	@NotNull(message="Unesite ispravnu email adresu.")
	@Pattern(regexp=".+@.+\\..+", message="Unesite ispravnu email adresu.")
	private String userEmail;
	@Column
	private boolean status; 
	@Column (name="created_at")
    private java.sql.Timestamp createdAt;
	@Column
	private boolean isSeen; // u bazi je tinyInt(1)  
	
	
	
	 
	// ---------------- POST
	// 
	@ManyToOne(cascade = {		
				CascadeType.DETACH, 
				CascadeType.MERGE,
				CascadeType.PERSIST,
				CascadeType.REFRESH}) 
		// necu da se brise post kad obrisem sve njegove komentare
	@JoinColumn(name="post_id")  
	private Post post; 
	
	
	
	  
		
	
	public Comment() {
		
	}
	public Comment(String userName, String userEmail, String body, Boolean status) {
		super();
		this.userName = userName;
		this.userEmail= userEmail;
		this.body = body;
		this.status = status;	 
	}

	
	
	public boolean getEnabled() { // bilo je isEnabled() {
		return status;
	}
	public void setEnabled(boolean status) {
		this.status = status;
	}
	public String getCreatedAtFormatted() {
		String dateFormatted = ""; 		
		if(createdAt != null) {			
			dateFormatted = new SimpleDateFormat("MMM dd, yyyy \n HH:MM:SS").format(createdAt);			 	
		}			  
		return dateFormatted; 
	}
	public String getFormattedDateSimpleStyle() {
		
		// tipa: October 2016
  
		String dateFormatted = ""; 		
		if(createdAt != null) {			
			dateFormatted = new SimpleDateFormat("MMM yyyy").format(createdAt);			 	
		}
			  
		return dateFormatted;
	}
	
	public void setCreatedAt(java.sql.Timestamp createdAt) {
		this.createdAt = createdAt;
	}  

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}	 			 
	public Post getPost() {
		return post;
	}
	public void setPost(Post post) {
		this.post = post;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public boolean getIsSeen() {
		return isSeen;
	}
	public void setIsSeen(boolean isSeen) {		 
		this.isSeen = isSeen;		 
	}
	
	
	
	
	@Override
	public String toString() { 
		return body+" - "+id;  
	}
	
	
}
