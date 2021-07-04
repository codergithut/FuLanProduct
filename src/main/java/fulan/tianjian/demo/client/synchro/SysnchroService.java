package fulan.tianjian.demo.client.synchro;

import static fulan.tianjian.demo.constant.ConstantCls.SYSNCHRO_URL;

import org.springframework.stereotype.Service;

import fulan.tianjian.demo.client.AbstractHttpClient;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;
import fulan.tianjian.demo.model.client.synchro.SynchroModel;

@Service
public class SysnchroService extends AbstractHttpClient<SynchroModel>{
	
	
	public boolean sysnchroData(String param) {
		MyRestValueModel<SynchroModel> data = postRestResult(SYSNCHRO_URL, param, SynchroModel.class);
        return "0000".equals(data.getStatus());
	}

	@Override
	public boolean analyseResult(SynchroModel synchroModel) {
		if("0000".equals(synchroModel.getCode())) {
			return true;
		}
		return false;
	}
	
	

}
