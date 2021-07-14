package fulan.tianjian.demo.k;

import org.neo4j.driver.internal.shaded.reactor.core.publisher.Mono;
import org.springframework.data.neo4j.repository.Neo4jRepository;


public interface MovieRepository extends Neo4jRepository<MovieEntity, String> {

    Mono<MovieEntity> findOneByTitle(String title);
}
