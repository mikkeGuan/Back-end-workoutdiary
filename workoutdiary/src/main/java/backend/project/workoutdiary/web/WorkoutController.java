package backend.project.workoutdiary.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import backend.project.workoutdiary.domain.Location;
import backend.project.workoutdiary.domain.LocationRepository;
import backend.project.workoutdiary.domain.Workout;
import backend.project.workoutdiary.domain.WorkoutRepository;

@Controller
public class WorkoutController {
		
		@Autowired
		private WorkoutRepository repository;
		
		@Autowired
		private LocationRepository LocationRepository;
		
		//Login page
		 @RequestMapping(value="/login")
		    public String login() {	
		        return "login";
		    }
		
		 //Show all workouts
		@RequestMapping("/workoutlist")
		public String workoutlist(Model model) {
			model.addAttribute("workouts", repository.findAll());
			return "workoutlist";

		}
		//Rest all workouts
		@RequestMapping(value="/workouts", method = RequestMethod.GET)
	    public @ResponseBody List<Workout> workoutlistRest() {	
	        return (List<Workout>) repository.findAll();
	    }    

		//Rest workout by id
	    @RequestMapping(value="/workout/{id}", method = RequestMethod.GET)
	    public @ResponseBody Optional<Workout> findworkoutRest(@PathVariable("id") Long workoutId) {	
	    	return repository.findById(workoutId);
	    }  

	  //Rest all locations
	  		@RequestMapping(value="/locations", method = RequestMethod.GET)
	  	    public @ResponseBody List<Location> locationListRest() {	
	  	        return (List<Location>) LocationRepository.findAll();
	  	    }   
	  		
	  	//Rest location by id
		    @RequestMapping(value="/location/{id}", method = RequestMethod.GET)
		    public @ResponseBody Optional<Location> findLocationRest(@PathVariable("id") Long locationId) {	
		    	return LocationRepository.findById(locationId);
		    }  
	    
	    //Add workout function
		@RequestMapping(value = "/add")
		public String addWorkout(Model model) {
			model.addAttribute("workout", new Workout());
		    model.addAttribute("locations", LocationRepository.findAll());
			return "addWorkout";
		}

		//Saving the new workout to the database
		@RequestMapping(value = "/save", method = RequestMethod.POST)
		public String saveWorkout(Workout workout) {
			repository.save(workout);
			return "redirect:/workoutlist";
		}

		//If user is admin, can delete workouts
	    @PreAuthorize("hasAuthority('admin')")
		@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
		public String deleteWorkout(@PathVariable("id") Long workoutId, Model model) {
			repository.deleteById(workoutId);
			return "redirect:/workoutlist";
		}

	    //Edit functionality
		@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
		public String editWorkout(@PathVariable("id") Long workoutId, Model model) {
		    Workout workout = repository.findById(workoutId).orElseThrow(() -> new IllegalArgumentException());
		    model.addAttribute("workout", workout);
		    model.addAttribute("locations", LocationRepository.findAll());
		    return "editWorkout";
		   
		}

		//Edited workout update
		@RequestMapping(value = "/update", method = RequestMethod.POST)
		public String update (Workout workout) {
			repository.save(workout);
			return "redirect:workoutlist";
		
		    
		    
		}
}
