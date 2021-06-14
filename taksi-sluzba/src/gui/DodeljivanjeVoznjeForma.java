package gui;

import korisnici.Korisnik;
import korisnici.Pol;
import korisnici.Vozac;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class DodeljivanjeVoznjeForma extends JFrame {

    private JLabel lblAdresaPolaska = new JLabel("Adresa polaska");
    private JTextField txtAdresaPolaska = new JTextField(20);
    private JLabel lblAdresaDestinacije = new JLabel("Adresa destinacije");
    private JTextField txtAdresaDestinacije = new JTextField(20);
    private JLabel lblMusterija = new JLabel("Musterija");
    private JTextField txtMusterija = new JTextField(20);
    private JLabel lblStatusVoznje = new JLabel("Status");
    private JTextField txtStatusVoznje = new JTextField("Kreirana");
    private JLabel lblVozac = new JLabel("Vozac");
    private  JComboBox<Vozac> comboBox = new JComboBox<Vozac>();
   // private JTextField txtVozac = new JTextField(20);
    private JButton dodeliVoznju = new JButton("Dodeli");

    public DodeljivanjeVoznjeForma(String adresaPolaska,String adresaDolaska,String korisnickoIme){
        setTitle("Dodeljivanje voznji");
        setSize(700,600);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        txtStatusVoznje.setEditable(false);
        txtAdresaPolaska.setText(adresaPolaska);
        txtAdresaDestinacije.setText(adresaDolaska);
        txtMusterija.setText(korisnickoIme);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
        pack();
    }


    public void initMenu(){
        MigLayout mig = new MigLayout("wrap 2","[][]","[]10[][]10[]");
        setLayout(mig);

        add(lblAdresaPolaska);
        add(txtAdresaPolaska);
        add(lblAdresaDestinacije);
        add(txtAdresaDestinacije);
        add(lblMusterija);
        add(txtMusterija);
        add(lblStatusVoznje);
        add(txtStatusVoznje);
        add(lblVozac);
        add(comboBox);
        add(dodeliVoznju);
    }



    public void initActions(){

    }
}
