package taxiSluzba;

import automobil.Automobil;
import automobil.VrstaAutomobila;
import korisnici.*;
import voznja.StatusVoznje;
import voznja.TipKreiraneVoznje;
import voznja.Voznja;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaxiSluzba {

    private List<Korisnik> sviKorisnici = new ArrayList<Korisnik>();
    private List<Automobil> sviAutomobili = new ArrayList<Automobil>();
    private List<Voznja> sveVoznje = new ArrayList<Voznja>();

    private String automobiliFajl;
    private String voznjeFajl;
    private String korisniciFajl;

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

    public List<Korisnik> getSviKorisnici() {
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

    public List<Voznja> getSveVoznje() {
        return sveVoznje;
    }

    public void setSveVoznje(List<Voznja> sveVoznje) {
        this.sveVoznje = sveVoznje;
    }

    public void ucitajKorisnike(String nazivFajla) {
        File file =  new File(nazivFajla);

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
                } else if ( tipKorisnika.equals(TipKorisnika.DISPECER) ){
                    String plataString = splitovano[10];
                    String brojTelefonaLinije = splitovano[11];
                    String odeljenjeString = splitovano[12];
                    Odeljenje odeljenje = Odeljenje.valueOf(odeljenjeString);
                    double plata = Double.parseDouble(plataString);
                    Korisnik korisnik = new Dispecer(jmbg, korisnickoIme, sifra, ime, prezime, adresa, pol, brojTelefona, plata, brojTelefonaLinije, odeljenje, tipKorisnika, obrisan );
                    sviKorisnici.add(korisnik);
                } else {
                    String brojClanskeKarte = splitovano[10];
                    String plataString = splitovano[11];
                    String taxiString = splitovano[12];

                    double plata = Double.parseDouble(plataString);


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
            BufferedReader reader= new BufferedReader(new FileReader(file));
            String line;

            while ((line = reader.readLine()) != null) {

                if(line.equals("")){
                    break;
                }

                String[] splitovano = line.split("\\|");
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                String idString = splitovano[0];
                String datum = splitovano[1];
                Date date = formatter.parse(datum);
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

                Voznja voznja = new Voznja(id, date,adresaPolaska,adresaDestinacije,statusVoznje,brKm,trajanje, musterija ,vozac, tipKreiraneVoznje, obrisan);
                sveVoznje.add(voznja);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Greska prilikom snimanja podataka o voznji");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Pogresan format datuma u fajlu iz kog se ucitava... Datum mora biti u formatu [dd-MM-yyyy HH:mm]");
        }
    }

    public void ucitajAutomobile(String nazivFajla) {
        File file = new File(nazivFajla);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {

                if (line.equals("")){
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

    public void sacuvajKorisnike(String nazivFajla) {
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
            for (Automobil automobil: sviAutomobili){
                zaIspis = zaIspis + automobil.formatirajZaUpisAutomobila() + "\n";
            }

            writer.write(zaIspis);
            writer.close();
        } catch (IOException e) {
            System.out.println("Greska prilikom cuvanja podatak o automobilima");
            e.printStackTrace();
        }

    }

    public void sacuvajVoznje(String nazivFajla) {
        File file = new File(nazivFajla);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            String zaIspis = "";
            for (Voznja voznja :sveVoznje){
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
    // ova metoda bi mozda trebala da vraca listu svhi vezaca, umesto da bude "void" metoda bez povratne vrednosti
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

        // return vozaci;
    }

    public Vozac obrisiVozaca(String brojClanskeKarte) {
        for (Korisnik k : sviKorisnici) {
            if (k.getTipKorisnika().equals(TipKorisnika.VOZAC) && !k.isObrisan()) {
                Vozac vozac = (Vozac) k;
                if (vozac.getBrojClanskeKarte().equalsIgnoreCase(brojClanskeKarte)) {
                    vozac.setObrisan(true);

                    return vozac;
                }
            }
        }

        return null;
    }

    public Vozac kreirajVozaca(String jmbg,String korisnickoIme,String lozinka,String ime, String prezime,String adresa,Pol pol,String brojtelefona,double plata,Automobil taxi,String brojClanskeKarte) {
        TipKorisnika tipKorisnika = TipKorisnika.VOZAC;
        Vozac noviVozac = new Vozac(jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojtelefona, brojClanskeKarte, plata, taxi, tipKorisnika);

        for (Korisnik k : sviKorisnici) {
            if (noviVozac.getJmbg().equals(k.getJmbg())) {
                System.out.println("Korisnik vec postoji");
                return null;
            } else if (noviVozac.getKorisnickoIme().equalsIgnoreCase(k.getKorisnickoIme())) {
                System.out.println("Vozac vec postoji");
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
        //sacuvajKorisnike();
        return noviVozac;
    }


    public Vozac izmeniVozaca(String jmbg,String korisnickoIme,String lozinka,String ime, String prezime,String adresa,Pol pol,String brojtelefona,double plata,Automobil taxi,String brojClanskeKarte) {
        Vozac vozacKogMenjamo = null;
        for (Korisnik k : sviKorisnici) {
            if (k.getJmbg().equalsIgnoreCase(k.getJmbg())) {
                if (k.getTipKorisnika().equals(TipKorisnika.VOZAC)) {
                    vozacKogMenjamo = (Vozac) k;
                    break;
                }
            }
        }
        vozacKogMenjamo.setLozinka(lozinka);
        vozacKogMenjamo.setAdresa(adresa);
        vozacKogMenjamo.setBrojTelefona(brojtelefona);
        vozacKogMenjamo.setPlata(plata);
        vozacKogMenjamo.setBrojClanskeKarte(brojClanskeKarte);
        vozacKogMenjamo.setTaxi(taxi);

        return vozacKogMenjamo;
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
        return dispecerKogMenjamo;
    }

    // implementirati binarnu pretragu kasnije
    public Automobil pronadjiAutomobilPoID(int id) {
        for (Automobil a : sviAutomobili) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }




    // CRUD za VOZNJE

    public Voznja kreirajVoznju(Date datum, String adresaPolaska, String adresaDestinacije, double brojKM, int trajanjeVoznje, Korisnik musterija, Vozac vozac) {
        int najveciID = sveVoznje.get(sveVoznje.size() - 1).getId();
        int noviID = najveciID + 1;

        Voznja novaVoznja = new Voznja(noviID, datum, adresaPolaska, adresaDestinacije, StatusVoznje.KREIRANA, brojKM, trajanjeVoznje, musterija, vozac, TipKreiraneVoznje.PUTEM_TELEFONA, false);
        sveVoznje.add(novaVoznja);

        sacuvajKorisnike(voznjeFajl);

        return novaVoznja;
    }

    // slicno kao  i sve ostale izmene
    public Voznja izmeniVoznju(int id,Date datum,String adresaPolaska,String adresaDestinacije,StatusVoznje statusVoznje,double brKM,int trajanjeVoznje,Korisnik musterija,Vozac vozac,TipKreiraneVoznje tipKreiraneVoznje) {
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
        return voznjaZaIzmenu;
    }

    public List<Voznja> dobaviVoznje() {
        List<Voznja> voznjePutemTelefona = new ArrayList<Voznja>();
        for (Voznja v : sveVoznje) {
            if (v.getTipKreiraneVoznje().equals(TipKreiraneVoznje.PUTEM_TELEFONA) && !v.isObrisan()) {
                voznjePutemTelefona.add(v);
            }
        }
        return voznjePutemTelefona;
    }

    // odraditi binarnu pretragu
    public Voznja pronadjiVoznjuPoID(int id) {
        Voznja pronadjenaVoznja = null;
        for (Voznja v : sveVoznje) {
            if (v.getId() == id) {
                pronadjenaVoznja = v;
            }
        }
        return pronadjenaVoznja;
    }

    public Voznja obrisiVoznju(int id) {
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

    public Vozac pronadjiVozacaPoKorisnickomImenu(String korisnickoIme) {
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
}
