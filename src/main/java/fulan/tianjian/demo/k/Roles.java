package fulan.tianjian.demo.k;

import java.util.List;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.RelationshipProperties;
import org.springframework.data.neo4j.core.schema.TargetNode;

@RelationshipProperties
public class Roles {

        @Id @GeneratedValue
        private Long id;

        private final List<String> roles;

        @TargetNode
        private final PersonEntity person;

        public Roles(PersonEntity person, List<String> roles) {
                this.person = person;
                this.roles = roles;
        }

        public List<String> getRoles() {
                return roles;
        }
}
