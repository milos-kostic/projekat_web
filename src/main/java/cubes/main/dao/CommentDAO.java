package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Comment;


public interface CommentDAO {

	public List<Comment> getAllComments();
	public List<Comment> getAllCommentsOrdered();  

	public Comment getCommentById(int id);
	
	public List<Comment> getCommentList(Integer PostId);
	
	public List<Comment> getBloggerCommentList(String username);
	

	public long getCommentsCount(int postId); // status enabled. Post ima metodu getCommentsCount koja vraca sve
	public long getUnreadBloggerCommentsCount(String username);
	public long getUnreadCommentsCount();  // admin vidi sve komentare
	
	
	public void saveComment(Comment comment);
	  
	
	public void markAsSeen(int id);   
	
 
}
