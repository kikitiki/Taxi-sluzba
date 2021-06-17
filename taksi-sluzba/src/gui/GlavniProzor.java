package gui;

import korisnici.Korisnik;
import korisnici.TipKorisnika;
import taxiSluzba.TaxiSluzba;

import javax.swing.*;

public class GlavniProzor extends JFrame {

    private TaxiSluzba taxiSluzba;

    public GlavniProzor(Korisnik ulogovani, TaxiSluzba taxiSluzba) {
        this.taxiSluzba = taxiSluzba;
        if (ulogovani.getTipKorisnika().equals(TipKorisnika.DISPECER)) {
            DispecerProzor dp;
            dp = new DispecerProzor(this.taxiSluzba);
            dp.setVisible(true);

        } else if (ulogovani.getTipKorisnika().equals(TipKorisnika.MUSTERIJA)) {
            if (ulogovani.getTipKorisnika().equals(TipKorisnika.MUSTERIJA)) {
                MusterijaProzor mp;
                mp = new MusterijaProzor(ulogovani);
                mp.setVisible(true);
            }
        }else {
            VozaciProzor vp;
            vp = new VozaciProzor(ulogovani, taxiSluzba);
            vp.setVisible(true);

        }
    }
}
