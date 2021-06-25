package fulan.tianjian.demo.web.controller.config;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fulan.tianjian.demo.client.insure.DroolsService;
import fulan.tianjian.demo.exception.DrlResourceEmptyException;
import fulan.tianjian.demo.exception.PureRiskLossException;
import fulan.tianjian.demo.model.client.insure.database.DrlResourceCurd;
import fulan.tianjian.demo.model.client.insure.database.DrlResourceEo;
import fulan.tianjian.demo.model.client.insure.drools.DrlResource;
import fulan.tianjian.demo.model.web.ResponseValue;
@RestController
@RequestMapping("/config")
public class DroolController {
	
	@Autowired
	private DrlResourceCurd drlResourceCurd;
	
	@Autowired
	private DroolsService droolsService;
	
	
	/**
	 * 
	 * @param drlResource
	 * @return
	 * @throws PureRiskLossException
	 * @throws DrlResourceEmptyException 
	 */
	@PostMapping("addDrlResource")
	public ResponseValue<Boolean> saveDrlResource(@RequestBody DrlResource drlResource) throws PureRiskLossException, DrlResourceEmptyException {
		DrlResourceEo drlResourceEo = new DrlResourceEo();
		BeanUtils.copyProperties(drlResource, drlResourceEo);
		drlResourceCurd.deleteByDrlName(drlResourceEo.getDrlName());
		drlResourceCurd.save(drlResourceEo);
		droolsService.initKieSession();
		return ResponseValue.successResponse(true);
	}

}
