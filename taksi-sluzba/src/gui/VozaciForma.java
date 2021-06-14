package gui;

import automobil.Automobil;
import automobil.VrstaAutomobila;
import korisnici.Pol;
import korisnici.Vozac;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class VozaciForma extends JFrame {

    private JLabel lblIme = new JLabel("Ime");
    private  JTextField txtIme = new JTextField(20);
    private JLabel lblPrezime = new JLabel("Prezime");
    private  JTextField txtPrezime = new JTextField(20);
    private JLabel lblKorisnickoIme = new JLabel("Korisnicko me");
    private  JTextField txtKorisnickoIme = new JTextField(20);
    private JLabel lbllozinka = new JLabel("Lozinka");
    private  JPasswordField pfLozinka = new JPasswordField(20);
    private JLabel lblPol = new JLabel("pol");
    private JComboBox<Pol> comboBox = new JComboBox<Pol>(Pol.values());
    private JLabel lblPlata = new JLabel("Plata");
    private JTextField txtPlata = new JTextField(20);
    private JLabel lblClanskaKarta = new JLabel("Broj clanske karte");
    private JTextField txtClanskakarta = new JTextField(20);
    private JLabel lblJmbg = new JLabel("Jmbg");
    private JTextField txtJmbg = new JTextField(20);
    private JLabel lblBrojTelefona= new JLabel("Broj teelfona");
    private JTextField txtBrojTelefona = new JTextField(20);
    private JLabel lblAdresa = new JLabel("Adresa");
    private JTextField txtAdresas = new JTextField(20);
    private JLabel lblAutomobili = new JLabel("Automobili");

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    public VozaciForma(){
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
        pack();
    }
     public void initMenu(){
         MigLayout mig = new MigLayout("wrap 2","[][]","[]10[][]10[]");
         setLayout(mig);
         add(lblIme);
         add(txtIme);
         add(lblPrezime);
         add(txtPrezime);
         add(lblKorisnickoIme);
         add(txtKorisnickoIme);
         add(lbllozinka);
         add(pfLozinka);
         add(lblPol);
         add(comboBox);
         add(lblPlata);
         add(txtPlata);
         add(lblClanskaKarta);
         add(txtClanskakarta);
         add(lblJmbg);
         add(txtJmbg);
         add(lblBrojTelefona);
         add(txtBrojTelefona);
         add(lblAdresa);
         add(txtAdresas);
     }

     public void initActions(){

     }
}
