package main;

import automobil.Automobil;
import gui.LoginProzor;
import korisnici.*;
import taxiSluzba.TaxiSluzba;
import voznja.StatusVoznje;
import voznja.Voznja;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
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

        List<Automobil> pronadjiSlobodneAutomobile = taxiSluzba.pronadjiSlobodneAutomobile();
        System.out.println(pronadjiSlobodneAutomobile);

        Collections.sort(taxiSluzba.getSviAutomobili());
        Collections.sort(taxiSluzba.getSveVoznje());
        Collections.sort(taxiSluzba.getSviKorisnici());


//        Collections.sort(taxiSluzba.getSviKorisnici());
        for (Korisnik k : taxiSluzba.getSviKorisnici()) {
            System.out.println(k.getKorisnickoIme());
        }

        Korisnik pronadjen = taxiSluzba.binarnaPretragaKorisnikaPoStringu(taxiSluzba.getSviKorisnici(),0,taxiSluzba.getSviKorisnici().size() - 1,"milan");
        if (pronadjen !=null){
            System.out.println(pronadjen.getKorisnickoIme());
        }else {
            System.out.println("Pogresno");
        }

        Voznja pronadjena = taxiSluzba.binarnaPretragaVoznjePoId(taxiSluzba.getSveVoznje(),0,taxiSluzba.getSveVoznje().size()-1,1);
        if (pronadjena !=null){
            System.out.println(pronadjena.getId());
        }else {
            System.out.println("Neuspesno");
        }

        for (Voznja a : taxiSluzba.getSveVoznje()) {
            System.out.println(a.getId());
        }
        Collections.sort(taxiSluzba.getSveVoznje());




        Automobil pronadjeni = taxiSluzba.binarnaPretragaAutomobilaPoId(taxiSluzba.getSviAutomobili(),0,taxiSluzba.getSviAutomobili().size(),8);
        if (pronadjeni !=null){
            System.out.println(pronadjeni);
        }else {
            System.out.println("Neuspeno");
        }



        for (Automobil a : taxiSluzba.getSviAutomobili()) {
            System.out.println(a.getId());
        }
        Collections.sort(taxiSluzba.getSviAutomobili());

        System.out.println("posle sortiranja.....");
        for (Automobil a : taxiSluzba.getSviAutomobili()) {
            System.out.println(a.getId());
        }

        //testirajKombinovanuPretragu(taxiSluzba);
        //testirajIzvestaje(taxiSluzba);



        Scanner scanner = new Scanner(System.in);

        LoginProzor lp = new LoginProzor(taxiSluzba);//taxiSluzba
        lp.setVisible(true);

        testirajKreiranjeVozaca(taxiSluzba, scanner);
        //testirajKreiranjeDispecera(taxiSluzba, scanner);
        testirajKreiranjeVoznje(taxiSluzba, scanner);
        testirajIzmenuVoznje(taxiSluzba, scanner);
        testirajDodeljivanjeVoznjeVozacu(taxiSluzba, scanner);
        //prikazDispecera(taxiSluzba);
       // prikazVozaca(taxiSluzba);
        brisanjeVozaca(taxiSluzba);
        //brisanjeDispecera(taxiSluzba);
        scanner.close();


    }

//
//    public static void testirajIzvestaje(TaxiSluzba taxiSluzba) {
//        LocalDateTime date = LocalDateTime.of(2021, 4, 29, 12, 12);
//        taxiSluzba.godisnjiIzvestaj(2021);
//        taxiSluzba.dnevniIzvestaj(date);
//        taxiSluzba.mesecniIzvestaj(4, 2021);
//        taxiSluzba.nedeljniIzvestaj(18, 2021);
//
//    }
//
//    public static void ispisiSvePodatke(TaxiSluzba taxiSluzba) {
//        for (Korisnik korisnik: taxiSluzba.getSviKorisnici()){
//            System.out.println(korisnik + "\n");
//        }
//        for (Automobil automobil: taxiSluzba.getSviAutomobili()){
//            System.out.println(automobil + "\n");
//        }
//
//        for (Voznja voznja : taxiSluzba.getSveVoznje()) {
//            System.out.println(voznja + "\n");
//            voznja.formatirajZaUpis();
//        }
//    }


