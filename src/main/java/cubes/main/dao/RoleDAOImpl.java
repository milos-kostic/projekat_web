package cubes.main.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Role;


@Component
public class RoleDAOImpl implements RoleDAO{

	@Autowired	 
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Role> getRoleList() {
		
		Session session = sessionFactory.getCurrentSession();
		 	
		List<Role> tags= session.createQuery("from Role ",Role.class).getResultList();
		 
		return tags;
	}

	  
	
	
	
	
	
	@Transactional
	@Override
	public void saveRole(Role role) {
		 
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(role); 
	}

	@Transactional
	@Override
	public Role getRoleByAuthority(String  authority) {
	    
		Session session = sessionFactory.getCurrentSession();
		
		Role role = session.get(Role.class, authority);
		
		return role;
	}

	@Transactional
	@Override
	public void deleteRole(int id) {

		Session session = sessionFactory.getCurrentSession();
		  
		// sa jednim pristupom bazi:
		Query query = session.createQuery("delete from Role where id=:RoleId");		
		query.setParameter("RoleId", id);		
		query.executeUpdate();
		
	}

}
