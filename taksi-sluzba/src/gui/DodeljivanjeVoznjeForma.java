package gui;

import automobil.Automobil;
import korisnici.Vozac;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;
import voznja.StatusVoznje;
import voznja.Voznja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

import static taxiSluzba.TaxiSluzba.*;

public class DodeljivanjeVoznjeForma extends JFrame {
    private JLabel lblDatum = new JLabel("Datum kreiranja");
    private JTextField txtDatum = new JTextField(20);
    private JLabel lblAdresaPolaska = new JLabel("Adresa polaska");
    private JTextField txtAdresaPolaska = new JTextField(20);
    private JLabel lblAdresaDestinacije = new JLabel("Adresa destinacije");
    private JTextField txtAdresaDestinacije = new JTextField(20);
   private JLabel lblMusterija = new JLabel("Musterija");
    private JTextField txtMusterija = new JTextField(20);
    private JLabel lblStatusVoznje = new JLabel("Status");
    private JLabel lblVozaci = new JLabel("Vozaci");
    private JTextField txtStatusVoznje = new JTextField("DODELJENA");
    private  JComboBox<String> comboBoxVozaci = new JComboBox<String>();

    private TaxiSluzba taxiSluzba;
    private Voznja voznja;

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");


    public DodeljivanjeVoznjeForma(Voznja voznja){
        this.taxiSluzba = taxiSluzba;
        this.voznja=voznja;

        txtStatusVoznje.setEditable(false);
        txtAdresaPolaska.setEditable(false);
        txtAdresaDestinacije.setEditable(false);
        txtStatusVoznje.setEditable(false);
        txtDatum.setEditable(false);
        txtAdresaPolaska.setText(voznja.getAdresaPolaska());
        txtAdresaDestinacije.setText(voznja.getAdresaDestinacije());

        List<Vozac> vozaciZaComboBox = dobaviVozace();

        for (Vozac v : vozaciZaComboBox) {
            comboBoxVozaci.addItem(v.getKorisnickoIme());
        }


        setTitle("Dodeljivanje voznji");
        setSize(700,600);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        txtStatusVoznje.setEditable(false);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
        pack();
    }


    public void initMenu(){
        MigLayout mig = new MigLayout("wrap 2","[][]","[]10[][]10[]");
        setLayout(mig);


        add(lblDatum);
        add(txtDatum);
        add(lblAdresaPolaska);
        add(txtAdresaPolaska);
        add(lblAdresaDestinacije);
        add(txtAdresaDestinacije);
        add(lblStatusVoznje);
        add(txtStatusVoznje);
        add(lblVozaci);
        add(comboBoxVozaci);
        add(btnOk);
        add(btnCancel);
    }



    public void initActions(){

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusVoznje statusVoznje = StatusVoznje.valueOf(txtStatusVoznje.getText().trim());
                Vozac vozac =  pronadjiVozacaPoKorisnickomImenu(String.valueOf(comboBoxVozaci.getSelectedItem()));


                izmeniVoznju(voznja.getId(),voznja.getDatumKreirnja(),voznja.getAdresaPolaska(),voznja.getAdresaDestinacije(),statusVoznje,voznja.getBrojKM(),voznja.getTrajanjeVoznje(),voznja.getMusterija(),vozac);
                DodeljivanjeVoznjeForma.this.dispose();
                DodeljivanjeVoznjeForma.this.setVisible(false);
            }


        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodeljivanjeVoznjeForma.this.dispose();
                DodeljivanjeVoznjeForma.this.setVisible(false);
            }
        });

    }

//    private void popuniComboBox(){
//        List<Vozac> vozaciZaComboBox = dobaviVozace();
//        for (Vozac v : vozaciZaComboBox) {
//            comboBoxVoznje.addItem(v.getKorisnickoIme());
//        }
//    }


}
