package org.jco.accountManager.ihm.swing;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jco.accountManager.api.model.events.IAccountEvent;

public class Serializator {
	
	public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

	public static String[] getAccountEventToRow(final IAccountEvent event) {
		return new String[] {"", getStringFormattedDate(event.getDate()), event.getLabel(), String.valueOf(event.getPay()), "", ""};
	}
	
	public static String getStringFormattedDate(final Date date){
		return dateFormatter.format(date.getTime());
	}
}
