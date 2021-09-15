package taxiSluzba;

import automobil.Automobil;
import automobil.VrstaAutomobila;
import korisnici.*;
import voznja.StatusVoznje;
import voznja.TipKreiraneVoznje;
import voznja.Voznja;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.*;

import static gui.PronadjeneVoznje.pronadjeneVoznje;

public class TaxiSluzba {

    private static List<Korisnik> sviKorisnici = new ArrayList<Korisnik>();
    private static List<Automobil> sviAutomobili = new ArrayList<Automobil>();
    private static List<Voznja> sveVoznje = new ArrayList<Voznja>();
    private static List idAutomobili = new ArrayList();
    private static List<String> korisnickaImena = new ArrayList<>();
    private static final double CENA_START = 100.0;
    private static final double CENA_PO_KM = 50.0;

    public static String automobiliFajl;
    public static String voznjeFajl;
    public static String korisniciFajl;

    public TaxiSluzba() {

    }

    public TaxiSluzba(String automobiliFajl, String voznjeFajl, String korisniciFajl) {
        this.automobiliFajl = automobiliFajl;
        this.voznjeFajl = voznjeFajl;
        this.korisniciFajl = korisniciFajl;
    }

    public TaxiSluzba(List<Korisnik> sviKorisnici, List<Automobil> sviAutomobili, List<Voznja> sveVoznje) {
        this.sviKorisnici = sviKorisnici;
        this.sviAutomobili = sviAutomobili;
        this.sveVoznje = sveVoznje;
    }

    public static List<Korisnik> getSviKorisnici() {
        return sviKorisnici;
    }

    public void setSviKorisnici(List<Korisnik> sviKorisnici) {
        this.sviKorisnici = sviKorisnici;
    }

    public List<Automobil> getSviAutomobili() {
        return sviAutomobili;
    }

    public void setSviAutomobili(List<Automobil> sviAutomobili) {
        this.sviAutomobili = sviAutomobili;
    }

    public static List<Voznja> getSveVoznje() {
        return sveVoznje;
    }

    public void setSveVoznje(List<Voznja> sveVoznje) {
        this.sveVoznje = sveVoznje;
    }

