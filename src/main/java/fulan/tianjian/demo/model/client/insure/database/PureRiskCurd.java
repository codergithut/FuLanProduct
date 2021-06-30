package fulan.tianjian.demo.model.client.insure.database;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PureRiskCurd extends JpaRepository<PureRiskEo, String>{

	PureRiskEo findByMd5Value(String md5Value);

}
