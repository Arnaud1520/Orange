package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Controleur;
import controleur.Produit;
import controleur.Tableau;

public class PanelProduits extends PanelPrincipal implements ActionListener{

	private JPanel panelForm = new JPanel();
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	private JTextField txtDesignation = new JTextField();
	private JTextField txtPrixAchat = new JTextField();
	private JTextField txtDateAchat = new JTextField();
	private JComboBox txtCategorie = new JComboBox<String>();
	private static JComboBox<String> txtIdClient = new JComboBox<String>();
	
	private JTable tableProduits;
	private JScrollPane uneScroll;
	
	private JLabel txtNbProduits = new JLabel();
	
	private Tableau unTableau;
	
	public PanelProduits() {
		super ("Gestion des produits");
		this.txtCategorie.addItem("Téléphone");
		this.txtCategorie.addItem("Télévision");
		this.txtCategorie.addItem("Informatique");
		
		//construction du panel produit
				this.panelForm.setBackground(Color.green);
				this.panelForm.setBounds(40, 100, 350, 300);
				this.panelForm.setLayout(new GridLayout(6,2));
				this.panelForm.add(new JLabel("Designation :"));
				this.panelForm.add(this.txtDesignation);
				this.panelForm.add(new JLabel("Prix achat :"));
				this.panelForm.add(this.txtPrixAchat);
				this.panelForm.add(new JLabel("Date achat :"));
				this.panelForm.add(this.txtDateAchat);
				this.panelForm.add(new JLabel("Categorie :"));
				this.panelForm.add(this.txtCategorie);
				this.panelForm.add(new JLabel("le client :"));
				this.panelForm.add(txtIdClient);
				
				this.panelForm.add(this.btAnnuler);
				this.panelForm.add(this.btEnregistrer);
				this.add(this.panelForm);
				
				//remplir le comboBox des id clients
				remplirCBXClients();
				
				//construction de la JTable
				String entetes [] = {"Id Produit", "Designation", "Prix", "Date Achat", "Categorie","client"};
				this.unTableau = new Tableau(this.obtenirDonnees(), entetes);
				this.tableProduits = new JTable (this.unTableau);
			    this.uneScroll = new JScrollPane(this.tableProduits);
			    this.uneScroll.setBounds(420, 160, 300, 235);
			    this.uneScroll.setBackground(Color.gray);
			    this.add(this.uneScroll);
				
			    
			  //installer le label txtNbclient
			    this.txtNbProduits.setBounds(440, 440, 300, 20);
			    this.txtNbProduits.setText("Le nombre de clients est de :"+this.unTableau.getRowCount());
			    this.add(this.txtNbProduits);
			    
				this.btAnnuler.addActionListener(this);
				this.btEnregistrer.addActionListener(this);
						
	}
	
	public Object [] [] obtenirDonnees(){
		ArrayList<Produit> lesProduits = Controleur.selectAllProduits();
		Object [] [] matrice = new Object [lesProduits.size()] [6];
		int i=0;
		for (Produit unProduit : lesProduits) {
			matrice[i][0] = unProduit.getIdproduit();
			matrice[i][1] = unProduit.getDesignation();
			matrice[i][2] = unProduit.getPrixAchat();
			matrice[i][3] = unProduit.getDateAchat();
			matrice[i][4] = unProduit.getCategorie();
			matrice[i][5] = unProduit.getIdclient();
			i++;
		}
		return matrice;
	}
	
	public static void remplirCBXClients() 
	{
		//vider combox des clients
		PanelProduits.txtIdClient.removeAllItems();
		
		//remplir avec les clients de la BDD ; id-nom
		ArrayList<Client> lesClients = Controleur.selectAllClients("");
		
		for (Client unClient : lesClients) {
			txtIdClient.addItem(unClient.getIdclient()+"-" + unClient.getNom());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtDesignation.setText("");
			this.txtPrixAchat.setText("");
			this.txtDateAchat.setText("");
			
		}
		else if (e.getSource() == this.btEnregistrer) {
			//on recupere les champs du formulaire dans les variables
			String designation = this.txtDesignation.getText(); 
			float prixAchat= Float.parseFloat(this.txtPrixAchat.getText()); 
			String dateAchat = this.txtDateAchat.getText(); 
			String categorie = this.txtCategorie.getSelectedItem().toString();
			String chaine = txtIdClient.getSelectedItem().toString();
			String tab[]= chaine.split("-");
			int idClient = Integer.parseInt(tab[0]);
			
			//on instancie la classe client
			Produit unProduit = new Produit(designation, prixAchat, dateAchat, categorie, idClient);
			//on vérifie les données
			
			//on insère dans la BDD
			Controleur.insertProduit(unProduit);
			
			//actualisation des données du client
			this.unTableau.setDonnees(this.obtenirDonnees());
			
			
			//on vide les champs
			this.txtDesignation.setText("");
			this.txtPrixAchat.setText("");
			this.txtDateAchat.setText("");
			
			
			//on affiche un message de confirmation
			JOptionPane.showMessageDialog(this, "Insertion effectuée");
			
			//actualiser nbclient
		    txtNbProduits.setText("Le nombre de clients est de :"+unTableau.getRowCount());
		}
		
	}
}
