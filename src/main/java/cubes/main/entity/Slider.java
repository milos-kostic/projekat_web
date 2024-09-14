package cubes.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="sliders")  
public class Slider {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	@Size(min=5, max=45, message="Polje Name je obavezno, 5 do 45 znakova.")
	@NotNull
	private String title;
	@Column
	@Size(min=5, max=200, message="Polje Name je obavezno, 5 do 200 znakova.")
	@NotNull
	private String description;
	@Column
	@Size(min=5, max=250, message="Polje Name je obavezno, 5 do 255 znakova.")
	@NotNull
	private String image;
//	@Column
//	private String url;
	@Column(name="button_title")  
	@Size(min=5, max=100, message="Polje Name je obavezno, 5 do 100 znakova.")
	@NotNull
	private String buttonTitle;
	@Column(name="button_url")  
	private String buttonUrl;
	@Column
	private boolean enabled;
	@Column(name="order_number") 
	private int orderNumber;
	
	
	public Slider() {		
	}
	
	public Slider(String title, String description, String image, String url, String buttonTitle, String buttonUrl, int orderNumber) {
		super();
		this.title = title;
		this.description = description;
		this.image = image;
		// this.url = url;
		this.buttonTitle = buttonTitle;
		this.buttonUrl = buttonUrl;
		this.orderNumber = orderNumber;
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	} 
	public String getButtonTitle() {
		return buttonTitle;
	}
	public void setButtonTitle(String buttonTitle) {
		this.buttonTitle = buttonTitle;
	}	
	public String getButtonUrl() {
		return buttonUrl;
	}
	public void setButtonUrl(String buttonUrl) {
		this.buttonUrl = buttonUrl;
	}
	public boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}	
	public int getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	 
	
	
	@Override
	public String toString() { 
		return title+"-"+id+"-"+description; 
	}
	
	
	
}
