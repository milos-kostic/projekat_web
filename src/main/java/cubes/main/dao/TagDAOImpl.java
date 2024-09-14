package cubes.main.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cubes.main.entity.Category;
import cubes.main.entity.Tag;


@Component
public class TagDAOImpl implements TagDAO{

	@Autowired	 
	private SessionFactory sessionFactory;
	
	@Transactional
	@Override
	public List<Tag> getTagList() {
		
		Session session = sessionFactory.getCurrentSession();
		 	
		List<Tag> tags= session.createQuery("from Tag ",Tag.class).getResultList();
		 
		return tags;
	}

	
	
	
	@Transactional
	@Override
	public List<Tag> getTagsOrderedByUsage() {
		
		Session session = sessionFactory.getCurrentSession();
		 
	    List<Tag> tags= session.createQuery("select distinct t from Tag t left join t.posts p GROUP BY t ORDER BY count(t.id) desc ", Tag.class).getResultList();
		 
		 
		
		 
		return tags;
	}
	
	
	@Transactional
	@Override
	public void saveTag(Tag tag) {
		 
		Session session = sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(tag); 
	}

	@Transactional
	@Override
	public Tag getTagById(int id) {
	    
		Session session = sessionFactory.getCurrentSession();
		
		Tag tag = session.get(Tag.class,id);
		
		return tag;
	}

	@Transactional
	@Override
	public void deleteTag(int id) {

		Session session = sessionFactory.getCurrentSession();
		  
		Query query = session.createQuery("delete from Tag where id=:TagId");		
		query.setParameter("TagId", id);		
		query.executeUpdate();
		
	}
	
	
	
	@Transactional
	@Override
	public List<Tag> getTagsWithPosts(){
		
		Session session = sessionFactory.getCurrentSession();		
		List<Tag> list = session.createQuery("from Tag t left join t.posts p ", Tag.class).getResultList();
		 
		for(Tag t : list) {
			Hibernate.initialize(t.getPosts());
		}
		
		return list; 
	}

}
