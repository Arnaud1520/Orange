package vue;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import controleur.Client;
import controleur.Controleur;
import controleur.Tableau;

public class PanelClient extends PanelPrincipal implements ActionListener{

	private JPanel panelForm = new JPanel();
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtAdresse = new JTextField();
	private JTextField txtEmail = new JTextField();
	
	private JTable tableClients;
	private JScrollPane uneScroll;
	private Tableau unTableau;
	
	private JPanel panelFiltre = new JPanel();
	private JButton btFiltrer = new JButton("Filtrer");
	private JTextField txtFiltre = new JTextField();
	
	private JLabel txtNbClients = new JLabel();
	
	
	public PanelClient() {
		super ("Gestion des clients");
		
		//construction du panel client
		this.panelForm.setBackground(Color.blue);
		this.panelForm.setBounds(40, 100, 350, 300);
		this.panelForm.setLayout(new GridLayout(5,2));
		this.panelForm.add(new JLabel("Nom Client :"));
		this.panelForm.add(this.txtNom);
		this.panelForm.add(new JLabel("Prenom Client :"));
		this.panelForm.add(this.txtPrenom);
		this.panelForm.add(new JLabel("Adresse Client :"));
		this.panelForm.add(this.txtAdresse);
		this.panelForm.add(new JLabel("Email Client :"));
		this.panelForm.add(this.txtEmail);
		
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btEnregistrer);
		this.add(this.panelForm);
		
		//placement du panel filtre
		this.panelFiltre.setBackground(Color.gray);
		this.panelFiltre.setBounds(420, 110, 360, 40);
		this.panelFiltre.setLayout(new GridLayout(1,3));
		this.panelFiltre.add(new JLabel("Filtrer par :"));
		this.panelFiltre.add(this.txtFiltre);
		this.panelFiltre.add(this.btFiltrer);
		this.add(this.panelFiltre);
		this.btFiltrer.addActionListener(this);
		
		//construction de la JTable
		String entetes [] = {"Id client", "Nom", "Prenom", "Adresse", "Email"};
		this.unTableau = new Tableau(this.obtenirDonnees(""), entetes);
		this.tableClients = new JTable (this.unTableau);
	    this.uneScroll = new JScrollPane(this.tableClients);
	    this.uneScroll.setBounds(420, 160, 300, 235);
	    this.uneScroll.setBackground(Color.gray);
	    this.add(this.uneScroll);
	    
	    //installer le label txtNbclient
	    this.txtNbClients.setBounds(440, 440, 300, 20);
	    this.txtNbClients.setText("Le nombre de clients est de :"+this.unTableau.getRowCount());
	    this.add(this.txtNbClients);
	    
		this.btAnnuler.addActionListener(this);
		this.btEnregistrer.addActionListener(this);
		
		//suppression d'un client sur double click
		this.tableClients.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int numLigne, idCLient;
				if (e.getClickCount() >= 2) {
					numLigne = tableClients.getSelectedRow();
					idCLient = Integer.parseInt(unTableau.getValueAt(numLigne, 0).toString());
					int reponse = JOptionPane.showConfirmDialog(null, "Voulez-vous supprimer le client ?", "Suppresion du client", JOptionPane.YES_NO_OPTION);
					if (reponse == 0) {
						//suppression en BDD
						//Controleur.deleteClient(idCLient);
						
						//appel de la procedure stockée
						String nomP = "deleteClient";
						String tab[] = {idCLient+""};
						Controleur.appelProcedure(nomP, tab);
						
						//actualiser affichage
						unTableau.setDonnees(obtenirDonnees(""));
						//actualisation de la liste des clients dans le panelproduit
						PanelProduits.remplirCBXClients();
						
						//actualiser nbclient
					    txtNbClients.setText("Le nombre de clients est de :"+unTableau.getRowCount());
					}
				}else if (e.getClickCount() >= 1) {
					numLigne = tableClients.getSelectedRow();
					txtNom.setText(unTableau.getValueAt(numLigne, 1).toString());
					txtPrenom.setText(unTableau.getValueAt(numLigne, 2).toString());
					txtAdresse.setText(unTableau.getValueAt(numLigne, 3).toString());
					txtEmail.setText(unTableau.getValueAt(numLigne, 4).toString());
					btEnregistrer.setText("Modifier");
				}
				
			}
		});
		
	}
	public Object [] [] obtenirDonnees(String filtre){
		ArrayList<Client> lesClients = Controleur.selectAllClients(filtre);
		Object [] [] matrice = new Object [lesClients.size()] [5];
		int i=0;
		for (Client unClient : lesClients) {
			matrice[i][0] = unClient.getIdclient();
			matrice[i][1] = unClient.getNom();
			matrice[i][2] = unClient.getPrenom();
			matrice[i][3] = unClient.getAdresse();
			matrice[i][4] = unClient.getEmail();
			i++;
		}
		return matrice;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtAdresse.setText("");
			this.btEnregistrer.setText("Enregistrer");
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Enregistrer")) {
			//on recupere les champs du formulaire dans les variables
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText(); 
			String email = this.txtEmail.getText(); 
			String adresse = this.txtAdresse.getText(); 
			
			//on instancie la classe client
			Client unClient = new Client(nom, prenom, adresse, email);
			//on vérifie les données
			
			//on insère dans la BDD
			//Controleur.insertClient(unClient);
			
			String nomP = "insertClient";
			String tab[] = {nom, prenom, adresse, email};
			Controleur.appelProcedure(nomP, tab);
			
			//on actualise affichage apres insertion
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			
			//on vide les champs
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtAdresse.setText("");
			//on affiche un message de confirmation
			JOptionPane.showMessageDialog(this, "Insertion effectuée");
			
			//actualisation de la liste des clients dans le panelproduit
			PanelProduits.remplirCBXClients();
			
			//actualiser le nbclient
		    txtNbClients.setText("Le nombre de clients est de :"+unTableau.getRowCount());

			
		} else if (e.getSource() == this.btFiltrer) {
			String filtre = this.txtFiltre.getText();
			
			//actualiser la matrice des donnees
			this.unTableau.setDonnees(this.obtenirDonnees(filtre));
		}
		else if (e.getSource() == this.btEnregistrer && this.btEnregistrer.getText().equals("Modifier")) {
			String nom = this.txtNom.getText(); 
			String prenom = this.txtPrenom.getText(); 
			String email = this.txtEmail.getText(); 
			String adresse = this.txtAdresse.getText();
			
			int numLigne = this.tableClients.getSelectedRow();
			int idClient = Integer.parseInt(this.unTableau.getValueAt(numLigne, 0).toString());
			
			//instanciation client
			Client unClient = new Client(idClient, nom, prenom, adresse, email);
			
			Controleur.updateClient(unClient);
			
			//actualisation des données du client
			this.unTableau.setDonnees(this.obtenirDonnees(""));
			
			//actualisation de la liste des clients dans le panelproduit
			PanelProduits.remplirCBXClients();
			

			
			//on vide les champs et on remet Enregistrer
			this.txtNom.setText("");
			this.txtPrenom.setText("");
			this.txtEmail.setText("");
			this.txtAdresse.setText("");
			this.btEnregistrer.setText("Enregistrer");
			JOptionPane.showMessageDialog(this, "Modification réussie du client.");
			
		}
		
	}
}
