package fulan.tianjian.demo.model.client.insure.remote;

import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.database.PremiumFloatingItemsEo;

/**
 * Created by tianjian on 2021/6/20.
 */
public class PremiumFloatingItemsRemote {
	
	private String premiumFloatingItemInfo;

	public String getPremiumFloatingItemInfo() {
		return premiumFloatingItemInfo;
	}

	public void setPremiumFloatingItemInfo(String premiumFloatingItemInfo) {
		this.premiumFloatingItemInfo = premiumFloatingItemInfo;
	}
	
	public PremiumFloatingItemsEo convertToEo(String orderNumber) {
		PremiumFloatingItemsEo premiumFloatingItemsEo = new PremiumFloatingItemsEo();
		BeanUtils.copyProperties(this, premiumFloatingItemsEo);
		premiumFloatingItemsEo.setOrderNumber(orderNumber);
		return premiumFloatingItemsEo;
		
	}
	
	
}
