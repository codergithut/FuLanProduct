package fulan.tianjian.demo.web.controller.server;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.vo.PersonVo;
import fulan.tianjian.demo.web.service.server.PersonService;

/**
 * Created by tianjian on 2021/6/20.
 */
@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	/**
	 * 保存人员数据
	 * @param persons 前端输入的人员数据
	 * @return
	 */
	@PostMapping("save")
	public ResponseValue<Boolean> savePersonData(@RequestBody List<PersonVo> persons) {
		
		Boolean result = personService.savePersonData(persons);
		
		return ResponseValue.successResponse(result);
	}
	
	public static void main(String[] args) {
		System.out.println(JSON.toJSONString(PersonVo.mockPersonVo("f2563aad-b692-4ace-8504-66f3517d6730")));
	}



}
