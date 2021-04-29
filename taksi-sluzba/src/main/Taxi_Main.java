package main;

import automobil.Automobil;
import automobil.VrstaAutomobila;
import korisnici.*;
import taxiSluzba.TaxiSluzba;
import voznja.StatusVoznje;
import voznja.Voznja;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Taxi_Main {
    public static String AUTOMOBILI_FAJL = "taksi-sluzba/src/fajlovi/automobili.txt";
    public static String VOZNJE_FAJL = "taksi-sluzba/src/fajlovi/voznje.txt";
    public static String KORISNICI_FAJL = "taksi-sluzba/src/fajlovi/korisnici.txt";

    public static void main(String[] args) {


        TaxiSluzba taxiSluzba = new TaxiSluzba();

        taxiSluzba.ucitajAutomobile(AUTOMOBILI_FAJL);
        taxiSluzba.ucitajKorisnike(KORISNICI_FAJL);
        taxiSluzba.ucitajVoznje(VOZNJE_FAJL);
        taxiSluzba.sacuvajKorisnike(KORISNICI_FAJL);
        taxiSluzba.sacuvajAutomobile(AUTOMOBILI_FAJL);
        taxiSluzba.sacuvajVoznje(VOZNJE_FAJL);
        System.out.println("PODACI UCITANI IZ DATOTEKA:");
        System.out.println("----------------------------------------------");
        ispisiSvePodatke(taxiSluzba);
        System.out.println("----------------------------------------------");

        System.out.println("Dodavanje test podataka...");

        Automobil auto = new Automobil("998876", "model1", "proizvodjac1", "ZR123PG", VrstaAutomobila.PUTNICKO_VOZILO, 1999);

        taxiSluzba.getSviAutomobili().add(auto);
        taxiSluzba.sacuvajAutomobile(AUTOMOBILI_FAJL);

        Korisnik korisnik = new Korisnik("16129886250432","iva","iva456","Iva","Ivac","Lozionicka 1",Pol.ZENSKI,"06412345678",TipKorisnika.MUSTERIJA);
        taxiSluzba.getSviKorisnici().add(korisnik);
        taxiSluzba.sacuvajKorisnike(KORISNICI_FAJL);

        Dispecer dispecer = new Dispecer("1111111111111","mate","matija111","Matija","Matic","Vrsacka 15",Pol.MUSKI,"064123789111",45789.00,"tri",Odeljenje.ODELJENJE_ZA_PRIJEM_VOZNJE,TipKorisnika.DISPECER);
        taxiSluzba.getSviKorisnici().add(dispecer);
        taxiSluzba.sacuvajKorisnike(KORISNICI_FAJL);

        Date datumKreiranja = new Date();
        Korisnik musterija = taxiSluzba.getSviKorisnici().get(0);
        Vozac vozac = (Vozac) taxiSluzba.getSviKorisnici().get(1);
        Voznja voznja = new Voznja(datumKreiranja, "adresa polaska", "destiancija", StatusVoznje.KREIRANA, 50.0, 30, musterija, vozac);
        taxiSluzba.getSveVoznje().add(voznja);


        Date datumKreiranja1 = new Date();
        Korisnik musterija1 = taxiSluzba.getSviKorisnici().get(3);
        Vozac vozac1 = (Vozac)taxiSluzba.getSviKorisnici().get(1);
        Voznja voznja1 = new Voznja(datumKreiranja1,"Kisacka 15","Vrsacka 11",StatusVoznje.KREIRANA_NA_CEKANJU,15.15,20,musterija1,vozac1);
        taxiSluzba.getSveVoznje().add(voznja1);

        taxiSluzba.sacuvajVoznje(VOZNJE_FAJL);
        taxiSluzba.sacuvajVoznje(VOZNJE_FAJL);

        

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
