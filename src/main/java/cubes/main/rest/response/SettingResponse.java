package cubes.main.rest.response;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cubes.main.dao.TagDAO;
import cubes.main.entity.Category;
import cubes.main.entity.Post;
import cubes.main.entity.Tag;
import cubes.main.entity.Tag;

// klasa sluzi da grupisem kateg i stikere u Jedan response:
//  tj vratice na JEdan Zahtev: JSON koji sadrzi Dva JSON NIZA: tagove i categories:
public class SettingResponse { // model koji cuva listu stikera i listu kateg


	private List<Category> categories;
	private List<Tag> tags;
	
	
	@Autowired 
	private SessionFactory sessionFactory;
	 
	public List<Tag> getTags() {
						
			 
						// i postove: 
//						for(Tag t : tags) {
//							 
//							 Hibernate.initialize(t.getPosts());
//					    }
							 
		return tags;
	}
	
	
	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}
	public List<Category> getCategories() {
//		
//		for(Category c: categories) {
//			Hibernate.initialize(c.getPosts());
//		}
		return categories;
	}
	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}
	
	
	
}
