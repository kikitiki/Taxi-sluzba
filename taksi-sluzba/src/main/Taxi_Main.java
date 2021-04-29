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
        //taxiSluzba.sacuvajVoznje(VOZNJE_FAJL);
        System.out.println("PODACI UCITANI IZ DATOTEKA:");
        System.out.println("----------------------------------------------");
        ispisiSvePodatke(taxiSluzba);
        System.out.println("----------------------------------------------");






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
