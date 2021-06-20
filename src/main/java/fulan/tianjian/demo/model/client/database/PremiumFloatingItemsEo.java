package fulan.tianjian.demo.model.client.database;

import fulan.tianjian.demo.model.client.remote.PremiumFloatingItemsRemote;
import org.springframework.beans.BeanUtils;

/**
 * Created by tianjian on 2021/6/20.
 */
public class PremiumFloatingItemsEo {
    public PremiumFloatingItemsRemote convertEoToRemote() {
        PremiumFloatingItemsRemote premiumFloatingItemsRemote = new PremiumFloatingItemsRemote();
        BeanUtils.copyProperties(this, premiumFloatingItemsRemote);
        return premiumFloatingItemsRemote;
    }
}
