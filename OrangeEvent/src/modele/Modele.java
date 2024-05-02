package modele;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import controleur.Client;
import controleur.Produit;
import controleur.Technicien;
import controleur.User;

public class Modele {
	//instanciation de la connection Mysql
	private static BDD uneBDD = new BDD("localhost", "orange_efrei", "root", "");
	
	public static User verifConnexion (String email, String mdp) {
	User unUser = null;
	String requete="SELECT * FROM user WHERE email = '" + email + "' and mdp ='"+mdp+"';";
	try {
		uneBDD.seConnecter();
		Statement unStat = uneBDD.getMaConnexion().createStatement();
		ResultSet unRes = unStat.executeQuery(requete);
		if (unRes.next()) {
			//extraction des données de la BDD
			unUser = new User (unRes.getInt("iduser"), unRes.getString("nom"), unRes.getString("prenom"),unRes.getString("email"), unRes.getString("mdp"), unRes.getString("role"));			
		}
		unStat.close();
		uneBDD.seDeconnecter();
		
	}catch(SQLException exp) {
		System.out.println("Erreur de connexion à la BDD :" + requete);
		exp.printStackTrace();
	}
	return unUser;
	}

	public static void updateUSer(User unUser) {
		String requete="UPDATE user SET nom='"+unUser.getNom()+"',prenom ='"+unUser.getPrenom()+"',email ='"+unUser.getEmail()+"',Mdp='"+unUser.getMdp()+"', role ='"+unUser.getRole()+"' WHERE iduser ="+unUser.getIduser();
		try {
			uneBDD.seConnecter();
			Statement unStat = uneBDD.getMaConnexion().createStatement();
			 unStat.execute(requete);
			unStat.close();
			uneBDD.seDeconnecter();
			
		}catch(SQLException exp) {
			System.out.println("Erreur de connexion à la BDD :" + requete);
			exp.printStackTrace();
		}
	}
/***************** GESTION DES CLIENTS ************/
		public static void insertClient(Client unClient) {
			String requete ="insert into client values (null, '"+unClient.getNom()+"','"+unClient.getPrenom()+"','"+unClient.getAdresse()+"','"+unClient.getEmail()+"');";
		
			try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement();
				 unStat.execute(requete);
				unStat.close();
				uneBDD.seDeconnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de connexion à la BDD :" + requete);
				exp.printStackTrace();
			}
		
	}
		public static ArrayList<Client> selectAllClients (String filtre){
			ArrayList<Client> lesClients = new ArrayList<Client>();
			String requete;
			if (filtre.equals("")) {
				requete ="select * from client;";
			}else {
				requete = "select * from client where nom like '%"+filtre+"%' or  "+" prenom like '%"+filtre+"%'  or adresse like '%"+filtre+"%';";
			}
			try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement();
				
				//quand on recupere un resultat ( SELECT)
				 ResultSet desRes = unStat.executeQuery(requete);
				 while (desRes.next()) {
					 Client unCLient = new Client(
						 desRes.getInt("idclient"), desRes.getString("nom"),
						 desRes.getString("prenom"), desRes.getString("adresse"),
						 desRes.getString("email")
						 );
						 lesClients.add(unCLient);
					 }
				 
				unStat.close();
				uneBDD.seDeconnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de connexion à la BDD :" + requete);
				exp.printStackTrace();
			}
			
			return lesClients;
		}
		
		public static void deleteClient (int idClient) {
			String requete="delete from client where idclient = "+idClient+";";
			try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement();
				 unStat.execute(requete);
				unStat.close();
				uneBDD.seDeconnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de connexion à la BDD :" + requete);
				exp.printStackTrace();
			}
		}
		
			public static void updateClient (Client unClient) {
				String requete= "update client set nom='"+ unClient.getNom()+"', prenom ='"+unClient.getPrenom()+"', adresse = '"+ unClient.getAdresse()+"', email = '"+ unClient.getEmail()+"' where idclient = "+unClient.getIdclient()+";";
			
			try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement();
				 unStat.execute(requete);
				unStat.close();
				uneBDD.seDeconnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de connexion à la BDD :" + requete);
				exp.printStackTrace();
			}
		}
		/***************** GESTION DES PRODUITS ************/
		public static void insertProduit(Produit unProduit) {
			String requete ="insert into produit values (null, '"+unProduit.getDesignation()+"','"+unProduit.getPrixAchat()+"','"+unProduit.getDateAchat()+"','"+unProduit.getCategorie()+"','"+unProduit.getIdclient()+"');";
		
			try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement();
				 unStat.execute(requete);
				unStat.close();
				uneBDD.seDeconnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de connexion à la BDD :" + requete);
				exp.printStackTrace();
			}
		
	}
		public static ArrayList<Produit> selectAllProduits (){
			ArrayList<Produit> lesProduits = new ArrayList<Produit>();
			String requete="select * from produit;";
			try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement();
				
				//quand on recupere un resultat ( SELECT)
				 ResultSet desRes = unStat.executeQuery(requete);
				 while (desRes.next()) {
					 Produit unProduit = new Produit(
						 desRes.getInt("idproduit"), desRes.getString("designation"),
						 desRes.getFloat("prixAchat"), desRes.getString("dateAchat"),
						 desRes.getString("categorie"), desRes.getInt("idclient")
						 );
						 lesProduits.add(unProduit);
					 }
				 
				unStat.close();
				uneBDD.seDeconnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de connexion à la BDD :" + requete);
				exp.printStackTrace();
			}
			
			return lesProduits;
		}

		public static ArrayList<Technicien> selectAllTechniciens() {
			ArrayList<Technicien> lesTechniciens = new ArrayList<Technicien>();
			String requete="select * from technicien;";
			try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement();
				
				//quand on recupere un resultat ( SELECT)
				 ResultSet desRes = unStat.executeQuery(requete);
				 while (desRes.next()) {
					 Technicien unTechnicien = new Technicien(
							 desRes.getInt("Idtechnicien"), desRes.getString("nom"), desRes.getString("prenom"),
						 desRes.getString("specialite"), desRes.getString("dateEmbauche")
						 
						 );
						 lesTechniciens.add(unTechnicien);
					 }
				 
				unStat.close();
				uneBDD.seDeconnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de connexion à la BDD :" + requete);
				exp.printStackTrace();
			}
			
			return lesTechniciens;
			
		}
		
		public static void appelProcedure (String nomP, String tab[]) {
			String chaine = "'" + String.join("','", tab) + "'";
			String requete="call " + nomP + "(" + chaine +"); ";
			System.out.println(requete);
			
			try {
				uneBDD.seConnecter();
				Statement unStat = uneBDD.getMaConnexion().createStatement();
				 unStat.execute(requete);
				unStat.close();
				uneBDD.seDeconnecter();
				
			}catch(SQLException exp) {
				System.out.println("Erreur de connexion à la BDD :" + requete);
				exp.printStackTrace();
			}
		}
}
