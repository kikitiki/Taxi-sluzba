package main;

import automobil.Automobil;
import korisnici.*;
import taxiSluzba.TaxiSluzba;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Taxi_Main {
    public static String AUTOMOBILI_FAJL = "taksi-sluzba/src/fajlovi/automobili.txt";
    public static String VOZNJE_FAJL = "taksi-sluzba/src/fajlovi/voznje.txt";
    public static String KORISNICI_FAJL = "taksi-sluzba/src/fajlovi/korisnici.txt";

    public static void main(String[] args){

        TaxiSluzba taxiSluzba = new TaxiSluzba();
        taxiSluzba.ucitajAutomobile(AUTOMOBILI_FAJL);
        taxiSluzba.ucitajKorisnike(KORISNICI_FAJL);


        //taxiSluzba.ucitajVoznje(VOZNJE_FAJL);

        taxiSluzba.sacuvajKorisnike(KORISNICI_FAJL);
    }

}
