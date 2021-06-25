package fulan.tianjian.demo.model.client.insure.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "drl_resource")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class DrlResourceEo {
	
	@Id
	@GeneratedValue(generator = "jpa-uuid")
    private String id;
	
	/**
	 * drlFile
	 */
    private String drlFile;
	
    /**
     * md5Value
     */
	private String md5Value;
	
	/**
	 * drlName
	 */
	private String drlName;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDrlFile() {
		return drlFile;
	}

	public void setDrlFile(String drlFile) {
		this.drlFile = drlFile;
	}

	public String getMd5Value() {
		return md5Value;
	}

	public void setMd5Value(String md5Value) {
		this.md5Value = md5Value;
	}

	public String getDrlName() {
		return drlName;
	}

	public void setDrlName(String drlName) {
		this.drlName = drlName;
	}
	

}
