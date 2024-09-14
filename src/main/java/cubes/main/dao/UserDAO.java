package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Role;
import cubes.main.entity.User;

public interface UserDAO {

	public List<User> getAllUsers();  
	
	public List<User> getUserList();
	
	
	public void saveUser(User user); // enable i disable isto preko ove
	
	public void deleteUser(String username);  
		
	public User getUser(String username);
	
	public User getUserById(int id);
	
	
	public List<Role> getUserRoles(); // ne pravimo poseban DAO za Role nego ovde 
	
	
	public Boolean isAdmin(String username);
}
