package org.jco.accountManager.ihm.swing;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.jco.accountManager.api.model.events.IAccountEvent;

public class Serializator {
	
	public static SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

	public static String[] serializeAccountEventToRow(final IAccountEvent event) {
		return new String[] {"", serializeDateToFormattedString(event.getDate()), event.getLabel(), String.valueOf(event.getPay()), "", ""};
	}
	
	public static String serializeAccountEventToString(final IAccountEvent event) {
		return new String("["+event.getAccountNumber()+"] ["+event.getUid()+"] ["+serializeDateToFormattedString(event.getDate())+"]["+event.getType()+"] "+event.getPay() +" "+ event.getLabel());
	}
	
	public static String serializeDateToFormattedString(final Date date){
		return dateFormatter.format(date.getTime());
	}
}
