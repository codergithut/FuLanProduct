package fulan.tianjian.demo.web.service.server;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import fulan.tianjian.demo.model.web.eo.PersonCurd;
import fulan.tianjian.demo.model.web.eo.PersonEo;
import fulan.tianjian.demo.model.web.vo.PersonVo;

@Service
public class PersonService {
	
	@Autowired
	private PersonCurd personCurd;

	
	@Transactional
	public Boolean savePersonData(List<PersonVo> persons) {

		if(CollectionUtils.isEmpty(persons)) {
			return false;
		}
		
		personCurd.deleteByOrderNumber(persons.get(0).getOrderNumber());
		
		List<PersonEo> personEos = null;
		personEos = persons.stream().map(e -> e.convertToEo()).collect(Collectors.toList());
		if(CollectionUtils.isEmpty(personEos)) {
			return false;
		}
		personCurd.saveAll(personEos);
		
		return true;
	}

	public List<PersonVo> findPersonByOrderNumber(String orderNumber) {
		List<PersonEo> personEos = personCurd.findByOrderNumber(orderNumber);
		if(CollectionUtils.isEmpty(personEos)) {
			return new ArrayList<PersonVo>();
		}
		return personEos.stream().map(e -> e.convertToVo()).collect(Collectors.toList());
	}

}
