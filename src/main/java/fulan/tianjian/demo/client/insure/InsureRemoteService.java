package fulan.tianjian.demo.client.insure;

import fulan.tianjian.demo.client.AbstractHttpClient;
import fulan.tianjian.demo.model.client.insure.remote.InsureRemote;
import fulan.tianjian.demo.model.client.rest.MyRestValueModel;

import org.springframework.stereotype.Service;


@Service
public class InsureRemoteService extends AbstractHttpClient<InsureRemote> {
	
	
	public MyRestValueModel<InsureRemote> postRestResult(String url, String params) {
		return postRestResult(url, params, InsureRemote.class);
	}

    @Override
    public boolean analyseResult(InsureRemote insureRemote) {
        if("0000".equals(insureRemote.getResultCode())) {
            return true;
        }
        return false;
    }
}
