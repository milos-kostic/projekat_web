package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Category;

public interface CategoryDAO {

	
	public List<Category> getCategoryList(); // sve
	
	public List<Category> getCategoryListForMainPage(); // homepage = 1
	
	public List<Category> getCategoryListForWidget();

	public List<Category> getCategoryListForFooter();
	
	
	public Category getCategoryById(int id);
	
	
	public List<Category> getCategoryListWithPosts();  // za Rest Api
	
	
	public void saveCategory(Category category);
	
	public void deleteCategory(int id);
	
	
}
