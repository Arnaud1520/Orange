package controleur;

import vue.VueConnection;
import vue.VueGenerale;

public class OrangeEvent {
	private static VueConnection uneVueConnexion;
	private static VueGenerale uneVueGenerale;
	
	public static void main (String args[]) {
		uneVueConnexion = new VueConnection();
	}
	
	public static void rendreVisibleVueGenerale (boolean action, User unUser) {
		if (action == true) {
			uneVueGenerale = new VueGenerale(unUser);
			uneVueGenerale.setVisible(true);
		}else {
			uneVueGenerale.dispose();
		}
	}

	public static void rendreVisibleVueConnection (boolean action) {
		uneVueConnexion.setVisible(action);
	}
}
