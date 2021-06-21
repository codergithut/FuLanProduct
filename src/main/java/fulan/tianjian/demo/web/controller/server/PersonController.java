package fulan.tianjian.demo.web.controller.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.server.vo.PersonVo;
import fulan.tianjian.demo.web.service.server.PersonService;

/**
 * Created by tianjian on 2021/6/20.
 */
@Service
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	/**
	 * 保存人员数据
	 * @param persons 前端输入的人员数据
	 * @return
	 */
	public ResponseValue<Boolean> savePersonData(List<PersonVo> persons) {
		return null;
	}



}
