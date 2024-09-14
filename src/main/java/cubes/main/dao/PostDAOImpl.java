package cubes.main.dao;

import java.security.Principal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
 
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Category;
import cubes.main.entity.Post;
import cubes.main.entity.User;
import cubes.main.service.CategoryService;

@Repository
public class PostDAOImpl implements PostDAO{

	@Autowired
	private SessionFactory sessionFactory;
	 
	
	@Transactional
	@Override
	public List<Post> getPostList() { // sve 
		 
		Session session = sessionFactory.getCurrentSession();
		
		List<Post> list = session.createQuery("from Post", Post.class).getResultList();
		
		return list;
	}
	
	
	@Transactional
	@Override
	public List<Post> getPostListOrdered() { // sve 
		 
		Session session = sessionFactory.getCurrentSession();
		
		List<Post> list = session.createQuery("from Post p order by p.id desc ", Post.class).getResultList();
		
		return list;
	}

	
	
	
	 
	@Transactional
	@Override 
	public List<Post> getPostList(Integer category, Integer[] tags, String user){  

		Session session = sessionFactory.getCurrentSession();
		
		 
		String queryString = "";
		if(tags != null) {
			 queryString = "select distinct p from Post p left join p.tags t where p.enabled = true ";
			 
		}else {
			 queryString = "from Post p where p.enabled = true ";			 
		}
		
		
		
		if(category!=null && category>0) {
			
			if(!queryString.contains("where")) { 
				queryString = queryString+" where ";
			}else {
				queryString = queryString+" AND ";
			}
				
			queryString = queryString + " p.category.id = :categoryId ";
		}
		
	  
		if(tags!=null && tags.length>0) {  
			if(!queryString.contains("where")) { 
				queryString = queryString+" where ";
			}else {
				queryString = queryString+" AND ";
			}
						
			queryString = queryString + " t.id IN (:tags) "; 
 
		}
		
		if(user!=null && user.length()>0) {  
			if(!queryString.contains("where")) { 
				queryString = queryString+" where ";
			}else {
				queryString = queryString+" AND ";
			}
						
			queryString = queryString + "  p.user.username = :UserName "; 
 
		}
		
		
		// order:
		queryString = queryString + " ORDER BY p.id desc ";
		  
		
		
		
		
		Query query = session.createQuery(queryString);
		
		if(category!=null && category>0) {
			query.setParameter("categoryId",category);
		}
		  
		if(tags!=null && tags.length>0) { 
			 
			query.setParameterList("tags", tags); 
		}
	  
		if(user!=null && user.length()>0) {  
			query.setParameter("UserName", user); 
		}
		
		
		List<Post> posts = query.getResultList(); 
		 
		for(Post p : posts) {
			Hibernate.initialize(p.getTags());
		}
		
		
		return posts;
		
	}

	


	// ---- getPostList SEARCH: ------
	
	@Transactional
	@Override
	public List<Post> getPostList(String text) {  // po: title, description, content
		
		Session session = sessionFactory.getCurrentSession();
		
		String stringQuery = "from Post p WHERE p.enabled = true AND (p.title LIKE :text OR p.description LIKE :text OR p.content LIKE :text)"; 
	 
		Query query = session.createQuery(stringQuery);
		
		query.setParameter("text", "%"+text+"%");
		 
		
		// i tagove:
		List<Post> posts = query.getResultList();
		for(Post p : posts) {
			Hibernate.initialize(p.getTags());
		}
		
		return posts; // query.getResultList();
	}	
	  
	
	
	
	// ---- getPostList SEARCH: ------
	
		@Transactional
		@Override
		public List<Post> getPostList(String text, Integer categoryId, String userName) { 
			
			Session session = sessionFactory.getCurrentSession();
			
		  
					 
			String stringQuery = "from Post p WHERE p.category.id = :CategoryId ";
			 
			Query query = session.createQuery(stringQuery);
			
			query.setParameter("CategoryId", categoryId);
	 
			// i tagove:
			List<Post> posts = query.getResultList();
			for(Post p : posts) {
				Hibernate.initialize(p.getTags());
			}
			 
			return posts; // query.getResultList();
		}	
		
		
		
		
		
		
		
		
		@Transactional  
		public List<Post> getPostListByCategoryId(Integer categoryId){	
			Session session = sessionFactory.getCurrentSession(); 
			String stringQuery = "from Post p where p.category.id = :CategoryId ";
			 
	 
			Query query = session.createQuery(stringQuery);
			
			query.setParameter("CategoryId", categoryId);
	  
			// i tagove:
			List<Post> posts = query.getResultList();
			for(Post p : posts) {
				Hibernate.initialize(p.getTags());
			}
			 
			return posts;  
		}	
		
		
		
