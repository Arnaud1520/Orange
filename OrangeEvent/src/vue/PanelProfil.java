package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.User;

public class PanelProfil extends PanelPrincipal implements ActionListener{
	
	private User unUser;
	private JTextArea txtInfos = new JTextArea();
	private JButton btModifier = new JButton("Modifier");
	
	private JPanel panelform = new JPanel();
	private JTextField txtNom = new JTextField();
	private JTextField txtPrenom = new JTextField();
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private JTextField txtRole = new JTextField();
	private JButton btEnregistrer = new JButton("Enregistrer");
	private JButton btAnnuler = new JButton("Annuler");
	
public PanelProfil(User unUser) {

	super("Gestion des Infos du Profil");
	this.unUser = unUser;
	
	this.txtInfos.setText("___INFOS DE VOTRE PROFIL____"+"\n\n Votre nom :" + unUser.getNom()+"\n\n Votre prénom :" + unUser.getPrenom()+"\n\n Votre Email :" + unUser.getEmail()+"\n\n Votre Role :" + unUser.getRole());
	this.txtInfos.setBounds(50, 120, 300, 200);
	this.txtInfos.setBackground(Color.blue);
	this.add(this.txtInfos);
	
	this.btModifier.setBounds(100, 360, 100, 30);
	this.add(this.btModifier);
	
	//installation du formulaire dans le panel
	this.panelform.setBackground(Color.green);
	this.panelform.setBounds(400, 120, 350, 300);
	this.panelform.setLayout(new GridLayout(6,2));
	this.panelform.add(new JLabel("Nom User :"));
	this.panelform.add(this.txtNom);
	
	this.panelform.add(new JLabel("Prenom User :"));
	this.panelform.add(this.txtPrenom);
	this.panelform.add(new JLabel("Email User :"));
	this.panelform.add(this.txtEmail);
	this.panelform.add(new JLabel("MDP User :"));
	this.panelform.add(this.txtMdp);
	this.panelform.add(new JLabel("Role User :"));
	this.panelform.add(this.txtRole);
	this.panelform.add(this.btAnnuler);
	this.panelform.add(this.btEnregistrer);
	this.add(this.panelform);
	this.panelform.setVisible(false);
	
	//rendre les boutons écoutables
	this.btAnnuler.addActionListener(this);
	this.btEnregistrer.addActionListener(this);
	this.btModifier.addActionListener(this);

	
	
}

@Override
public void actionPerformed(ActionEvent e) {
	if (e.getSource() == this.btModifier) {
		this.txtNom.setText(this.unUser.getNom());
		this.txtPrenom.setText(this.unUser.getPrenom());
		this.txtEmail.setText(this.unUser.getEmail());
		this.txtMdp.setText(this.unUser.getMdp());
		this.txtRole.setText(this.unUser.getRole());
		this.panelform.setVisible(true);

	}else if (e.getSource() == this.btAnnuler) {
		this.panelform.setVisible(false);
	}
	else if (e.getSource() == this.btAnnuler) {
		this.panelform.setVisible(false);
	}
	else if (e.getSource() == this.btEnregistrer) {
		String nom = this.txtNom.getText();
		String prenom = this.txtPrenom.getText();
		String email = this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		String role = this.txtRole.getText();
		
		//on realise les modifs dans unUser
		this.unUser.setNom(nom);
		this.unUser.setPrenom(prenom);
		this.unUser.setEmail(email);
		this.unUser.setMdp(mdp);
		this.unUser.setRole(role);
		
		//controle des données
		
		//modification dans la BDD
		Controleur.updateUSer(this.unUser);
		
		//modification dans l'affichage du TextArea
		this.txtInfos.setText("___INFOS DE VOTRE PROFIL____"+"\n\n Votre nom :" + unUser.getNom()+"\n\n Votre prénom :" + unUser.getPrenom()+"\n\n Votre Email :" + unUser.getEmail()+"\n\n Votre Role :" + unUser.getRole());
		
		//on ferme le formulaire
		this.panelform.setVisible(false);
		JOptionPane.showMessageDialog(this, "Modification effectuée");
	} 
}
}
