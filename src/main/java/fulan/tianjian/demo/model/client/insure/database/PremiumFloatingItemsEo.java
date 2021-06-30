package fulan.tianjian.demo.model.client.insure.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.remote.PremiumFloatingItemsRemote;

/**
 * Created by tianjian on 2021/6/20.
 */
@Entity
@Table(name = "premium_floating_items")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PremiumFloatingItemsEo {
	
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	
	private String premiumFloatingItemInfo;
	
	private String orderNumber;
	
	
    public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getPremiumFloatingItemInfo() {
		return premiumFloatingItemInfo;
	}



	public void setPremiumFloatingItemInfo(String premiumFloatingItemInfo) {
		this.premiumFloatingItemInfo = premiumFloatingItemInfo;
	}



	public String getOrderNumber() {
		return orderNumber;
	}



	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}



	public PremiumFloatingItemsRemote convertEoToRemote() {
        PremiumFloatingItemsRemote premiumFloatingItemsRemote = new PremiumFloatingItemsRemote();
        BeanUtils.copyProperties(this, premiumFloatingItemsRemote);
        return premiumFloatingItemsRemote;
    }
}