		@Transactional  
		public List<Post> getPostListByUserName(String username){	
			Session session = sessionFactory.getCurrentSession();
			  
			String stringQuery = "from Post p where p.user.username = :UserName ";
		 
	 
			Query query = session.createQuery(stringQuery);
			
			query.setParameter("UserName", username);
	  
			List<Post> posts = query.getResultList();
			for(Post p : posts) {
				Hibernate.initialize(p.getTags());
			}
			 
			return posts;  
		}	
		
		
		
		@Transactional  
		public List<Post> getPostListByTagId(Integer tagId){	
			Session session = sessionFactory.getCurrentSession();
			  
			String stringQuery = "select distinct p from Post p left join p.tags t where t.id = :TagId ";
			 
	 
			Query query = session.createQuery(stringQuery);
			
			query.setParameter("TagId", tagId);
	  
			List<Post> posts = query.getResultList();
			for(Post p : posts) {
				Hibernate.initialize(p.getTags());
			}
			 
			return posts; // query.getResultList();
		}	
		
		
		
		
		
		
		
		
		
		
		//admin:
		@Transactional  
		public List<Post> getPostListByUser(String username){	
			Session session = sessionFactory.getCurrentSession();
			  
			String stringQuery = "from Post p where p.user.username = :UserName order by p.id desc ";
			 
	 
			Query query = session.createQuery(stringQuery);
			
			query.setParameter("UserName", username);
	 
	   
			List<Post> posts = query.getResultList();
			for(Post p : posts) {
				Hibernate.initialize(p.getTags());
			}
			 
			
			return posts; // query.getResultList();
		}	
		
		
		
		
		
		
		
		
		
	// ---- getPostList SEARCH ADMIN: ------
	 
	@Transactional
	@Override 
	public List<Post> getPostList(
			
					String title , 
					Integer categoryId,  
					String username, 
					Boolean status
			 
			){    

		
		Session session = sessionFactory.getCurrentSession();
		
		 
		String queryString = "from Post p ";
		  
		
		if(title!=null && !title.isEmpty()) {  
			queryString = queryString + "where p.title LIKE :SearchTextPostTitle ";
		}
		
		
		
		
		// po Kategoriji: 
		if(categoryId!=null) {  
			if(!queryString.contains("where")) { 
				queryString = queryString+" where ";
			}else {
				queryString = queryString+" AND ";
			}
						
			queryString = queryString + "  p.category.id = :CategoryId ";  
 
		}
		 
		
		
		// po korisniku, samo admin, blogger vidi samo svoje postove: 
		if(username!=null && username.length()>0) {  
			if(!queryString.contains("where")) { 
				queryString = queryString+" where ";
			}else {
				queryString = queryString+" AND ";
			}
						
			queryString = queryString + "  p.user.username = :UserName ";  
 
		}
		
		
		
		// po Statusu: 
		if(status!=null) {  
			if(!queryString.contains("where")) { 
				queryString = queryString+" where ";
			}else {
				queryString = queryString+" AND ";
			}
						
			queryString = queryString + "  p.enabled = :Status "; 
 
		}
		
		 
		 
		queryString = queryString + "  ORDER BY p.id DESC ";
		
		
		 
	Query query = session.createQuery(queryString);
	
		
		if(title!=null && !title.isEmpty()) {  
			query.setParameter("SearchTextPostTitle", "%"+title+"%");
		}
	 
		if(categoryId!=null) {
			query.setParameter("CategoryId",categoryId);
		}
		  
	  
		if(username!=null && username.length()>0) {  
			query.setParameter("UserName", username); 
		}
		
		if(status!=null) {  
			query.setParameter("Status", status);  
		}
		
		 
	
		List<Post> posts = query.getResultList(); 
		 
		
		return posts;
		
	}

	 
		
		
	
		@Transactional
		@Override
		public List<Post> getPostList(String searchTextTitle, String searchTextCategory) { 
			 
			
			Session session = sessionFactory.getCurrentSession();
			 
			String stringQuery = "from Post p where p.title LIKE :SearchTextTitle OR p.category.name LIKE :SearchTextCategory"; 
			 
			Query query = session.createQuery(stringQuery);
			
			query.setParameter("SearchTextTitle", "%"+searchTextTitle+"%");
			query.setParameter("SearchTextCategory", "%"+searchTextCategory+"%");
			 
			List<Post> posts = query.getResultList();
			for(Post p : posts) {
				Hibernate.initialize(p.getTags());
			}
			
			return posts;  
		}	
	
	

