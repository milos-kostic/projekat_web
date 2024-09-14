package cubes.main.dao;

import java.util.List;

import cubes.main.entity.Slider; 

public interface SliderDAO {
  
	
	 public List<Slider> getSliderList();   
	 
	 public List<Slider> getSliderListOrdered();  
	 
	 
	 
	 public void saveSlider(Slider s);
	 
	 public Slider getSliderById(int id);
	 
	 public void deleteSlider(int id);  
	  
	 
}