    public void ucitajKorisnike(String nazivFajla) {
        File file = new File(nazivFajla);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {

                // ako ucitamo liniju koja je prazna -> prekini ucitavanje i izadji iz While petlje
                if (line.equals("")) {
                    break;
                }
                String[] splitovano = line.split("\\|");

                String ime = splitovano[0];
                String prezime = splitovano[1];
                String korisnickoIme = splitovano[2];
                String sifra = splitovano[3];
                String adresa = splitovano[4];
                String jmbg = splitovano[5];
                String polString = splitovano[6];
                String brojTelefona = splitovano[7];
                String tipKorisnikaString = splitovano[8];
                String obrisanString = splitovano[9];
                Pol pol = Pol.valueOf(polString);
                TipKorisnika tipKorisnika = TipKorisnika.valueOf(tipKorisnikaString);

                Boolean obrisan = Boolean.valueOf(obrisanString);

                if (tipKorisnika.equals(TipKorisnika.MUSTERIJA)) {
                    Korisnik korisnik = new Korisnik(jmbg, korisnickoIme, sifra, ime, prezime, adresa, pol, brojTelefona, tipKorisnika, obrisan);
                    sviKorisnici.add(korisnik);
                } else if (tipKorisnika.equals(TipKorisnika.DISPECER)) {
                    String plataString = splitovano[10];
                    String brojTelefonaLinije = splitovano[11];
                    String odeljenjeString = splitovano[12];
                    Odeljenje odeljenje = Odeljenje.valueOf(odeljenjeString);
                    double plata = Double.parseDouble(plataString);
                    Korisnik korisnik = new Dispecer(jmbg, korisnickoIme, sifra, ime, prezime, adresa, pol, brojTelefona, plata, brojTelefonaLinije, odeljenje, tipKorisnika, obrisan);
                    sviKorisnici.add(korisnik);
                } else {
                    String brojClanskeKarte = splitovano[10];
                    String plataString = splitovano[11];
                    String taxiString = splitovano[12];

                    double plata = Double.parseDouble(plataString);

                    Collections.sort(sviAutomobili);

                    Automobil taxi = null;
                    for (Automobil automobil : sviAutomobili) {
                        if (automobil.getBrojTaksiVozila().equals(taxiString)) {
                            taxi = automobil;
                        }
                    }

                    Korisnik korisnik = new Vozac(jmbg, korisnickoIme, sifra, ime, prezime, adresa, pol, brojTelefona, brojClanskeKarte, plata, taxi, tipKorisnika, obrisan);
                    sviKorisnici.add(korisnik);
                }

            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Greska prilikom snimanja podataka o korisnicima");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void ucitajVoznje(String nazivFajla) {
        File file = new File(nazivFajla);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {

                if (line.equals("")) {
                    break;
                }

                String[] splitovano = line.split("\\|");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                String idString = splitovano[0];
                String datum = splitovano[1];

                LocalDateTime date = LocalDateTime.parse(datum, formatter);
                String adresaPolaska = splitovano[2];
                String adresaDestinacije = splitovano[3];
                String statusVoznjeString = splitovano[4];
                String brKmString = splitovano[5];
                String trajanjeVoznjeString = splitovano[6];
                // ucitavamo ID-eve musterije i vozaca
                String musterijaKorisnickoIme = splitovano[7];
                String vozacKorisnickoIme = splitovano[8];
                StatusVoznje statusVoznje = StatusVoznje.valueOf(statusVoznjeString);
                String tipKreiraneVoznjeString = splitovano[9];
                TipKreiraneVoznje tipKreiraneVoznje = TipKreiraneVoznje.valueOf(tipKreiraneVoznjeString);
                String obrisanString = splitovano[10];
                int id = Integer.parseInt(idString);
                double brKm = Double.parseDouble(brKmString);
                int trajanje = Integer.parseInt(trajanjeVoznjeString);
                Boolean obrisan = Boolean.valueOf(obrisanString);

                Korisnik musterija = null;
                Vozac vozac = null;

                for (Korisnik korisnik : sviKorisnici) {
                    if (korisnik.getKorisnickoIme().equalsIgnoreCase(musterijaKorisnickoIme)) {
                        musterija = korisnik;
                    } else if (korisnik.getKorisnickoIme().equalsIgnoreCase(vozacKorisnickoIme)) {
                        vozac = (Vozac) korisnik;
                    }
                }

                Voznja voznja = new Voznja(id, date, adresaPolaska, adresaDestinacije, statusVoznje, brKm, trajanje, musterija, vozac, tipKreiraneVoznje, obrisan);
                sveVoznje.add(voznja);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Greska prilikom snimanja podataka o voznji");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ucitajAutomobile(String nazivFajla) {
        File file = new File(nazivFajla);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.equals("")) {
                    break;
                }

                String[] splitovano = line.split("\\|");
                String idString = splitovano[0];
                String brojTaxiVozila = splitovano[1];
                String model = splitovano[2];
                String proizvodjac = splitovano[3];
                String registracija = splitovano[4];
                String tipVozilaString = splitovano[5];
                VrstaAutomobila tipVozila = VrstaAutomobila.valueOf(tipVozilaString);
                String godinaProizvodnjeString = splitovano[6];
                String obrisanString = splitovano[7];
                int godinaProizvodnje = Integer.parseInt(godinaProizvodnjeString);
                int id = Integer.parseInt(idString);
                Boolean obrisan = Boolean.valueOf(obrisanString);
                Automobil automobil = new Automobil(id, brojTaxiVozila, model, proizvodjac, registracija, tipVozila, godinaProizvodnje, obrisan);
                sviAutomobili.add(automobil);
            }

            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Greska prilikom snimanja podataka o automobilu");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void sacuvajKorisnike(String nazivFajla) {
        File file = new File(nazivFajla);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String zaIspis = "";
            for (Korisnik korisnik : sviKorisnici) {
                if (korisnik.getTipKorisnika().equals(TipKorisnika.MUSTERIJA)) {
                    zaIspis = zaIspis + korisnik.formatrajZaUpis() + "\n";
                } else if (korisnik.getTipKorisnika().equals(TipKorisnika.DISPECER)) {
                    Dispecer dispecer = (Dispecer) korisnik;
                    zaIspis = zaIspis + dispecer.formatrajZaUpis() + "\n";
                } else {
                    Vozac vozac = (Vozac) korisnik;
                    zaIspis = zaIspis + vozac.formatrajZaUpis() + "\n";
                }
            }

            writer.write(zaIspis);
            writer.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom cuvanja podataka o korisniku");
            e.printStackTrace();
        }
    }


    public void sacuvajAutomobile(String nazivFajla) {
        File file = new File(nazivFajla);
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String zaIspis = "";
            for (Automobil automobil : sviAutomobili) {
                zaIspis = zaIspis + automobil.formatirajZaUpisAutomobila() + "\n";
            }

            writer.write(zaIspis);
            writer.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom cuvanja podatak o automobilima");
            e.printStackTrace();
        }

    }

    public static void sacuvajVoznje(String nazivFajla) {
        File file = new File(nazivFajla);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String zaIspis = "";
            for (Voznja voznja : sveVoznje) {
                zaIspis = zaIspis + voznja.formatirajZaUpis() + "\n";
            }

            writer.write(zaIspis);
            writer.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom cuvanja podataka o voznji");
            e.printStackTrace();
        }

    }

    public Korisnik login(String korisnickoIme, String sifra) {

        for (Korisnik k : sviKorisnici) {
            if (k.getKorisnickoIme().equalsIgnoreCase(korisnickoIme) && k.getLozinka().equals(sifra) && !k.isObrisan()) {
                System.out.println("Uspesno ste se ulogovali");
                return k;
            }
        }
        System.out.println("Ne postoji korisnik sa unetim kredencijalima");
        return null;
    }

    //CRUD ZA VOZACA
    public void prikaziVozace() {
        List<Vozac> vozaci = new ArrayList<Vozac>();
        for (Korisnik k : sviKorisnici) {
            if (k.getTipKorisnika().equals(TipKorisnika.VOZAC) && !k.isObrisan()) {
                vozaci.add((Vozac) k);
            }
        }

        for (Vozac v : vozaci) {
            System.out.println(v);
        }
    }

    public static ArrayList<Vozac> dobaviVozace() {
        ArrayList<Vozac> sviVozaci = new ArrayList<Vozac>();
        for (Korisnik k : sviKorisnici) {
            if (k.getTipKorisnika().equals(TipKorisnika.VOZAC) && !k.isObrisan()) {
                sviVozaci.add((Vozac) k);
            }
        }
        return sviVozaci;
    }

    public static Vozac pronadjiVozacaIzTabele(String jmbg){
        for (Korisnik k : sviKorisnici) {
            if (k.getJmbg().equals(jmbg) && !k.isObrisan()) {
               return (Vozac) k  ;
            }
        }
        return null;
    }



    public static Vozac obrisiVozaca(String brojClanskeKarte) {
        for (Korisnik k : sviKorisnici) {
            if (k.getTipKorisnika().equals(TipKorisnika.VOZAC) && !k.isObrisan()) {
                Vozac vozac = (Vozac) k;
                if (vozac.getBrojClanskeKarte().equalsIgnoreCase(brojClanskeKarte)) {
                    vozac.setObrisan(true);
                    sacuvajKorisnike(korisniciFajl);
                    return vozac;
                }
            }
        }

        return null;
    }




    public List<Automobil> pronadjiSlobodneAutomobile() {
        List<Automobil> sviSlobodniAutomobili = new ArrayList<Automobil>();
        List<Vozac> sviVozaci = dobaviVozace();
        for (Automobil a : sviAutomobili) {
            if (a.isObrisan() == true) {
                continue;
            }
            boolean automobilSlobodan = true;
            for (Vozac v : sviVozaci) {
                if (v.getTaxi().getBrojTaksiVozila().equals(a.getBrojTaksiVozila())) {
                    automobilSlobodan =false;
                    break;
                }
            }
            if (automobilSlobodan) {
                sviSlobodniAutomobili.add(a);
            }
        }
        return sviSlobodniAutomobili;
    }

    public Vozac kreirajVozaca(String jmbg,String korisnickoIme,String lozinka,String ime, String prezime,String adresa,Pol pol,String brojtelefona,double plata,Automobil taxi,String brojClanskeKarte) {
        TipKorisnika tipKorisnika = TipKorisnika.VOZAC;
        Vozac noviVozac = new Vozac(jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojtelefona, brojClanskeKarte, plata, taxi, tipKorisnika);

        for (Korisnik k : sviKorisnici) {
            if (noviVozac.getJmbg().equals(k.getJmbg())) {
                System.out.println("Korisnik sa datim JMBG vec postoji");
                return null;
            } else if (noviVozac.getKorisnickoIme().equalsIgnoreCase(k.getKorisnickoIme())) {
                System.out.println("Vozac sa ovim korisnickim imenom vec postoji");
                return null;
            } else if (k.getTipKorisnika().equals(TipKorisnika.VOZAC)) {
                Vozac trenutniVozac = (Vozac) k;
                if (noviVozac.getBrojClanskeKarte().equalsIgnoreCase(trenutniVozac.getBrojClanskeKarte())) {
                    System.out.println("Postoji korisnik sa prosledjenim brojem clanse karte!");
                    return null;
                } else if (noviVozac.getTaxi().getId() == trenutniVozac.getTaxi().getId()) {
                    System.out.println("Vec postoji vozac koji vozi ovo taxi vozilo!!!!");
                    return null;
                }
            }
        }
        sviKorisnici.add(noviVozac);
        Collections.sort(sviKorisnici);
        sacuvajKorisnike(korisniciFajl);
        return noviVozac;
    }




    //CRUD ZA DISPECERA
    public void prikaziDispecere() {
        List<Dispecer> dispecer = new ArrayList<>();
        for (Korisnik k : sviKorisnici){
            if (k.getTipKorisnika().equals(TipKorisnika.DISPECER) && !k.isObrisan()){
                dispecer.add((Dispecer) k );
            }
        }

        for (Dispecer d : dispecer) {
            System.out.println(d);
        }
    }

    public Dispecer obrisiDispicera(String jmbg){
        for (Korisnik k:sviKorisnici){
            if (k.getTipKorisnika().equals(TipKorisnika.DISPECER) && !k.isObrisan()){
                Dispecer dispecer = (Dispecer) k;
                if (dispecer.getJmbg().equalsIgnoreCase(jmbg)){
                    dispecer.setObrisan(true);
                    sacuvajKorisnike(korisniciFajl);
                    return dispecer;
                }
            }
        }
        return null;
    }

    public Dispecer kreirajDispecera(String jmbg,String korisnickoIme,String lozinka,String ime,String prezime,String adresa,Pol pol,String brojTelefona,double plata,String brojTelefonaLinije,Odeljenje odeljenje){
        TipKorisnika tipKorisnika = TipKorisnika.DISPECER;
        Dispecer noviDispecer = new Dispecer(jmbg,korisnickoIme,lozinka,ime,prezime,adresa,pol,brojTelefona,plata,brojTelefonaLinije,odeljenje,tipKorisnika);

        for (Korisnik k:sviKorisnici){
            if (noviDispecer.getJmbg().equalsIgnoreCase(k.getJmbg())){
                System.out.println("Korisnik vec postoji");
                return null;
            }else if (noviDispecer.getKorisnickoIme().equalsIgnoreCase(k.getKorisnickoIme())){
                System.out.println("Korisnik vec postoji");
                return null;
            }

        }
        sviKorisnici.add(noviDispecer);
        sacuvajKorisnike(korisniciFajl);
        return noviDispecer;

    }

    public Dispecer izmeniDispecera(String jmbg,String korisnickoIme,String lozinka,String prezime,String adresa,Pol pol,String brojTelefona,double plata,String brojTelefonaLinije,Odeljenje odeljenje){
        Dispecer dispecerKogMenjamo = null;
        for (Korisnik k : sviKorisnici){
            if(k.getJmbg().equalsIgnoreCase(k.getJmbg())){
                if (k.getTipKorisnika().equals(TipKorisnika.DISPECER)){
                    dispecerKogMenjamo = (Dispecer) k;
                    break;
                }
            }
        }
        dispecerKogMenjamo.setAdresa(adresa);
        dispecerKogMenjamo.setBrojTelefona(brojTelefona);
        dispecerKogMenjamo.setPlata(plata);
        dispecerKogMenjamo.setLozinka(lozinka);
        dispecerKogMenjamo.setBrojTelefona(brojTelefonaLinije);
        sacuvajKorisnike(korisniciFajl);
        return dispecerKogMenjamo;
    }





    public Automobil pronadjiAutomobilPoID(int id) {
        for (Automobil a : sviAutomobili) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    // CRUD za VOZNJE

    public static Voznja kreirajVoznju(LocalDateTime datum, String adresaPolaska, String adresaDestinacije, double brojKM, int trajanjeVoznje, Korisnik musterija) {
        int najveciID = sveVoznje.get(sveVoznje.size() - 1).getId();
        int noviID = najveciID + 1;

        Voznja novaVoznja = new Voznja(noviID, datum, adresaPolaska, adresaDestinacije, StatusVoznje.KREIRANA, brojKM, trajanjeVoznje, musterija, null, TipKreiraneVoznje.PUTEM_TELEFONA, false);
        sveVoznje.add(novaVoznja);

        sacuvajVoznje(voznjeFajl);

        return novaVoznja;
    }


    public static Voznja izmeniVoznju(int id, LocalDateTime datum, String adresaPolaska, String adresaDestinacije, StatusVoznje statusVoznje, double brKM, int trajanjeVoznje, Korisnik musterija, Vozac vozac) {

        Voznja voznjaZaIzmenu = pronadjiVoznjuPoID(id);

        if (voznjaZaIzmenu == null) {
            System.out.println("Ne postoji voznja sa unetim ID...");
            return null;
        }
        voznjaZaIzmenu.setDatumKreirnja(datum);
        voznjaZaIzmenu.setAdresaPolaska(adresaPolaska);
        voznjaZaIzmenu.setAdresaDestinacije(adresaDestinacije);
        voznjaZaIzmenu.setStatus(statusVoznje);
        voznjaZaIzmenu.setBrojKM(brKM);
        voznjaZaIzmenu.setTrajanjeVoznje(trajanjeVoznje);
        voznjaZaIzmenu.setVozac(vozac);
        voznjaZaIzmenu.setMusterija(musterija);
        sacuvajVoznje(voznjeFajl);
        return voznjaZaIzmenu;
    }

    public static List<Voznja> dobaviVoznjeKreiraneTelefonom() {
        List<Voznja> voznjePutemTelefona = new ArrayList<Voznja>();
        for (Voznja v : sveVoznje) {
            if (v.getTipKreiraneVoznje().equals(TipKreiraneVoznje.PUTEM_TELEFONA) && !v.isObrisan()&& v.getStatus().equals(StatusVoznje.ZAVRSENA)) {
                voznjePutemTelefona.add(v);
            }
        }
        return voznjePutemTelefona;
    }

    public static List<Voznja> dobaviVoznjeKreiraneAplikacijom() {
        List<Voznja> voznjePutemApliacije = new ArrayList<Voznja>();
        for (Voznja v : sveVoznje) {
            if (v.getTipKreiraneVoznje().equals(TipKreiraneVoznje.PUTEM_APLIKACIJE) && !v.isObrisan()&& v.getStatus().equals(StatusVoznje.ZAVRSENA)) {
                voznjePutemApliacije.add(v);
            }
        }
        return voznjePutemApliacije;
    }

    public static List<Voznja> dobaviVoznjeKreiraneTelefonAukcija() {
        List<Voznja> voznjePutemApliacije = new ArrayList<Voznja>();
        for (Voznja v : sveVoznje) {
            if (v.getTipKreiraneVoznje().equals(TipKreiraneVoznje.PUTEM_TELEFONA) && !v.isObrisan()&& v.getStatus().equals(StatusVoznje.KREIRANA)) {
                voznjePutemApliacije.add(v);
            }
        }
        return voznjePutemApliacije;
    }



    public List<Voznja> voznjeVozaca(String korisnickoIme){
        System.out.println("korisnicko ime: " + korisnickoIme);
        List<Voznja> nadjeneVoznje = new ArrayList<Voznja>();
        for (Voznja v: sveVoznje) {
            if (v.getVozac() != null && v.getVozac().getKorisnickoIme().equals(korisnickoIme)) {
                System.out.println(v);
                nadjeneVoznje.add(v);
            }
        }
        System.out.println("prosao");

        System.out.println("size: " + nadjeneVoznje.size());
        return nadjeneVoznje;

    }


    public static Voznja pronadjiVoznjuPoID(int id) {

        Voznja pronadjenaVoznja = null;
        for (Voznja v : sveVoznje) {
            if (v.getId() == id) {
                pronadjenaVoznja = v;
            }
        }
        return pronadjenaVoznja;
    }

    public static Voznja obrisiVoznju(int id) {
        Voznja voznjaZaBrisanje = pronadjiVoznjuPoID(id);
        if (voznjaZaBrisanje == null) {
            System.out.println("Ne postoji voznja sa unetim ID...");
            return null;
        } else {
            voznjaZaBrisanje.setObrisan(true);
            sacuvajVoznje(voznjeFajl);
        }

        return voznjaZaBrisanje;
    }

    public static Vozac pronadjiVozacaPoKorisnickomImenu(String korisnickoIme) {
        Vozac pronadjenVozac = null;
        for (Korisnik k : sviKorisnici) {
            if (k.getTipKorisnika().equals(TipKorisnika.VOZAC) && !k.isObrisan() && k.getKorisnickoIme().equals(korisnickoIme)) {
                pronadjenVozac = (Vozac) k;
                break;
            }
        }
        return pronadjenVozac;
    }




    public Korisnik pronadjiMusterijuPoKorisnickomImenu(String korisnickoIme) {
        Korisnik pronadjenaMusterija = null;
        for (Korisnik k : sviKorisnici) {
            if (k.getTipKorisnika().equals(TipKorisnika.MUSTERIJA) && !k.isObrisan() && k.getKorisnickoIme().equals(korisnickoIme)) {
                pronadjenaMusterija = k;
                break;
            }
        }
        return pronadjenaMusterija;
    }

    public Voznja dodeliVoznjuVozacu(String korisnickoImeVozaca, int idVoznje) {
        Voznja pronadjenaVoznja = binarnaPretragaVoznjePoId(sveVoznje,0,sveVoznje.size()-1,idVoznje);
        Korisnik pronadjeniKorisnik = binarnaPretragaKorisnikaPoStringu(sviKorisnici,0,sviKorisnici.size()-1,korisnickoImeVozaca);
        if (pronadjenaVoznja == null) {
            System.out.println("Ne postoji voznja sa unetim ID!");
            return null;
        }
        if (pronadjeniKorisnik == null) {
            System.out.println("Ne postoji vozac sa unetim korisnickim imenom");
            return null;
        }

        Vozac pronadjeniVozac = (Vozac) pronadjeniKorisnik;
        pronadjenaVoznja.setStatus(StatusVoznje.DODELJENA);
        pronadjenaVoznja.setVozac(pronadjeniVozac);
        sacuvajVoznje(voznjeFajl);

        return pronadjenaVoznja;
    }

    public static Voznja odbijVoznju(int id) {
        Voznja pronadjenaVoznja = binarnaPretragaVoznjePoId(sveVoznje,0,sveVoznje.size()-1,id);
        if (pronadjenaVoznja == null){
            System.out.println("Ne postoji voznja sa unetim id");
            return null;
        }
        pronadjenaVoznja.setVozac(null);
        pronadjenaVoznja.setStatus(StatusVoznje.ODBIJENA);
        sacuvajVoznje(voznjeFajl);

        return pronadjenaVoznja;
    }

    public Voznja prihvatiVoznju(int id){
        Voznja prihvacenaVoznja = binarnaPretragaVoznjePoId(sveVoznje,0,sveVoznje.size()-1,id);
        if (prihvacenaVoznja ==null){
            System.out.println("Ne postoji voznja za unetim id");
            return null;
        }
        prihvacenaVoznja.setStatus(StatusVoznje.PRIHVACENA);
        sacuvajVoznje(voznjeFajl);
        return prihvacenaVoznja;
    }


    public Voznja zavrsiVoznju(int id, double brojKM, int trajanjeVoznje) {
        Voznja pronadjenaVoznja = binarnaPretragaVoznjePoId(sveVoznje,0,sveVoznje.size()-1,id);
        if (pronadjenaVoznja == null){
            System.out.println("Ne postoji voznja sa unetim id");
            return null;
        }
        pronadjenaVoznja.setBrojKM(brojKM);
        pronadjenaVoznja.setTrajanjeVoznje(trajanjeVoznje);
        pronadjenaVoznja.setStatus(StatusVoznje.ZAVRSENA);

        sacuvajVoznje(voznjeFajl);

        return pronadjenaVoznja;
    }


    public static ArrayList<Vozac> kombinovanaPretraga(String ime, String prezime,Double plataOd, Double plataDo, String automobilID) {

        ArrayList<Vozac> sviVozaci = (ArrayList<Vozac>) dobaviVozace();

        Iterator<Vozac> it = sviVozaci.iterator();

        while(it.hasNext()) {
            Vozac trenutni = it.next();

            // TRUE && TRUE = TRUE
            // TRUE && FALSE = FALSE
            // FLASAE && TRUE = FALSE
            // FALSE && FALSE = FALSE

            // TRUE || TRUE = TRUE
            // TRUE || FALSE = TRUE
            // FALSE || TRUE = TRUE
            // FALSE || FALSE = FALSE

            if (!ime.equals("") && !trenutni.getIme().equalsIgnoreCase(ime)) {
                it.remove();
                continue;

            }

            if (!prezime.equals("") && !trenutni.getPrezime().equalsIgnoreCase(prezime)) {
                it.remove();

                continue;
            }

            if (!(plataOd == null) && (trenutni.getPlata() < plataOd)) {
                it.remove();

                continue;
            }

            if (!(plataDo == null) && (trenutni.getPlata() > plataDo)) {
                it.remove();

                continue;
            }

            if (!automobilID.equals("") && !trenutni.getTaxi().getBrojTaksiVozila().equalsIgnoreCase(automobilID)) {
                it.remove();
                continue;
            }
        }
        return sviVozaci;

    }

    public static float prosecanBrKmPoVoznji(){
        float ukupanBrPredjenihKm = 0;
        for (Voznja voznja: pronadjeneVoznje){
            ukupanBrPredjenihKm += voznja.getBrojKM();
        }
        return ukupanBrPredjenihKm/pronadjeneVoznje.size();
    }
    public static float prosecnoTrajanjeVoznji(){
        float ukupnoTrajanjeVoznji = 0;
        for (Voznja voznja: pronadjeneVoznje){
            ukupnoTrajanjeVoznji += voznja.getTrajanjeVoznje();
        }return ukupnoTrajanjeVoznji / pronadjeneVoznje.size();
    }
    public static double ukupnaZarada(){
        double ukupnaZarada = 0;
        for (Voznja voznja: pronadjeneVoznje){
            double cenaPoKm = TaxiSluzba.CENA_PO_KM;
            double cenaStarta =TaxiSluzba.CENA_START;
            ukupnaZarada += ((voznja.getBrojKM() * cenaPoKm) + cenaStarta);

        }return ukupnaZarada;
    }
    public static double prosecnaZarada(){
        double ukupnaZarada = 0;
        for (Voznja voznja: pronadjeneVoznje){
            double cenaPoKm = TaxiSluzba.CENA_PO_KM;
            double cenaStarta = TaxiSluzba.CENA_START;
            ukupnaZarada += ((voznja.getBrojKM() * cenaPoKm) + cenaStarta);

        }return ukupnaZarada / pronadjeneVoznje.size();
    }

    public static ArrayList<Voznja> VoznjeTelefonom(){
        ArrayList<Voznja> voznje = new ArrayList<Voznja>(){};
        for (Voznja voznja: pronadjeneVoznje){
            if (voznja.getTipKreiraneVoznje().equals(TipKreiraneVoznje.PUTEM_TELEFONA)){
                voznje.add(voznja);
            }
        }
        return voznje;
    }
    public static ArrayList<Voznja> VoznjeAplikacijom(){
        ArrayList<Voznja> voznje = new ArrayList<Voznja>();
        for (Voznja voznja: pronadjeneVoznje){
            if (voznja.getTipKreiraneVoznje().equals(TipKreiraneVoznje.PUTEM_APLIKACIJE)){
                voznje.add(voznja);
            }
        }return voznje;
    }

//    public static ArrayList<Voznja> VoznjeTelefonom(){
//        ArrayList<Voznja> voznje = new ArrayList<Voznja>();
//        for (Voznja porudzbina: pronadjeneVoznje){
//            if (porudzbina.getTipPorudzbine().equals(TipPorudzbine.TELEFON)){
//                voznje.add(porudzbina);
//            }
//        }return voznje;
//    }

    public static ArrayList<Voznja> dnevniIzvestaj(LocalDateTime dan) {
        ArrayList<Voznja> pronadjeneVoznje = new ArrayList<>();
        for (Voznja voznja : sveVoznje) {
            if (voznja.getStatus().equals(StatusVoznje.ZAVRSENA) &&
                    voznja.getDatumKreirnja().getYear() == dan.getYear() &&
                    voznja.getDatumKreirnja().getMonth().equals(dan.getMonth()) &&
                    voznja.getDatumKreirnja().getDayOfMonth() == dan.getDayOfMonth()) {
                pronadjeneVoznje.add(voznja);
            }
        }
        return pronadjeneVoznje;

    }
    public static ArrayList<Voznja> nedeljniIzvestaj(int nedelja, int godina) {
        ArrayList<Voznja> pronadjeneVoznje = new ArrayList<>();

        for (Voznja voznja : sveVoznje) {
            WeekFields weekFields = WeekFields.of(Locale.getDefault());
            int nedeljaVoznje = voznja.getDatumKreirnja().get(weekFields.weekOfWeekBasedYear());
            if (voznja.getStatus().equals(StatusVoznje.ZAVRSENA) &&
                    voznja.getDatumKreirnja().getYear() == godina && nedeljaVoznje == nedelja) {
                pronadjeneVoznje.add(voznja);
            }
        }
        return pronadjeneVoznje;
    }

    public static ArrayList<Voznja> mesecniIzvestaj(int mesec, int godina) {
        ArrayList<Voznja> pronadjeneVoznje = new ArrayList<Voznja>();
        for (Voznja voznja : sveVoznje) {
            if (voznja.getStatus().equals(StatusVoznje.ZAVRSENA) &&
                    voznja.getDatumKreirnja().getYear() == godina &&
                    voznja.getDatumKreirnja().getMonth().getValue() == mesec) {
                pronadjeneVoznje.add(voznja);
            }
        }
        return pronadjeneVoznje;
    }

    public static ArrayList<Voznja> godisnjiIzvestaj(int godina) {
        ArrayList<Voznja> pronadjeneVoznje = new ArrayList<Voznja>();
        for (Voznja voznja : sveVoznje) {
            if (voznja.getStatus().equals(StatusVoznje.ZAVRSENA) &&
                    voznja.getDatumKreirnja().getYear() == godina) {
                pronadjeneVoznje.add(voznja);
            }
        }
        return pronadjeneVoznje;
    }
    public static ArrayList<Voznja> zavrseneVoznje() {
        ArrayList<Voznja> voznje = new ArrayList<Voznja>();
        for (Voznja porudzbina: sveVoznje) {
            if (porudzbina.getStatus().equals(StatusVoznje.ZAVRSENA)){
                Voznja voznja = porudzbina;
                voznje.add(voznja);
            }
        }return voznje;
    }


    public Automobil binarnaPretragaAutomobilaPoId(List<Automobil> automobili,int levi,int desni,int id){
        if (desni >= levi){
            int sredina = levi + (desni - levi)/2;
            Automobil trenutni = automobili.get(sredina);
            if (trenutni.getId() == id){
                return trenutni;
            }

            if (trenutni.getId() < id){
                return binarnaPretragaAutomobilaPoId(automobili,sredina +1,desni,id) ;
            }
            if (trenutni.getId() > id){
                return binarnaPretragaAutomobilaPoId(automobili,levi,sredina -1,id);
            }
        }
        return null;
    }


    public static Voznja binarnaPretragaVoznjePoId(List<Voznja> voznje, int levi, int desni, int id){
        if (desni >= levi){
            int sredina = levi + (desni - levi)/2;
            Voznja trenutna = voznje.get(sredina);
            if (trenutna.getId() == id){
                return trenutna;
            }

            if (trenutna.getId() < id){
                return binarnaPretragaVoznjePoId(voznje,sredina +1,desni,id) ;
            }
            if (trenutna.getId() > id){
                return binarnaPretragaVoznjePoId(voznje,levi,sredina -1,id);
            }
        }
        return null;
    }




    public Korisnik binarnaPretragaKorisnikaPoStringu(List<Korisnik> korisnici,int levi,int desni,String ime){
        if (desni >= levi){
            int sredina = levi +(desni - levi)/2;
            Korisnik trenutni = korisnici.get(sredina);
            if (trenutni.getKorisnickoIme().equalsIgnoreCase(ime)){
                return trenutni;
            }

            if (trenutni.getKorisnickoIme().compareToIgnoreCase(ime) < 0){
                return binarnaPretragaKorisnikaPoStringu(korisnici, sredina +1,desni,ime) ;
            }
            if (trenutni.getKorisnickoIme().compareToIgnoreCase(ime) > 0){
                return binarnaPretragaKorisnikaPoStringu(korisnici,levi,sredina -1,ime);
            }
        }
        return null;
    }
}