	@Transactional
	@Override
	public List<Post> getPostListForMainPage() { // latest 3 for homepage
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Post> list = session.createQuery("from Post p where p.homepage=1 order by p.id desc",Post.class).setMaxResults(3).getResultList();
		 
		for(Post p : list) {
			Hibernate.initialize(p.getTags());
		}
		
		return list;
	}

	@Transactional
	@Override
	public List<Post> getLatest12PostsForMainPage() {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Post> list = session.createQuery("from Post p order by p.id desc",Post.class).setMaxResults(12).getResultList();
		
		return list;
	}
	
	@Transactional
	@Override
	public List<Post> getLatest3Posts() {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<Post> list = session.createQuery("from Post p order by p.id desc",Post.class).setMaxResults(3).getResultList();
		
		return list;
	}
 
 
	@Transactional 
	@Override
	public List<Post> getPostListWithTags() {
		
		Session session = sessionFactory.getCurrentSession();
		List<Post> list = session.createQuery("from Post", Post.class).getResultList();
		 
		for(Post p : list) {
			Hibernate.initialize(p.getTags());
		}
				
		return list;
	}
	
	
	
	// for   Widget: 
	@Transactional
	@Override 
	public List<Post> getLatest3MostViewedPostsAtLastMonth() {

		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "from Post p where createdAt < :MonthAgo order by views desc, id desc";
		Query query = session.createQuery(queryString);
		
	 
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		Date dateMonthAgo = cal.getTime();
		query.setParameter("MonthAgo", dateMonthAgo); 
		
	
		
		List<Post> posts = query.setMaxResults(3).getResultList();
	 
		return posts;
	}
	
	
	 
	
	
	@Transactional
	@Override 
	public List<Post> getMostViewed3Posts() {  // za admin dashboard

		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "from Post p order by views desc, id desc";
		Query query = session.createQuery(queryString);
		
	 
		 
		List<Post> posts =  query.setMaxResults(3).getResultList();
	  
		
		return posts;
	}
	
	@Transactional
	@Override 
	public List<Post> getMostViewed3Posts(String username) {  

		Session session = sessionFactory.getCurrentSession();
		
		String queryString = "from Post p  where p.user.username = :UserName order by views desc, id desc";
		Query query = session.createQuery(queryString);
		
		query.setParameter("UserName", username);
		
		 
		List<Post> posts =  query.setMaxResults(3).getResultList();
	  
		
		return posts;
	}
 
	
	
	
	
	
	
	
	@Transactional
	@Override
	public void savePost(Post post) {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(post);
		
	}
	
	
	

	@Transactional
	@Override
	public void deletePost(int id) {
		 
		Session session = sessionFactory.getCurrentSession();
	
		Query query = session.createQuery("delete from Post where id=:PostId"); // hibernate.query, ne persistance.q
		query.setParameter("PostId", id);		
		query.executeUpdate();
	}

	
	
	
	@Transactional
	@Override
	public Post getPostById(int id) {
		 
		Session session = sessionFactory.getCurrentSession();
		Post p = session.get(Post.class, id);
	  
		 Hibernate.initialize(p.getTags()); 
				
		return p;
	}
	

	@Transactional
	@Override
	public Post getPreviousPostById(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		 
		Query query = session.createQuery("from Post p where p.id < :PostID order by p.id desc");		
		query.setParameter("PostID", id).setMaxResults(1);; 
		 
		
		// if null:
		List<Post> results = query.getResultList();
        if (results.isEmpty()) return new Post();
        else if (results.size() == 1) return results.get(0);
        		
		return results.get(0);
		
	}
	
	@Transactional
	@Override
	public Post getNextPostById(int id) {
		
		Session session = sessionFactory.getCurrentSession();
		 
		Query query = session.createQuery("from Post p where p.id > :PostID order by p.id asc");
		query.setParameter("PostID", id).setMaxResults(1);  
		

		// if null:
		List<Post> results = query.getResultList();
        if (results.isEmpty()) return new Post();
        else if (results.size() == 1) return results.get(0);
        		
		return results.get(0);
		 
	}

	@Transactional
	@Override
	public void  incrementViews(int id) {
		 
		Session session = sessionFactory.getCurrentSession();
		 
		Query query = session.createQuery("UPDATE Post p SET p.views = p.views + 1 WHERE p.id = :PostID");
		
		query.setParameter("PostID", id).executeUpdate();	
		  
	}
	
	 
}
