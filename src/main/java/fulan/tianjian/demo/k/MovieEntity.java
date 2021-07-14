package fulan.tianjian.demo.k;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.schema.Relationship.Direction;

@Node("Movie") 
public class MovieEntity {

        @Id 
        private final String title;

        @Property("tagline") 
        private final String description;

        @Relationship(type = "ACTED_IN", direction = Direction.INCOMING) 
        private List<Roles> actorsAndRoles;

        @Relationship(type = "DIRECTED", direction = Direction.INCOMING) 
        private List<PersonEntity> directors = new ArrayList<>();

        public MovieEntity(String title, String description) { 
                this.title = title;
                this.description = description;
        }

		public List<Roles> getActorsAndRoles() {
			return actorsAndRoles;
		}

		public void setActorsAndRoles(List<Roles> actorsAndRoles) {
			this.actorsAndRoles = actorsAndRoles;
		}

		public List<PersonEntity> getDirectors() {
			return directors;
		}

		public void setDirectors(List<PersonEntity> directors) {
			this.directors = directors;
		}

		public String getTitle() {
			return title;
		}

		public String getDescription() {
			return description;
		}
        
        

        // Getters omitted for brevity
}