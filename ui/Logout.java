package ui;

import model.Session;

public class Logout {

	public void logout() {
		
		Session session = new Session();
		MotherUi ui = session.getUi();
		ui.setVisible(false);

		Login l = new Login();
		l.setVisible(true);
	}
	
	public void logout2() {
		
		Session session = new Session();		
		MotherUiofEmployer eui = session.getEUi();
		eui.setVisible(false);

		Login l = new Login();
		l.setVisible(true);
	}
}
