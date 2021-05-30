package gui;

import korisnici.Korisnik;
import taxiSluzba.TaxiSluzba;

import javax.swing.*;

public class GlavniProzor extends JFrame {
   private JMenuBar mainMenu= new JMenuBar();
   private JMenu pregled = new JMenu("Pregled");
   private JMenuItem vozaci = new JMenu("Vozaci");
   private JMenuItem dispeceri = new JMenuItem("Dispeceri");
   private JMenuItem voznje = new JMenuItem("Voznje");

   private TaxiSluzba taxiSluzba;
   private Korisnik prijavljeniKorisnik;

   public GlavniProzor(TaxiSluzba taxiSluzba,Korisnik prijavljeniKorisnik){
       this.taxiSluzba = taxiSluzba;
       this.prijavljeniKorisnik = prijavljeniKorisnik;
       setTitle("Korisnik: " + prijavljeniKorisnik.getKorisnickoIme());
       setSize(500,500);
       setResizable(false);
       setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       setLocationRelativeTo(null);
       initMenu();
       initActi0ons();
   }

   private void initMenu(){
        setJMenuBar(mainMenu);
        mainMenu.add(pregled);
        pregled.add(vozaci);
        pregled.add(dispeceri);
   }

   private void initActi0ons(){

   }
}