//    public static void prikazDispecera(TaxiSluzba taxiSluzba){
//        System.out.println("Prikaz dispecera:");
//        taxiSluzba.prikaziDispecere();
//    }
//
//    public static void prikazVozaca(TaxiSluzba taxiSluzba){
//        System.out.println("Prikaz vozaca:");
//        taxiSluzba.prikaziVozace();
//
//    }

    public static void brisanjeVozaca(TaxiSluzba taxiSluzba){
        System.out.println("Brisanje vozaca......");
        Vozac obrisanVozac = taxiSluzba.obrisiVozaca("32857198357193857");
        if (obrisanVozac != null) {
            System.out.println("Obrisali smo vozaca: " + obrisanVozac.getBrojClanskeKarte());
        } else {
            System.out.println("Ne postoji korisnik sa datim brojem clanske karte");
        }
        taxiSluzba.prikaziVozace();
    }

//    public static void brisanjeDispecera(TaxiSluzba taxiSluzba){
//        System.out.println("Brisanje dispecera");
//        Dispecer obrisanDispecer = taxiSluzba.obrisiDispicera("9999999999");
//        if (obrisanDispecer != null) {
//            System.out.println("Obrisali smo dispecera: " + obrisanDispecer.getJmbg());
//        } else {
//            System.out.println("Ne postoji korisnik sa datim jmbgom");
//        }
//        taxiSluzba.prikaziDispecere();
//    }
//
////    public static void testirajKombinovanuPretragu(TaxiSluzba taxiSluzba) {
////        System.out.println("Kombinovana pretraga");
////
////        System.out.println("Rezultati pretrage: ");
////        ArrayList<Vozac> rezultati = taxiSluzba.kombinovanaPretraga("ana", "");
////        System.out.println(taxiSluzba.kombinovanaPretraga("ana", ""));
////        for (Vozac v : rezultati) {
////            System.out.println(rezultati);
//  //      }
//    }



    public static void testirajKreiranjeVoznje(TaxiSluzba taxiSluzba, Scanner scanner) {
        System.out.println("KREIRANJE VOZNJE");

        System.out.println("Unesite korisnicko ime musterije: ");
        String korisnickoImeMusterije = scanner.nextLine();
        Korisnik pronadjenaMusterija = taxiSluzba.pronadjiMusterijuPoKorisnickomImenu(korisnickoImeMusterije);

        System.out.println("Unesite adresu polaska:");
        String adresaPolaska = scanner.nextLine();

        System.out.println("Unesite adresu destinacije:");
        String adresaDestinacije = scanner.nextLine();

        System.out.println("Unesite broj km: ");
        String brKmString = scanner.nextLine();
        Double brKM = Double.parseDouble(brKmString);

        System.out.println("Unesite trajanje voznje: ");
        String trajanjeVoznjeString = scanner.nextLine();
        Integer trajanjeVoznje = Integer.parseInt(trajanjeVoznjeString);

        System.out.println("Unesite datum u formatu dd-MM-yyyy");
        String datum = scanner.nextLine();
        LocalDateTime datum1 = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        datum1 = LocalDateTime.parse(datum, formatter);
        taxiSluzba.kreirajVoznju(datum1, adresaPolaska, adresaDestinacije, brKM,trajanjeVoznje, pronadjenaMusterija);
    }

