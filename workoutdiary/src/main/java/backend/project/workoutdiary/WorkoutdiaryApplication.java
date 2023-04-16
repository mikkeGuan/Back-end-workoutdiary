package backend.project.workoutdiary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import backend.project.workoutdiary.domain.AppUser;
import backend.project.workoutdiary.domain.AppUserRepository;
import backend.project.workoutdiary.domain.Location;
import backend.project.workoutdiary.domain.LocationRepository;
import backend.project.workoutdiary.domain.Workout;
import backend.project.workoutdiary.domain.WorkoutRepository;

@SpringBootApplication
public class WorkoutdiaryApplication {
	private static final Logger log = LoggerFactory.getLogger(WorkoutdiaryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(WorkoutdiaryApplication.class, args);
	}

	@Bean
	public CommandLineRunner data(WorkoutRepository repository, AppUserRepository urepository, LocationRepository locationRepository) {
		return (args) -> {
			
			Location pasila = new Location();
			pasila.setName("Pasila");
			locationRepository.save(pasila);

			Location herttoniemi = new Location();
			herttoniemi.setName("Herttoniemi");
			locationRepository.save(herttoniemi);
			
			Location itis = new Location();
			itis.setName("Itis");
			locationRepository.save(itis);
			
			Location home = new Location();
			home.setName("Home");
			locationRepository.save(home);
			
			repository.save(new Workout(null, "Bench press", 80, 8, 4, pasila));
			repository.save(new Workout(null, "Leg extension", 100, 10, 3, herttoniemi));
			repository.save(new Workout(null, "Bicep curls", 10, 12, 3, home));
			repository.save(new Workout(null, "Latt pulldown", 55, 8, 4, itis));
			
			AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user");
			AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin");
			
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all workouts");
			for (Workout workout : repository.findAll()) {
				log.info(workout.toString());
			}
		};
		}
}
