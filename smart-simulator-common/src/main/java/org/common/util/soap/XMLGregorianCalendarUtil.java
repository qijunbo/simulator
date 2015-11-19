package org.common.util.soap;

import java.util.Calendar;

import javax.xml.datatype.XMLGregorianCalendar;

import com.sun.org.apache.xerces.internal.jaxp.datatype.XMLGregorianCalendarImpl;

public class XMLGregorianCalendarUtil {

	private static int meter = 0;

	public static int getMeter() {
		meter += (int) (Math.random() * 100);
		return meter;
	}

	public static XMLGregorianCalendar createXMLGregorianCalendar() {
		return createXMLGregorianCalendar(System.currentTimeMillis());
	}

	public static XMLGregorianCalendar createXMLGregorianCalendar(
			long timeMillis) {
		Calendar now = Calendar.getInstance();
		now.setTimeInMillis(timeMillis);
		XMLGregorianCalendar calendar = XMLGregorianCalendarImpl
				.createDateTime(now.get(Calendar.YEAR),
						now.get(Calendar.MONTH),
						now.get(Calendar.DAY_OF_MONTH),
						now.get(Calendar.HOUR_OF_DAY),
						now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
		return calendar;
	}

	public static String generateKey(String deviceId, int connectorId) {
		return deviceId + "#" + connectorId;
	}

}
