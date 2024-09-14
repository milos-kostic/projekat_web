package cubes.main.entity;

import java.awt.Image;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cubes.main.dao.PostDAO;

@Entity
@Table(name="posts")
public class Post {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	@Size(min=10, max=255, message="Naziv treba da ima od 10 do 255 znakova.")
	@NotNull
	private String title;
	@Column
	@Size(min=20, max=500, message="Opis treba da ima od 20 do 500 znakova.")
	@NotNull
	private String description;
	@Column
	private String image;	
	@Column
	private String image2;	
	@Column 
	private boolean homepage; 	
	@Column
	private String content;  	
	@Column
	private int views;	
	@Column
	private boolean enabled;
//	@Column (name="created_at") 
	@Column (name="created_at")
    private java.sql.Timestamp createdAt;

	

	// --------------  CATEGORY
	// kategorija: veza vise-na-jedan:
	@ManyToOne(cascade = {		
				CascadeType.DETACH, 
				CascadeType.MERGE,
				CascadeType.PERSIST,
				CascadeType.REFRESH})  
	@JoinColumn(name="category_id")  
	private Category category;  
	
	
	
	
	// ------------  TAGS
	@ManyToMany(
			cascade= {
			CascadeType.DETACH,
			CascadeType.MERGE,
			CascadeType.PERSIST,
			CascadeType.REFRESH
		}
		// , fetch = FetchType.EAGER  // kad uzmes proizvod uzmi i njegove TAGOVE		
			)   
	@JoinTable(
			name="posts_tags", 
			joinColumns = @JoinColumn(name="post_id"),
			inverseJoinColumns = @JoinColumn(name="tag_id"))  
	 
	private List<Tag> tags; 
	
	
	
	
	
	// --------------  USER
	// user: veza je vise-na-jedan:
	@ManyToOne(cascade = {		
					CascadeType.DETACH, 
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH}) 
			// necu da se brise korisnika kad obrisem sve njegove postove
	@JoinColumn(name="user_username")  
	private User user;  
		
		
	
	
		
		
	// --------------  COMMENTS 
	@OneToMany( 
			 
				cascade= {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
			}
			,
			fetch = FetchType.EAGER  
				)	  
		 @JoinColumn(name="post_id")
		 private List<Comment> comments;
		
		
	
	  
	
	
	public Post() {
		
	}
	public Post(String title, String description, String image, boolean homepage) {
		super();
		this.title = title;
		this.description = description;
		this.image = image;
		this.homepage = homepage;
	}




	public List<Comment> getComments() {		 
		return comments;
	}
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
 	public int getCommentsCount() {		
			 
		return comments.size();
 		 
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
	public String getTitleLimited() {
		
		String titleLimited = title;
		
		if(title.length() > 40)
		{
			titleLimited = title.substring(0,40);  
		}
		
		return titleLimited;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {		 
		return description;
	}
	public String getDescriptionLimited() {
		
		String descriptionLimited = description;
		
		if(description.length() > 120)
		{
			descriptionLimited = description.substring(0,120) + "...";
		}
		
		return descriptionLimited;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContentLimited() {
		
		String contentLimited = content;
		
		if(content.length() > 100)
		{
			contentLimited = content.substring(0,100) + "...";
		}
		
		return contentLimited;
	}
	public int getViews() {
		return views;
	}
	public void setViews(int views) {
		this.views = views;
	}
//	public boolean isEnabled() {
//		return enabled;
//	}
	public boolean getEnabled() { // bilo je isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public List<Tag> getTags() {
		return tags;
	}
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
		
		//  
		this.setImage2(image);
	} 
	public String getImage2() {
		return image2;
	}
	public void setImage2(String image2) {
		this.image2 = image2;
	} 
	public void setPrice(int views) {
		this.views = views;
	}
	 
	
	
	public Category getCategory() {
		
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	
	public User getUser() {		
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	 
	 
	
	
	public boolean getHomepage() {
		return homepage;
	}
	public void setHomepage(boolean homepage) {
		this.homepage = homepage;
		
		//  
		this.setEnabled(homepage);
	}
	


	 
	
	
	public Tag getRandomTag() {
		
		if(tags == null || tags.size()==0) {
			 // return new Tag();
			return null;
		}
		 
		
		Random random = new Random();
	 
		return  tags.get(random.nextInt(tags.size()));
		 
	}
	
	
	 
	
	
	public String getSeoTitle() {
		
		return title.replaceAll(" ", "-").toLowerCase(); 
	}
	
	
	
	public String getFormattedDate() {
		
		// 20 May | 2016 
		
		String dateFormatted = ""; 
		
		if(createdAt != null) {
			
			dateFormatted = new SimpleDateFormat("dd MMM | yyyy").format(createdAt);
			 	
		}
			  
		return dateFormatted;
	}
	
	
	public String getFormattedDateOldStyle() {
		
		// October 26, 2016
		
		String dateFormatted = ""; 		
		if(createdAt != null) {			
			dateFormatted = new SimpleDateFormat("MMM dd, yyyy").format(createdAt);			 	
		}
			  
		return dateFormatted;
	}
	
	
	
	public String getMonthsDifference() {
		   
		String difference= "";
		 
		 
		LocalDateTime dateTimeCreated = createdAt.toLocalDateTime();  
		LocalDateTime dateTimeNow = LocalDateTime.now(); // uses your system timezone
		 
		Duration duration = Duration.between(dateTimeCreated, dateTimeNow);
		long durationInMillis = duration.toMillis();

			
		if(TimeUnit.MILLISECONDS.toDays(durationInMillis)>365) { 

			int yearsDif = (int)(TimeUnit.MILLISECONDS.toDays(durationInMillis)/365);
			if(yearsDif>1) {
				difference =  String.valueOf(TimeUnit.MILLISECONDS.toDays(durationInMillis)/365) + " years ago";
			}else {
				difference = "1 year ago";
			} 
		}else if(TimeUnit.MILLISECONDS.toDays(durationInMillis)>30) { 

			int monthsDif = (int)(TimeUnit.MILLISECONDS.toDays(durationInMillis)/30);
			if(monthsDif>1) {
				difference = String.valueOf(TimeUnit.MILLISECONDS.toDays(durationInMillis)/30) + " months ago";
			}else {
				difference = "1 month ago";
			} 
		}else if(TimeUnit.MILLISECONDS.toHours(durationInMillis)>24) {
			int hoursDif = (int)(TimeUnit.MILLISECONDS.toHours(durationInMillis)/24);
			if(hoursDif>1) {
				difference = String.valueOf(TimeUnit.MILLISECONDS.toHours(durationInMillis)/24) + " days ago";
			}else {
				difference = "1 day ago";
			} 
		}else if(TimeUnit.MILLISECONDS.toMinutes(durationInMillis)>60) {
			int minutesDif = (int)(TimeUnit.MILLISECONDS.toMinutes(durationInMillis)/60);
			if(minutesDif>1) {
				difference = String.valueOf(TimeUnit.MILLISECONDS.toMinutes(durationInMillis)/60) + " hours ago";
			}else {
				difference = "1 hour ago";
			} 
		}
			  
	
		return difference;
	}
	
	
	
	
	public String getCreatedAtFormatted() {
		String dateFormatted = ""; 		
		if(createdAt != null) {			
			dateFormatted = new SimpleDateFormat("MMM dd, yyyy \n HH:MM:SS").format(createdAt);			 	
		}			  
		return dateFormatted; 
	}
	public void setCreatedAt(java.sql.Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	
	
	
	
	
	@Override
	public String toString() { 
		return title+" - "+id; 
	}
	
	
}
