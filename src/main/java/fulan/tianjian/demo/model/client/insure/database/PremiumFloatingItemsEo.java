package fulan.tianjian.demo.model.client.insure.database;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.remote.PremiumFloatingItemsRemote;

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
