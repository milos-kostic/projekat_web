package cubes.main.rest;

// greske za kategorije:
public class TagException extends RuntimeException{

	
	public TagException() {
		 // generalna poruka:
		super("Doslo je do problema sa ucitavanjem kategorije.");
		
	}
	
 
	public TagException(String message) {
		super(message);
	}
}
