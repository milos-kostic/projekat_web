package cubes.main.dao;

import java.util.List;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import cubes.main.entity.Category;
import cubes.main.entity.Post;

// @Component
@Repository
public class CategoryDAOImpl implements CategoryDAO{

	 
	@Autowired 
	private SessionFactory sessionFactory;
		 
	@Override
	public List<Category> getCategoryList() {
	 
		Session session = sessionFactory.getCurrentSession();
		
		List<Category> categories = session.createQuery("from Category order by priority",Category.class).getResultList();
		
		return categories;
	}

 
	@Override
	public List<Category> getCategoryListForMainPage() {
		Session session = sessionFactory.getCurrentSession();
		List<Category> list = session.createQuery("from Category c where c.homepage=1",Category.class).setMaxResults(4).getResultList();
		return list;
	}
	 
	
	@Override
	public List<Category> getCategoryListForWidget() {
		Session session = sessionFactory.getCurrentSession();
		List<Category> list = session.createQuery("from Category c where c.homepage=1 order by priority",Category.class).setFirstResult(1).setMaxResults(5).getResultList();
		
		for(Category c: list) {
			Hibernate.initialize(c.getPosts()); 
		}
	 
	
		
		return list;
	}
	
	 
	@Override
	public List<Category> getCategoryListForFooter() {
		Session session = sessionFactory.getCurrentSession();
		List<Category> list = session.createQuery("from Category c where c.homepage=1",Category.class).setFirstResult(1).setMaxResults(4).getResultList();
		return list;
	}
 
	@Override
	public void saveCategory(Category category) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(category);
	}


 
	@Override
	public Category getCategoryById(int id) { 
		Session session = sessionFactory.getCurrentSession();
		Category cat = session.get(Category.class,id);
		return cat;
	}

 
	@Override
	public void deleteCategory(int id) { 
		
		 Session session = sessionFactory.getCurrentSession();
		 
		  
		Query query = session.createQuery("delete from Category where id=:id"); // ...= :id" // sql u hibernate
	 
		query.setParameter("id", id);
		query.executeUpdate();		
		
	}

	@Transactional
	@Override
	public List<Category> getCategoryListWithPosts() {
		Session session = sessionFactory.getCurrentSession();		
		List<Category> list = session.createQuery("select distinct c from Category c left join Post p  GROUP BY c.id ", Category.class).getResultList();
 
	
		return list; 
	}



	
	
	
	
}
