package fulan.tianjian.demo.model.client;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



public interface RemoteRecordCurd extends JpaRepository<RemoteRecordEo, String>{

	List<RemoteRecordEo> findByUrlAndIsSuccess(String smsUrl, String string);

	List<RemoteRecordEo> findByMd5Value(String md5Value);

}
