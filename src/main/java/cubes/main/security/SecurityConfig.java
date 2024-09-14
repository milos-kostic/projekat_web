package cubes.main.security;

 
 

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource myDataSource; // iz paketa javax.sql
	
	@Override // overrajdujemo:
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		  
		  
		auth.jdbcAuthentication().dataSource(myDataSource);
		 
	}
	 
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests() 
		 
		.antMatchers("/").permitAll()   // sve Rute su otvorene, u web.xml postavimo: proveri sve rute, 
		 
		// resursi dostupni i bez logovanja:
		.antMatchers("/admin/plugins/**").permitAll()
		.antMatchers("/admin/dist/**").permitAll()
		
		 
		.antMatchers("admin/dist/**").permitAll() // za resurse .css  .js i pluginove
		
		// Rest Api
		.antMatchers("/api/**").permitAll() // za Rest Api: da radi i Post metod. 
		
		.antMatchers("admin/plugins/**").permitAll() 
		
		
		// stranice:
		.antMatchers("/admin/post-list").hasAnyRole("admin, blogger")	
		.antMatchers("/admin/post-form").hasAnyRole("admin, blogger")		
		// .antMatchers("/admin/post-save").hasRole("admin")		
		
		.antMatchers("/admin/category-list").hasAnyRole("admin, blogger")	 // samo da pregleda i dodaje nove,
		.antMatchers("/admin/category-form").hasAnyRole("admin, blogger")   	//  ne moze da menja i brise postojece
		
		.antMatchers("/admin/tag-list").hasAnyRole("admin, blogger") // samo da pregleda i dodaje nove,
		.antMatchers("/admin/tag-form").hasAnyRole("admin, blogger") //  ne moze da menja i brise postojece
		
		.antMatchers("/admin/user-list").hasRole("admin")
		.antMatchers("/admin/user-form").hasRole("admin")
		
		// Other:
		.antMatchers("/admin/message-list").hasRole("admin")
		.antMatchers("/admin/slider-list").hasRole("admin")		
		.antMatchers("/admin/comment-list").hasAnyRole("admin, blogger") 
		
		
		.antMatchers("/admin/**").hasAnyRole("admin, blogger") 
			// ** sve ostale stranice i akcije pocinju sa /admin moze da pristupi i admin i employee
			// tj da budu ulogovani
		
		//
		.and().formLogin().loginPage("/login-page").loginProcessingUrl("/authenticateTheUser").permitAll();
		
		
	
		
		
		// ruta za Rest Api za Delete, privremeno:
		http.cors().and().csrf().ignoringAntMatchers("/api/**").disable();
		
		// proba: 403 forbidden:
		// iskljuci csrf:
	///	http.httpBasic().disable();
		
		/* http.authorizeRequests().antMatchers("/**").permitAll().antMatchers("/*")
		     .fullyAuthenticated().and().formLogin()
		     .and().csrf().disable();*/
		
	     http.csrf().disable();
		
	}
	
}
