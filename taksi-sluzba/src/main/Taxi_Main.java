package main;

import automobil.Automobil;
import gui.LoginProzor;
import korisnici.*;
import taxiSluzba.TaxiSluzba;
import voznja.Voznja;

import java.util.*;

public class Taxi_Main {
    public static String AUTOMOBILI_FAJL = "taksi-sluzba/src/fajlovi/automobili.txt";
    public static String VOZNJE_FAJL = "taksi-sluzba/src/fajlovi/voznje.txt";
    public static String KORISNICI_FAJL = "taksi-sluzba/src/fajlovi/korisnici.txt";

    public static void main(String[] args) {


        TaxiSluzba taxiSluzba = new TaxiSluzba(AUTOMOBILI_FAJL, VOZNJE_FAJL, KORISNICI_FAJL);


        taxiSluzba.ucitajAutomobile(AUTOMOBILI_FAJL);
        taxiSluzba.ucitajKorisnike(KORISNICI_FAJL);
        taxiSluzba.ucitajVoznje(VOZNJE_FAJL);
        taxiSluzba.sacuvajKorisnike(KORISNICI_FAJL);
        taxiSluzba.sacuvajAutomobile(AUTOMOBILI_FAJL);
        taxiSluzba.sacuvajVoznje(VOZNJE_FAJL);

        LoginProzor lp = new LoginProzor(taxiSluzba);
        lp.setVisible(true);

        for (Voznja v : taxiSluzba.getSveVoznje()) {
            System.out.println(v);
        }



        Scanner scanner = new Scanner(System.in);




        System.out.println("Prikaz dispecera:");
        taxiSluzba.prikaziDispecere();

        System.out.println("Prikaz vozaca:");
        taxiSluzba.prikaziVozace();

        System.out.println("Brisanje vozaca......");
        Vozac obrisanVozac = taxiSluzba.obrisiVozaca("32857198357193857");
        if (obrisanVozac != null) {
            System.out.println("Obrisali smo vozaca: " + obrisanVozac.getBrojClanskeKarte());
        } else {
            System.out.println("Ne postoji korisnik sa datim brojem clanske karte");
        }
        taxiSluzba.prikaziVozace();

        System.out.println("Brisanje dispecera");
        Dispecer obrisanDispecer = taxiSluzba.obrisiDispicera("1111111111111");
        if (obrisanDispecer !=null){
            System.out.println("Obrisali smo dispecera: " + obrisanDispecer.getJmbg());
        }else {
            System.out.println("Ne postoji korisnik sa datim jmbgom");
        }
        taxiSluzba.prikaziDispecere();


        System.out.println("KREIRANJE VOZACA");

        System.out.println("Unesite jmbg: ");
        String jmbg = scanner.nextLine();

        System.out.println("Unesite korisnicko ime: ");
        String korisnickoIme = scanner.nextLine();

        System.out.println("Unesite sifru: ");
        String sifra = scanner.nextLine();

        System.out.println("Unesite  ime: ");
        String ime = scanner.nextLine();

        System.out.println("Unesite prezime: ");
        String prezime = scanner.nextLine();

        System.out.println("Unesite pol: ");
        String polString = scanner.nextLine().toUpperCase();
        Pol pol = Pol.valueOf(polString);

        System.out.println("Unesite platu: ");
        String plataString = scanner.nextLine();
        Double plata = Double.parseDouble(plataString);


        System.out.println("Unesite adresu: ");
        String adresa = scanner.nextLine();

        System.out.println("Unesite broj telefona: ");
        String brojTelefona = scanner.nextLine();

        System.out.println("Unesite broj clanske karte: ");
        String brojClanskeKarte= scanner.nextLine();

        System.out.println("Unesite korisnicko ime vozaca: ");
        String korisnickoImeVozaca = scanner.nextLine();
        Vozac pronadjeniVozac = taxiSluzba.pronadjiVozacaPoKorisnickomImenu(korisnickoImeVozaca);


        int idAutomobila = 7;

        Automobil pronadjenAutomobil = taxiSluzba.pronadjiAutomobilPoID(idAutomobila);

        if (pronadjenAutomobil == null) {
            System.out.println("neka greska se desila, ne mozemo da pronadjemo automobil sa ID=" + idAutomobila);
        }


        Vozac kreiraniVozac = taxiSluzba.kreirajVozaca(jmbg,korisnickoImeVozaca, sifra, ime, prezime, adresa, pol, brojTelefona, plata, pronadjenAutomobil, brojClanskeKarte);
        if (kreiraniVozac == null) {
            System.out.println("Oops, greska");
        }

        taxiSluzba.prikaziVozace();
        taxiSluzba.sacuvajKorisnike(KORISNICI_FAJL);




        //KREIRANJE DISPECERA
        System.out.println("KREIRANJE DISPECERA");

        System.out.println("Unesite odeljenje: ");
        String odeljenjeString = scanner.nextLine().toUpperCase();
        Odeljenje odeljenje =Odeljenje.valueOf(odeljenjeString);


        System.out.println("Unesite pol: ");
        String polString1 = scanner.nextLine().toUpperCase();
        Pol pol1 = Pol.valueOf(polString1);

        System.out.println("Unesite jmbg: ");
        String jmbg1 = scanner.nextLine();

        System.out.println("Unesite korisnicko ime: ");
        String korisnickoIme1 = scanner.nextLine();

        System.out.println("Unesite sifru: ");
        String sifra1 = scanner.nextLine();

        System.out.println("Unesite  ime: ");
        String ime1 = scanner.nextLine();

        System.out.println("Unesite prezime: ");
        String prezime1 = scanner.nextLine();

        System.out.println("Unesite adresa: ");
        String adresa1 = scanner.nextLine();

        System.out.println("Unesite broj telefona: ");
        String brojTelefona1 = scanner.nextLine();

        System.out.println("Unesite platu: ");
        String plataString1 = scanner.nextLine();
        Double plata1 = Double.parseDouble(plataString1);

        System.out.println("Unesite broj telfonske linije: ");
        String brojTelefonLinije= scanner.nextLine();

        Dispecer kreiraniDispecer = taxiSluzba.kreirajDispecera(jmbg1,korisnickoIme1,sifra1,ime1,prezime1,adresa1,pol1,brojTelefona1,plata1,brojTelefonLinije,odeljenje);
        if (kreiraniDispecer == null){
            System.out.println("Dispecer vec postoji");
        }

        taxiSluzba.prikaziDispecere();
        taxiSluzba.sacuvajKorisnike(KORISNICI_FAJL);

        scanner.close();

    }


    public static void ispisiSvePodatke(TaxiSluzba taxiSluzba) {
        for (Korisnik korisnik: taxiSluzba.getSviKorisnici()){
            System.out.println(korisnik + "\n");
        }
        for (Automobil automobil: taxiSluzba.getSviAutomobili()){
            System.out.println(automobil + "\n");
        }

        for (Voznja voznja : taxiSluzba.getSveVoznje()) {
            System.out.println(voznja + "\n");
            voznja.formatirajZaUpis();
        }

    }
}
