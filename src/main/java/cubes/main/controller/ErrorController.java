package cubes.main.controller;

 import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorController {

	@RequestMapping(value="errors")
	public String getErrorPage(HttpServletRequest request, Model model) {
		 
		int statusCode = getErrorCode(request);
		
		model.addAttribute("statusCode", statusCode);
		
		switch(statusCode) {
		
		case 400:
			  
			model.addAttribute("errorMessage","Poslali ste los HttpRequest.");			
			break;
			
		case 404:
			  
			// model.addAttribute("color",...); 
			// model.addAttribute("image",...);
			model.addAttribute("errorMessage","Uneli ste adresu koja ne postoji.");			
			break;
			
		default:
			
			model.addAttribute("errorMessage","Doslo je do greske.");
		}
		  
		return "error-page";
	}
	
	
	
	
	private int getErrorCode(HttpServletRequest httpRequest) {
		
		return (Integer)httpRequest.getAttribute("javax.servlet.error.status_code"); // vraca Kod greske koja se desila
		
	}
	
}
