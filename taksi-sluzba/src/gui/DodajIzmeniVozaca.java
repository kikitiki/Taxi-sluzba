package gui;

import automobil.Automobil;
import korisnici.Korisnik;
import korisnici.Pol;
import korisnici.Vozac;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static taxiSluzba.TaxiSluzba.korisniciFajl;

public class DodajIzmeniVozaca extends JFrame {

    private JLabel lblIme = new JLabel("Ime");
    private  JTextField txtIme = new JTextField(20);
    private JLabel lblPrezime = new JLabel("Prezime");
    private  JTextField txtPrezime = new JTextField(20);
    private JLabel lblKorisnickoIme = new JLabel("Korisnicko me");
    private  JTextField txtKorisnickoIme = new JTextField(20);
    private JLabel lbllozinka = new JLabel("Lozinka");
    private  JPasswordField pfLozinka = new JPasswordField(20);
    private JLabel lblPol = new JLabel("Pol");
    private JComboBox<Pol> comboBox = new JComboBox<Pol>(Pol.values());
    private JLabel lblPlata = new JLabel("Plata");
    private JTextField txtPlata = new JTextField(20);
    private JLabel lblClanskaKarta = new JLabel("Broj clanske karte");
    private JTextField txtClanskakarta = new JTextField(20);
    private JLabel lblJmbg = new JLabel("Jmbg");
    private JTextField txtJmbg = new JTextField(20);
    private JLabel lblBrojTelefona= new JLabel("Broj telfona");
    private JTextField txtBrojTelefona = new JTextField(20);
    private JLabel lblAdresa = new JLabel("Adresa");
    private JTextField txtAdresa = new JTextField(20);
    private JLabel lblAutomobili = new JLabel("Automobili");
    private JComboBox<Automobil> comboBoxAutomobil = new JComboBox<Automobil>();

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private TaxiSluzba taxiSluzba;
    private Vozac vozac;

    public DodajIzmeniVozaca(TaxiSluzba taxiSluzba, Vozac vozac){
        this.taxiSluzba = taxiSluzba;
        this.vozac =vozac;

        if (vozac == null){
            setTitle("Dodavanje vozaca");
        }else{
            setTitle("Izmena podataka");
        }

        List<Automobil> slobodniAutomobili = taxiSluzba.pronadjiSlobodneAutomobile();

        for (Automobil a: slobodniAutomobili) {
           comboBoxAutomobil.addItem(a);
        }


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

         if(vozac !=null){
             popuniPolja();
         }

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
         add(txtAdresa);
         add(lblAutomobili);
         add(comboBoxAutomobil);
         add(btnOk);
         add(btnCancel);
     }

     public void initActions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(validacija()){
                    String ime = txtIme.getText().trim();
                    String prezime = txtPrezime.getText().trim();
                    String korisnickoIme = txtKorisnickoIme.getText().trim();
                    String sifra =new String(pfLozinka.getPassword()).trim();
                    Pol pol = (Pol)comboBox.getSelectedItem();
                    String jmbg = txtJmbg.getText().trim();
                    String adresa = txtAdresa.getText().trim();
                    String brojTelefona = txtBrojTelefona.getText().trim();
                    String brojClanskeKarte = txtClanskakarta.getText().trim();
                    Automobil automobil =(Automobil)comboBoxAutomobil.getSelectedItem();
                    Double plata = Double.valueOf(txtPlata.getText().trim());

                    if (vozac == null){//DODAVANJE:
                        taxiSluzba.kreirajVozaca(jmbg,korisnickoIme,sifra,ime,prezime,adresa,pol,brojTelefona,plata,automobil,brojClanskeKarte);
                    }else {//IZMENA:
                        vozac.setJmbg(jmbg);
                        vozac.setLozinka(sifra);
                        vozac.setIme(ime);
                        vozac.setPrezime(prezime);
                        vozac.setAdresa(adresa);
                        vozac.setPol(pol);
                        vozac.setBrojTelefona(brojTelefona);
                        vozac.setPlata(Double.parseDouble(String.valueOf(plata)));
                        vozac.setBrojClanskeKarte(brojClanskeKarte);
                    }
                    TaxiSluzba.sacuvajKorisnike(korisniciFajl);
                    DodajIzmeniVozaca.this.dispose();
                    DodajIzmeniVozaca.this.setVisible(false);
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodajIzmeniVozaca.this.dispose();
                DodajIzmeniVozaca.this.setVisible(false);
            }
        });
     }

     private void popuniPolja(){
        txtIme.setText(vozac.getIme());
        txtPrezime.setText(vozac.getPrezime());
        txtKorisnickoIme.setText(vozac.getKorisnickoIme());
        pfLozinka.setText(vozac.getLozinka());
        comboBox.setSelectedItem(vozac.getPol());
        txtPlata.setText(String.valueOf(vozac.getPlata()));
        txtClanskakarta.setText(vozac.getBrojClanskeKarte());
        txtJmbg.setText(vozac.getJmbg());
        txtBrojTelefona.setText(vozac.getBrojTelefona());
        txtAdresa.setText(vozac.getAdresa());
     }

     private boolean validacija(){
        boolean ok = true;
        String poruka = "Molimo popravite sledece greske u unosu:\n";
        if(txtIme.getText().trim().equals("")){
            poruka += "- Unesite ime\n";
            ok = false;
        }
        if(txtPrezime.getText().trim().equals("")){
            poruka += "- Unesite prezime\n";
            ok = false;
        }
         if(txtKorisnickoIme.getText().trim().equals("")){
             poruka += "- Unesite korisnicko ime\n";
             ok = false;
         }else if (vozac != null){
             String korisnickoIme = txtKorisnickoIme.getText().trim();
             Korisnik pronadjeni = taxiSluzba.pronadjiVozacaPoKorisnickomImenu(korisnickoIme);
             if (pronadjeni == null){
                 poruka += "Vozac sa tim korisnickim imenom vec postoji\n";
                 System.out.println("5");
                 ok = false;
             }
         }
         if(txtAdresa.getText().trim().equals("")){
             poruka += "- Unesite adresu\n";
             ok = false;
         }
         if(txtBrojTelefona.getText().trim().equals("")){
             poruka += "- Unesite broj telefona\n";
             ok = false;
         }
         if(txtJmbg.getText().trim().equals("")){
             poruka += "- Unesite jmbg\n";
             ok = false;
         }

         if (txtClanskakarta.getText().trim().equals("")){
             poruka += "- Unesite broj clanske karte\n";
             ok = false;
         }
         String sifra = new String(pfLozinka.getPassword()).trim();
         if(sifra.equals("")){
             System.out.println("9");
             poruka += "- Unesite sifru\n";
             ok = false;
         }
         if (txtPlata.getText().trim().equals("")){
             poruka += "- Unesite platu\n";
                 ok = false;
         }


         if (ok == false){
             JOptionPane.showMessageDialog(null,poruka,"Neispravni podaci",JOptionPane.WARNING_MESSAGE);
         }
         System.out.println(ok);
        return ok;
     }
}
