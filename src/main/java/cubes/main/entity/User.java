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
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.Session;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Entity  
@Table(name="users")
public class User {
 
	@Id //  sami unosimo
	@Column
	@Size(min=3, max=50, message="Polje Username je obavezno, 3 do 50 znakova.")
	@NotNull
	private String username; // ovo je id za users tabelu
	@Column
	@Size(min=5, max=250, message="Polje lozinka je obavezno, 5 do 250 znakova.")
	@NotNull
	private String password;
	@Column
	private boolean enabled;
	@Column
	@Size(min=3, max=50, message="Ime je obavezno, 3 do 50 znakova.")
	@NotNull
	private String name;
	@Column
	@Size(min=3, max=50, message="Prezime je obavezno, 3 do 50 znakova.")
	@NotNull
	private String surname;
	@Column 
	// @NotNull(message="Slika je obavezna.")
	private String image;
	
//	// @Column(name="profile_image")  // on file system 
//	private MultipartFile profileImage;
	
	
	
//	@Transient  
	@ManyToMany(
			cascade= {
					CascadeType.DETACH,
					CascadeType.MERGE,
					CascadeType.PERSIST,
					CascadeType.REFRESH
					}
	//		, fetch = FetchType.EAGER  
			)
 	@JoinTable(
 			name="authorities", 
 			joinColumns  = @JoinColumn(name="username"),
 			inverseJoinColumns = @JoinColumn(name="authority"))	 
	private List<Role> roles;
	
	 
	
	
	
	
	public User() {
		
	}

	public User(String username, String password, boolean enabled) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}

	
	
	
	 
	
	 
//	public MultipartFile getProfileImage() {
//		return profileImage;
//	}
//
//	public void setProfileImage(MultipartFile profileImage) {
//		this.profileImage = profileImage;
//	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	
	public String getEncodedPassword() {
		return password.replace("{bcrypt}", "");
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean getEnabled() { // bilo je isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) { 
		this.enabled = enabled;
	}
	
	
	 
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {  
		this.roles = roles;
	}

	public void addRole(Role role) {  
		this.roles.add(role);
	}

	// za pojedinacne Role geter i seter brisemo:
//	public Role getRole() {
//		if(roles==null || roles.isEmpty()) {
//			return new Role();
//		}//		
//		return roles.get(0); // vrati prvu iz liste
//	}
//		
//	public void setRole(Role role) {
//		roles.add(role); // tu rolu ubacujem u niz
//	}

	
	
	
	public void generateBCryptPassword() { // get i set nazivamo metodu samo ako je pozivamo na jsp stranici
		
		if( ! getPassword().contains("{bcrypt}")) { //  ako nije enkriptovan onda kriptuj
			
			String password = new BCryptPasswordEncoder().encode(this.getPassword());  // enkript pre unosa u bazu
			this.setPassword("{bcrypt}"+password);
			 
		}
		
	}
	
	
	
	
	public Boolean isAdmin() {
    
		 	List<Role> roles = getRoles();
		
			for(Role r : roles) {
				if(r.getAuthority().equals("ROLE_admin")) {
					return true;
				}
				 
			}
			 
		return false;
	}
	
	
	
	
	@Override
	public String toString() { 
		return username+"-"+name+"-"+surname+"-"+password+"-"+roles;
	}
	
	
}
