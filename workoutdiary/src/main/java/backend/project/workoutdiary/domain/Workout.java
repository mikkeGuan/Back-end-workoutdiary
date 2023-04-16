package backend.project.workoutdiary.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Workout {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String exercise;
	private int weight;
	private int repetition;
	private int sets;
	
	@ManyToOne
	@JoinColumn(name = "location_id")
	private Location location;

	
	public Workout() {
		
	}
	public Workout(Long id, String exercise, int weight, int repetition, int sets, Location location) {
		super();
		this.id = id;
		this.exercise = exercise;
		this.weight = weight;
		this.repetition = repetition;
		this.sets = sets;
		this.location = location;
		
		
			
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getExercise() {
		return exercise;
	}

	public void setExercise(String exercise) {
		this.exercise = exercise;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public int getSets() {
		return sets;
	}

	public void setSets(int sets) {
		this.sets = sets;
	}

	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	@Override
	public String toString() {
		return "Workout [id=" + id + ", exercise=" + exercise + ", weight=" + weight + ", repetition=" + repetition
				+ ", sets=" + sets + ", location=" + location + "]";
	
	
	}
}
