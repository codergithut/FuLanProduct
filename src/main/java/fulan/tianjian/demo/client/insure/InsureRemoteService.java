package fulan.tianjian.demo.client.insure;

import fulan.tianjian.demo.client.AbstractHttpClient;
import fulan.tianjian.demo.model.client.remote.InsureRemote;
import org.springframework.stereotype.Service;

@Service
public class InsureRemoteService extends AbstractHttpClient<InsureRemote> {

    @Override
    public boolean analyseResult(InsureRemote insureRemote) {
        if("0000".equals(insureRemote.getResultCode())) {
            return true;
        }
        return false;
    }
}
