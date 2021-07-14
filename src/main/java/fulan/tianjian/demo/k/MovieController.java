package fulan.tianjian.demo.k;

import java.util.ArrayList;
import java.util.List;

import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import fulan.tianjian.demo.model.web.ResponseValue;

@RestController
@RequestMapping("/movie")
public class MovieController {
	
	@Autowired
	private MovieRepository movieRespository;
	
	@GetMapping("create")
	public ResponseValue<Boolean> createMovie() {
		
		MovieEntity movie = new MovieEntity("恐怖片", "午夜凶铃");
		
		PersonEntity person = new PersonEntity(10, "卡梅隆");
		
		List<Roles> rolesAndActors = new ArrayList<Roles>();
		List<String> roles = new ArrayList<String>();
		roles.add("导演");
		Roles role = new Roles(person, roles);
		rolesAndActors.add(role);
	
		movie.setActorsAndRoles(rolesAndActors);
		
		movieRespository.save(movie);

		return ResponseValue.successResponse(true);
	}

}
