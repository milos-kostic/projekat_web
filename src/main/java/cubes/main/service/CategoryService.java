package cubes.main.service;

import java.util.List;

import cubes.main.entity.Category;

public interface CategoryService {
 
	
	 public List<Category> getCategoryList();  

	 public  List<Category> getCategoryListForMainPage();
	 
	 public List<Category> getCategoryListForWidget();
	 
	 public List<Category> getCategoryListForFooter();
	 
	 public List<Category> getCategoriesWithPosts();
	 
	 
	 
	 public Category getCategoryById(int id);
	 
	 
	 
	 public void saveCategory(Category category);

	 public void deleteCategory(int id); 

	
	 
}
