package gui;

import korisnici.Korisnik;
import korisnici.Vozac;
import taxiSluzba.TaxiSluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VozaciProzor extends JFrame {
    private JMenuBar mainMenu = new JMenuBar();
    private JButton sveVoznje = new JButton("Sve voznje");
    private JButton aukcija = new JButton("Aukcija");
    private JButton btnLogout = new JButton("Logout");
    private TaxiSluzba taxiSluzba;
    private Korisnik ulogovaniKorisnik;

    public VozaciProzor( Korisnik ulogovani, TaxiSluzba taxiSluzba){
        this.taxiSluzba = taxiSluzba;
        this.ulogovaniKorisnik = ulogovani;
        //voznjaVozaca(ulogovani.getKorisnickoIme());
        setTitle("Dobrodosli ulogovani ste kao vozac" + ulogovani.getKorisnickoIme() );
        setSize(300,300);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        setLocationRelativeTo(null);
        initMenu();
        initActions();
    }

    private void initMenu(){
        setJMenuBar(mainMenu);
        mainMenu.add(sveVoznje);
        mainMenu.add(aukcija);
        mainMenu.add(btnLogout);
    }
    private void initActions(){

        sveVoznje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VoznjaTabelaVozac vv = new VoznjaTabelaVozac(taxiSluzba, ulogovaniKorisnik);
                vv.setVisible(true);
            }
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VozaciProzor.this.dispose();
                LoginProzor loginProzor = new LoginProzor(taxiSluzba);
                loginProzor.setVisible(true);
            }
        });

        aukcija.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Aukcija aukcija = new Aukcija(taxiSluzba, (Vozac) ulogovaniKorisnik);
                aukcija.setVisible(true);
            }
        });


    }
}
