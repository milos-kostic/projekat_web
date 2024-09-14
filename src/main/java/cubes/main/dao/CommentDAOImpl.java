package cubes.main.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;


import cubes.main.entity.Comment;
import cubes.main.entity.Message;
import cubes.main.entity.Post;
import cubes.main.entity.Tag;
 
@Repository
public class CommentDAOImpl implements CommentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Transactional
	@Override
	public List<Comment> getAllComments() {

		Session session=sessionFactory.getCurrentSession();
		List<Comment> list = session.createQuery("from Comment c order by c.id desc ", Comment.class).getResultList();
		return list;
	}

	
	@Transactional
	@Override
	public List<Comment> getAllCommentsOrdered() {

		Session session=sessionFactory.getCurrentSession();
		List<Comment> list = session.createQuery("from Comment c order by c.id desc ", Comment.class).getResultList();
		return list;
	}

	
	
	@Transactional
	@Override 
	public List<Comment> getCommentList(Integer PostId) {

		Session session = sessionFactory.getCurrentSession();
		
		 
		String queryString = "from Comment c ";
 	 
		 
			queryString = queryString + "where c.post.id = :postId order by c.id desc";
	  
		 
		Query query = session.createQuery(queryString);
		 
			query.setParameter("postId",PostId);
	 
		
		List<Comment> comments = query.getResultList(); 
		  
		return comments;
		
	}
 


	
	@Transactional
	@Override 
	public List<Comment> getBloggerCommentList(String username) {

		Session session = sessionFactory.getCurrentSession();
		
		


		// String queryString = "from Comment c ";
		// String queryString = "from Comment c left join Post p on c.post.id=p.id left join User u on p.user=u.username  WHERE u.username LIKE :UserName ";
		String queryString = "from Comment c WHERE c.post.id IN (select distinct p.id from Post p WHERE p.user.username = :UserName )  ";
		 
		 
		 
		Query query = session.createQuery(queryString);
	 	query.setParameter("UserName", username);
	 	 
		
		List<Comment> comments = query.getResultList(); 
		 
	 
		
		return comments;
		
	}
	
	
	
	@Transactional
	@Override
	public Comment getCommentById(int id) {
		 
		Session session = sessionFactory.getCurrentSession();
		Comment c = session.get(Comment.class, id);
	  
				
		return c;
	}
	
	
	
	
	@Transactional
	@Override
	public long getCommentsCount(int postId) {

		Session session = sessionFactory.getCurrentSession();
		String queryString = "select count(*) from Comment c where c.post.id = :PostId  AND  c.status=1 ";
		 
		Query query = session.createQuery(queryString);
	 	query.setParameter("PostId", postId);
	 	
		 
		return   (long) query.uniqueResult();
	}
	
	
	@Transactional
	@Override
	public long getUnreadCommentsCount() {

		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from Comment c where c.isSeen=0");
		
	 	  
		 
		return   (long) query.uniqueResult();
	}
	
	@Transactional
	@Override
	public long getUnreadBloggerCommentsCount(String username) {

		Session session = sessionFactory.getCurrentSession(); 
		
		String queryString ="SELECT COUNT(*) from Comment c WHERE c.isSeen=0 AND c.post.id IN (select distinct p.id from Post p WHERE p.user.username = :UserName )";
		
		Query query = session.createQuery(queryString);
	 	query.setParameter("UserName", username);
	 	 
		
		return   (long) query.uniqueResult();
	}

	
	
	
	
	@Transactional
	@Override
	public void saveComment(Comment comment) { 
		
		 
	
		Session session=sessionFactory.getCurrentSession();
		
		session.saveOrUpdate(comment);
	}

	
	
	
	@Transactional
	@Override
	public void markAsSeen(int id) {
		  
	}
 
	
	
	 
	
	 
}
