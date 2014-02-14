package org.jco.accountManager.ihm.swing;

import org.jco.accountManager.api.model.IAccount;

public class CountTools {

	public static final String COUNT_TYPE_CHECK_LABEL = "Chèque";
	public static final String COUNT_TYPE_SAVING_LABEL = "Epargne";
	
	public static final String COUNT_TYPE_SAVING_COLUMN_LABEL = "Epargne"; 
	public static final String COUNT_TYPE_CHECK_COLUMN_LABEL = "Budget";
	
	public static final int INFINTE = -1; 

	public static String getCountTypeColumnLabel(int type) {
		switch( type ){
		case IAccount.COUNT_TYPE_SAVING:
			return COUNT_TYPE_SAVING_COLUMN_LABEL;
		case IAccount.COUNT_TYPE_CHECK:
			return COUNT_TYPE_CHECK_COLUMN_LABEL;
		default:
			return COUNT_TYPE_CHECK_COLUMN_LABEL;	
		}
	}
	
	public static String getCountTypeLabel(int type) {
		switch( type ){
		case IAccount.COUNT_TYPE_SAVING:
			return COUNT_TYPE_SAVING_LABEL;
		case IAccount.COUNT_TYPE_CHECK:
			return COUNT_TYPE_CHECK_LABEL;
		default:
			return COUNT_TYPE_CHECK_LABEL;	
		}
	}

	public static int getCountTypeId(String type) {
		if( type.equals(COUNT_TYPE_CHECK_LABEL)){
			return IAccount.COUNT_TYPE_CHECK;
		} else if( type.equals(COUNT_TYPE_SAVING_LABEL)){
			return IAccount.COUNT_TYPE_SAVING;
		} else {
			return IAccount.COUNT_TYPE_CHECK;
		}
	}

}
