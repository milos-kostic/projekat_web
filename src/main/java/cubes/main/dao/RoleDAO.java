package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Role; 


public interface RoleDAO {

	public List<Role> getRoleList(); 
	
	public void saveRole(Role role);
	
	public Role getRoleByAuthority(String authority);
	
	public void deleteRole(int id);
}
