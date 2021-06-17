package gui;

import korisnici.Korisnik;
import korisnici.Vozac;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;
import voznja.StatusVoznje;
import voznja.TipKreiraneVoznje;
import voznja.Voznja;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import static main.Taxi_Main.VOZNJE_FAJL;
import static taxiSluzba.TaxiSluzba.kreirajVoznju;

public class MusterijaProzor extends JFrame {

    private JLabel lblAdresaPolaska = new JLabel("Adresa polaska:");
    private JTextField txtAdresaPolaska = new JTextField(20);
    private JLabel lblAdresaDolaska = new JLabel("Adresa dolaska:");
    private JTextField txtAdresaDolaska = new JTextField(20);
    private JButton btnPoruci = new JButton("Poruci:");

    public MusterijaProzor( Korisnik ulogovani){

        setTitle("Dobrodosli ulogovani ste kao korisnik" + ulogovani.getKorisnickoIme() );
        setSize(300,300);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        setLocationRelativeTo(null);
        initMenu();
        initActions(ulogovani);

    }



    private void initMenu(){

        MigLayout mig = new MigLayout("wrap 2","[][]","35[]25[]25[]25[]");
        setLayout(mig);


        add(lblAdresaPolaska);
        add(txtAdresaPolaska);
        add(lblAdresaDolaska);
        add(txtAdresaDolaska);
        add(btnPoruci);


    }

    private void initActions(Korisnik ulogovani){
        btnPoruci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String adreasaPolaska = txtAdresaPolaska.getText().trim();
                String adresaDolaska = txtAdresaDolaska.getText().trim();

                kreirajVoznju(LocalDateTime.now(),adreasaPolaska,adresaDolaska,
                        0.0,0,ulogovani);
                JOptionPane.showMessageDialog(null, "Uspesno ste porucili voznju",
                        "Uspesno", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

}
