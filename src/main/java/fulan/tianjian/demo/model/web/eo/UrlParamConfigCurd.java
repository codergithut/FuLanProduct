package fulan.tianjian.demo.model.web.eo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlParamConfigCurd extends JpaRepository<UrlParamConfigEo, String>{

}
