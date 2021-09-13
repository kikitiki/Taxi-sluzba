package gui;

import korisnici.Korisnik;
import korisnici.TipKorisnika;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginProzor extends JFrame{
    private JLabel lblPozdrav = new JLabel("Dobrodosli,molimo vas da se prijavite");
    private JLabel lbKKorisnisnickoIme = new JLabel("Korisnicko ime:");
    private JTextField txtKorisnickoIme = new JTextField(20);
    private JLabel lblSifra = new JLabel("Sifra");
    private JPasswordField pfSifra = new JPasswordField(20);
    private JButton bntOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private TaxiSluzba taxiSluzba;

    public LoginProzor(TaxiSluzba taxiSluzba){
        this.taxiSluzba = taxiSluzba;
        setTitle("Prijava");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGUI();
        initActions();
        pack();
    }

    public void initGUI(){
        MigLayout mig = new MigLayout("wrap 2","[][]","[]10[][]10[]");
        setLayout(mig);

        add(lblPozdrav,"span 2");
        add(lbKKorisnisnickoIme);
        add(txtKorisnickoIme);
        add(lblSifra);
        add(pfSifra);
        add(new JLabel());
        add(bntOk,"split 2");
        add(btnCancel);

        getRootPane().setDefaultButton(bntOk);
    }

    public  void initActions(){
        bntOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String unetoKorisnickoIme = txtKorisnickoIme.getText().trim();
                String unetaSifra = new String(pfSifra.getPassword()).trim();

                if (unetoKorisnickoIme.equals("") || unetaSifra.equals("")) {
                    JOptionPane.showMessageDialog(null, "Niste uneli sve podatke!", "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    Korisnik ulogovaniKorisnik = taxiSluzba.login(unetoKorisnickoIme, unetaSifra);
                    if (ulogovaniKorisnik == null) {
                        JOptionPane.showMessageDialog(null, "Pogresni kredencijali!", "Greska", JOptionPane.ERROR_MESSAGE);
                    } else {
                        LoginProzor.this.dispose();
                        LoginProzor.this.setVisible(false);
                        GlavniProzor gp = new GlavniProzor(ulogovaniKorisnik, taxiSluzba);
                        gp.setVisible(true);

                    }
                }
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginProzor.this.dispose();
                LoginProzor.this.setVisible(false);
            }
        });
    }
}
