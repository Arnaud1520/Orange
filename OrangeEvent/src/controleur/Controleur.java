package controleur;

import java.util.ArrayList;

import modele.Modele;

public class Controleur {
	public static User verifConnexion(String email, String mdp) {
		return Modele.verifConnexion(email, mdp);
	}

	public static void updateUSer(User unUser) {
		Modele.updateUSer(unUser);	
	}
	
	public static void insertClient(Client unClient) {
		Modele.insertClient(unClient);
	}
	
	public static void deleteClient(int idClient) {
		Modele.deleteClient(idClient);
	}
	
	public static void updateClient(Client unCLient) {
		Modele.updateClient(unCLient);
	}
	public static ArrayList<Client> selectAllClients (String filtre){
		return Modele.selectAllClients(filtre);
	}
	
	public static void insertProduit(Produit unProduit) {
		Modele.insertProduit(unProduit);
	}
	
	public static ArrayList<Produit> selectAllProduits (){
		return Modele.selectAllProduits();
	}

	public static ArrayList<Technicien> selectAllTechniciens() {
		
		return Modele.selectAllTechniciens();
	}
	
	public static void appelProcedure (String nomP, String tab[]) {
	Modele.appelProcedure(nomP, tab);
	}
	
}
