package gui;

import korisnici.Korisnik;
import korisnici.TipKorisnika;
import taxiSluzba.TaxiSluzba;

import javax.swing.*;

public class GlavniProzor extends JFrame {

    public GlavniProzor(Korisnik ulogovani) {
        if (ulogovani.getTipKorisnika().equals(TipKorisnika.DISPECER)) {
            DispecerProzor dp;
            dp = new DispecerProzor();
            dp.setVisible(true);

        } else if (ulogovani.getTipKorisnika().equals(TipKorisnika.MUSTERIJA)) {
            if (ulogovani.getTipKorisnika().equals(TipKorisnika.MUSTERIJA)) {
                MusterijaProzor mp;
                mp = new MusterijaProzor(ulogovani);
                mp.setVisible(true);
            }
        }
    }
}
