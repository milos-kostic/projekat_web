package cubes.main.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name="messages")
public class Message {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	@NotNull 
	@Size(min=3, max=20, message="Ime treba da ima od 3 do 20 znakova.")
	private String name;
	@Column
	@NotNull 
	@Size(min=3, max=20, message="Prezime treba da ima od 3 do 20 znakova.")
	private String surname;
	@Column
	@NotNull(message="Unesite ispravnu email adresu.")
	@Pattern(regexp=".+@.+\\..+", message="Unesite ispravnu email adresu.")
	private String email;
	@Column
	@NotNull(message="Unesite sadrzaj poruke.")
	@Size(min=3, message="Sadrzaj poruke treba da ima 3 do 500 znakova.")
	private String message;
	@Column
	private boolean isSeen; // u bazi je tinyInt(1) moze i boolean isto radi
	
	
	public Message() {
		
	}
	public Message(String name, String surname, String email, String message, boolean isSeen) {
		super();
		// this.id = id;
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.message = message;
		this.isSeen = isSeen;
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public boolean getIsSeen() {
		return isSeen;
	}
	public void setIsSeen(boolean isSeen) {		 
		this.isSeen = isSeen;		 
	}
	
	
	
	
	@Override
	public String toString() {  
		return email+"-"+message+"-"+id;
	}
	
}
