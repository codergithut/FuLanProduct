package fulan.tianjian.demo.model.client.insure.drools;

public class DrlResource {
	
	private String drlFile;
	
	private String md5Value;
	
	private String drlName;

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
	
	public static DrlResource mockDrlResource() {
		String drlString = "package fulan.tianjian.demo.drools\r\n"
				+ "rule \"PolicyRuleEngine Rule\"\r\n"
				+ "    when\r\n"
				+ "        $p : PolicyRuleEngine(carAge == 3)\r\n"
				+ "    then\r\n"
				+ "        $p.saveGivingPolicy(\"1\", \"2\", \"3\", \"4\");\r\n"
				+ "        System.out.println(\"PolicyRuleEngine Rule\");\r\n"
				+ "end\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "rule \"PolicyRuleEngine isNewEnergy\"\r\n"
				+ "	when\r\n"
				+ "	    $p : PolicyRuleEngine(seat == 3)\r\n"
				+ "    then\r\n"
				+ "        $p.saveGivingPolicy(\"1\", \"2\", \"3\", \"4\");\r\n"
				+ "        System.out.println(\"PolicyRuleEngine Rule\");\r\n"
				+ "end";
		DrlResource drlResource = new DrlResource();
		drlResource.setDrlFile(drlString);
		drlResource.setDrlName("demo");
		return drlResource;
	}
	
	

}