//    public static void testirajKreiranjeDispecera(TaxiSluzba taxiSluzba, Scanner scanner) {
//        System.out.println("KREIRANJE DISPECERA");
//
//        System.out.println("Unesite odeljenje: ");
//        String odeljenjeString = scanner.nextLine().toUpperCase();
//        Odeljenje odeljenje =Odeljenje.valueOf(odeljenjeString);
//
//
//        System.out.println("Unesite pol: ");
//        String polString1 = scanner.nextLine().toUpperCase();
//        Pol pol1 = Pol.valueOf(polString1);
//
//        System.out.println("Unesite jmbg: ");
//        String jmbg1 = scanner.nextLine();
//
//        System.out.println("Unesite korisnicko ime: ");
//        String korisnickoIme1 = scanner.nextLine();
//
//        System.out.println("Unesite sifru: ");
//        String sifra1 = scanner.nextLine();
//
//        System.out.println("Unesite  ime: ");
//        String ime1 = scanner.nextLine();
//
//        System.out.println("Unesite prezime: ");
//        String prezime1 = scanner.nextLine();
//
//        System.out.println("Unesite adresa: ");
//        String adresa1 = scanner.nextLine();
//
//        System.out.println("Unesite broj telefona: ");
//        String brojTelefona1 = scanner.nextLine();
//
//        System.out.println("Unesite platu: ");
//        String plataString1 = scanner.nextLine();
//        Double plata1 = Double.parseDouble(plataString1);
//
//        System.out.println("Unesite broj telfonske linije: ");
//        String brojTelefonLinije= scanner.nextLine();
//
//        Dispecer kreiraniDispecer = taxiSluzba.kreirajDispecera(jmbg1,korisnickoIme1,sifra1,ime1,prezime1,adresa1,pol1,brojTelefona1,plata1,brojTelefonLinije,odeljenje);
//        if (kreiraniDispecer == null){
//            System.out.println("Dispecer vec postoji");
//        }
//
//        taxiSluzba.prikaziDispecere();
//        taxiSluzba.sacuvajKorisnike(KORISNICI_FAJL);
//
//    }

    public static void testirajKreiranjeVozaca(TaxiSluzba taxiSluzba, Scanner scanner) {
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

    }

    public static void testirajIzmenuVoznje(TaxiSluzba taxiSluzba, Scanner scanner) {
        System.out.println("IZMENA VOZNJE");
        System.out.println("Uneiste ID voznje koju menjate: ");
        int idVoznje = Integer.parseInt(scanner.nextLine());

        Voznja voznjaZaIzmenu = taxiSluzba.pronadjiVoznjuPoID(idVoznje);
        if (voznjaZaIzmenu == null) {
            System.out.println("Ne postoji voznja sa unetim ID!!!");
            return;
        }

        System.out.println("Unesite korisnicko ime musterije: ");
        String korisnickoImeMusterije = scanner.nextLine();
        Korisnik pronadjenaMusterija = taxiSluzba.pronadjiMusterijuPoKorisnickomImenu(korisnickoImeMusterije);

        if (pronadjenaMusterija == null) {
            System.out.println("Ne postoji musterija sa unetim korisnickim imenom");
            return;
        }

        System.out.println("Unesite adresu polaska:");
        String adresaPolaska = scanner.nextLine();

        System.out.println("Unesite adresu destinacije:");
        String adresaDestinacije = scanner.nextLine();

        System.out.println("Unesite broj km: ");
        String brKmString = scanner.nextLine();
        Double brKM = Double.parseDouble(brKmString);

        System.out.println("Unesite trajanje voznje: ");
        String trajanjeVoznjeString = scanner.nextLine();
        Integer trajanjeVoznje = Integer.parseInt(trajanjeVoznjeString);

        System.out.println("Unesite korisnicko ime vozaca: ");
        String imeVozaca = scanner.nextLine();
        Vozac pronadjenjnVozac = taxiSluzba.pronadjiVozacaPoKorisnickomImenu(imeVozaca);

        if (pronadjenjnVozac == null) {
            System.out.println("Ne postoji vozac sa unetim korisnickim imenom!!!");
            return;
        }

        System.out.println("Unesite datum u formatu dd-MM-yyyy");
        String datum = scanner.nextLine();
        LocalDateTime datum1 = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        datum1 = LocalDateTime.parse(datum, formatter);

        taxiSluzba.izmeniVoznju(idVoznje, datum1, adresaPolaska, adresaDestinacije, StatusVoznje.ODBIJENA, brKM, trajanjeVoznje, pronadjenaMusterija, pronadjenjnVozac);
    }

    public static void testirajDodeljivanjeVoznjeVozacu(TaxiSluzba taxiSluzba, Scanner scanner) {
        System.out.println("Dodeljivanje voznje vozacu...");
        System.out.println("Unesite ID voznje: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Unesite korisnicko ime vozaca: ");
        String korisnickoImeVozaca = scanner.nextLine();

        taxiSluzba.dodeliVoznjuVozacu(korisnickoImeVozaca, id);

    }

    public static void testiraOdbijVoznju(TaxiSluzba taxiSluzba,Scanner scanner){
        System.out.println("Odbijanje voznje:");
        System.out.println("Unesite id voznje:");
        int id = Integer.parseInt(scanner.nextLine());

        taxiSluzba.odbijVoznju(id);
    }
}
// TRUE && TRUE = TRUE
// TRUE && FALSE = FALSE
// FLASAE && TRUE = FALSE
// FALSE && FALSE = FALSE

// TRUE || TRUE = TRUE
// TRUE || FALSE = TRUE
// FALSE || TRUE = TRUE
// FALSE || FALSE = FALSE