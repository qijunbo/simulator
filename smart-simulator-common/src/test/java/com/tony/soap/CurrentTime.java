package com.tony.soap;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentTime {

	public String currentTime( ) {
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMDDHHmm");
		return format.format(new Date());
	}

}
