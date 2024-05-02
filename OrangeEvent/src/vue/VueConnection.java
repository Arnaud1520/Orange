package vue;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controleur.Controleur;
import controleur.OrangeEvent;
import controleur.User;

public class VueConnection extends JFrame implements ActionListener, KeyListener {
	
	private JTextField txtEmail = new JTextField();
	private JPasswordField txtMdp = new JPasswordField();
	private JButton btAnnuler = new JButton("Annuler");
	private JButton btSeconnecter = new JButton("Se connecter");
	private JPanel panelForm = new JPanel();
	
	public VueConnection() {
		this.setTitle("Admin BDD Orange Event");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setBounds(100, 100, 600, 300);
		this.setLayout(null);
		this.getContentPane().setBackground(Color.gray);
		
		//inserer le logo
		ImageIcon uneImage = new ImageIcon("src/images/Orange.png");
		JLabel leLogo = new JLabel(uneImage);
		leLogo.setBounds(30, 40, 200, 200);
		this.add(leLogo);
		
		//construction du panelForm
		this.panelForm.setBounds(300, 40, 250, 200);
		this.panelForm.setBackground(Color.gray);
		this.panelForm.setLayout(new GridLayout(3,2));
		this.panelForm.add(new JLabel("Email : "));
		this.panelForm.add(this.txtEmail);
		this.panelForm.add(new JLabel("MDP : "));
		this.panelForm.add(this.txtMdp);
		this.panelForm.add(this.btAnnuler);
		this.panelForm.add(this.btSeconnecter);
		this.add(this.panelForm);
		
		//rendre les boutons ecoutables
		this.btAnnuler.addActionListener(this);
		this.btSeconnecter.addActionListener(this);
		
		this.txtEmail.addKeyListener(this);
		this.txtMdp.addKeyListener(this);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btAnnuler) {
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
		
		else if (e.getSource() == this.btSeconnecter) {
			this.traitement ();
			
		}
		
	}
	
	public void traitement() {
		String email = this.txtEmail.getText();
		String mdp = new String (this.txtMdp.getPassword());
		
		//on vérifie les donnéees (sécurité)
		
		//on vérifie dans la base de données
		User unUser = Controleur.verifConnexion(email, mdp);
		if (unUser != null) {
			
		
		
		//on ouvre le logiciel : Vue Générale
		JOptionPane.showMessageDialog(this, "Bienvenue M/MM" + unUser.getNom()+" "+unUser.getPrenom());
		
		OrangeEvent.rendreVisibleVueConnection(false);
		OrangeEvent.rendreVisibleVueGenerale(true, unUser);
		} else {
			JOptionPane.showMessageDialog(this, "Veuillez vérifier vos identifiants !");
			this.txtEmail.setText("");
			this.txtMdp.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			this.traitement();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
