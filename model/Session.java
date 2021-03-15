package model;

import java.util.Vector;

import ui.MotherUi;
import ui.MotherUiofEmployer;

public class Session {
	public static int userId;
	public static String userName;
	public static Integer lastId;
	public static Vector <Integer> id = new Vector <Integer>();
	public static MotherUi ui ;
	public static MotherUiofEmployer eui ;

	public static MotherUi getUi() {
		return ui;
	}

	public static void setUi(MotherUi ui) {
		Session.ui = ui;
	}
	
	public static MotherUiofEmployer getEUi() {
		return eui;
	}

	public static void setEUi(MotherUiofEmployer eui) {
		Session.eui = eui;
	}

	public static Integer getLastId() {
		return lastId;
	}

	public static void setLastId(Integer lastId) {
		Session.lastId = lastId;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		Session.userName = userName;
	}

	public static int getUserId() {
		return userId;
	}

	public static void setUserId(int userId) {
		Session.userId = userId;
	}
}
