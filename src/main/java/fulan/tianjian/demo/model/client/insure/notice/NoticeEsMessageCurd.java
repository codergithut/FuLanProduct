package fulan.tianjian.demo.model.client.insure.notice;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


public interface NoticeEsMessageCurd extends ElasticsearchRepository<NoticeEsMessage, String>{

}
