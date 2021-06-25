package fulan.tianjian.demo.util;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	
	public static Date strToDate(String strDate) {
		   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		   ParsePosition pos = new ParsePosition(0);
		   Date strtodate = formatter.parse(strDate, pos);
		   return strtodate;
		}

}
