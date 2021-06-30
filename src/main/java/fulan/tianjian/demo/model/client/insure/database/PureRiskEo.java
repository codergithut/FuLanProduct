package fulan.tianjian.demo.model.client.insure.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.beans.BeanUtils;

import fulan.tianjian.demo.model.client.insure.remote.PureRiskInfoRemote;

/**
 * Created by tianjian on 2021/6/20.
 */
@Entity
@Table(name = "pure_risk_info")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class PureRiskEo {
	
	@Id
	@GeneratedValue(generator = "jpa-uuid")
	private String id;
	
	private String pureRiskInfo;
	
	private String md5Value;
	
	
    public String getPureRiskInfo() {
		return pureRiskInfo;
	}

	public void setPureRiskInfo(String pureRiskInfo) {
		this.pureRiskInfo = pureRiskInfo;
	}


	public String getMd5Value() {
		return md5Value;
	}



	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public PureRiskInfoRemote createPureRiskInfoRemoteByEo() {
        PureRiskInfoRemote pureRiskInfoRemote = new PureRiskInfoRemote();
        BeanUtils.copyProperties(this, pureRiskInfoRemote);
        return pureRiskInfoRemote;
    }
}
