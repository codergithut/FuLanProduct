package fulan.tianjian.demo.web.controller.server;

import org.springframework.beans.factory.annotation.Autowired;

import fulan.tianjian.demo.model.web.ResponseValue;
import fulan.tianjian.demo.model.web.vo.UrlParamConfigVo;
import fulan.tianjian.demo.web.service.server.UrlParamConfigService;

public class UrlParamConfigController {
	
	@Autowired
	private UrlParamConfigService urlParamConfigService;
	
	public ResponseValue<String> initUrlParamConfig(UrlParamConfigVo urlParamConfigVo) {
		
		return ResponseValue.successResponse(urlParamConfigService.initUrlParamConfig(urlParamConfigVo));
		
		
	}
	
	

}
