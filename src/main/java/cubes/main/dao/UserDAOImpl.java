package cubes.main.dao;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Category;
import cubes.main.entity.Post;
import cubes.main.entity.Role;
import cubes.main.entity.User;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	
	@Override
	@Transactional
	public List<User> getAllUsers() {
		
		Session session = sessionFactory.getCurrentSession(); // session iz paketa hibernate
		
		List<User> list = session.createQuery("from User", User.class).getResultList();
		 
			 
			// ucitavam i njegove Role:
			for(User u : list) {
				Hibernate.initialize(u.getRoles());
			}
			
			
		return list;
	}

	
	
	@Override
	@Transactional
	public void saveUser(User user) {
		
		Session s = sessionFactory.getCurrentSession(); 
		  
	    s.saveOrUpdate(user);
		
	}

	
	@Override
	@Transactional
	public void deleteUser(String username) {

		Session session = sessionFactory.getCurrentSession();
		
		// primer sa dva pristupa bazi: (ali hibernat sintaksa)
		User user = session.get(User.class, username);		
		session.delete(user);
		
		// radili smo sa jednim nadji primer: otprilike:
		// napravis upit: delete user u where u.username = :username, 
		// damo parametar i izvrsimo upit - samo jednom
		// bolje je jednim upitom.
		 
	}

	@Override
	@Transactional
	public User getUser(String username) {
		
		Session session = sessionFactory.getCurrentSession();
		
		User user = session.get(User.class, username);

		 
			// List<User> users= session.createQuery("from User u ").getResultList();
			// ucitavam i njegove Role:  
//			for(User u : users) {
				Hibernate.initialize(user.getRoles());
//			}
			
			
		return user;
	}
	
	@Override
	public User getUserById(int id) {
 
		Session session = sessionFactory.getCurrentSession();
		User user = session.get(User.class, id);
		
		 
			//List<User> users= session.createQuery("from User u ").getResultList();
			// ucitavam i njegove Role:  
//			for(User u : users) {
				Hibernate.initialize(user.getRoles());
//			}
			
			
		return user;
	}
	
	

	@Transactional
	@Override
	public List<Role> getUserRoles() { // da uzmemo sve role iz baze:
		
		Session session = sessionFactory.getCurrentSession();
		List<Role> list = session.createQuery("from Role",Role.class).getResultList();
		 
		return list;
	}

	
	@Transactional
	@Override
	public List<User> getUserList() {
		
		Session session = sessionFactory.getCurrentSession();
		
		List<User> users = session.createQuery("from User", User.class).getResultList();
		
		 	// i njihove Role:
			// List<User> users= session.createQuery("from User u ").getResultList();
			// ucitavam i njegove Role:
			for(User u : users) {
				Hibernate.initialize(u.getRoles());
			}
			
			
		return users; 
	}


	
	@Transactional
	@Override
	public Boolean isAdmin(String username) {
 
			Session session = sessionFactory.getCurrentSession();	
		 
		 	User user = session.get(User.class, username);
		  
	//		 	Hibernate.initialize(user.getRoles());
		 
	  
		 	List<Role> roles = user.getRoles();
		
			for(Role r : roles) {
				if(r.getAuthority().equals("ROLE_admin")) {
					return true;
				}
				// System.out.println("*************** user: "+user.getUsername()+" - "+ r.getAuthority());
			}
			 
		
		return false;
	}

	
}
