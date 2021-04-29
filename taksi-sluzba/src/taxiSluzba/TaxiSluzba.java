package taxiSluzba;

import automobil.Automobil;
import automobil.VrstaAutomobila;
import korisnici.*;
import voznja.StatusVoznje;
import voznja.Voznja;

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

    public TaxiSluzba() {

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
                Pol pol = Pol.valueOf(polString);
                TipKorisnika tipKorisnika = TipKorisnika.valueOf(tipKorisnikaString);


                if (tipKorisnika.equals(TipKorisnika.MUSTERIJA)) {
                    Korisnik korisnik = new Korisnik(jmbg, korisnickoIme, sifra, ime, prezime, adresa, pol, brojTelefona, tipKorisnika);
                    sviKorisnici.add(korisnik);
                } else if ( tipKorisnika.equals(TipKorisnika.DISPECER) ){
                    String plataString = splitovano[9];
                    String brojTelefonaLinije = splitovano[10];
                    String odeljenjeString = splitovano[11];
                    Odeljenje odeljenje = Odeljenje.valueOf(odeljenjeString);
                    double plata = Double.parseDouble(plataString);
                    Korisnik korisnik = new Dispecer(jmbg, korisnickoIme, sifra, ime, prezime, adresa, pol, brojTelefona, plata, brojTelefonaLinije, odeljenje, tipKorisnika );
                    sviKorisnici.add(korisnik);
                } else {
                    String brojClanskeKarte = splitovano[9];
                    String plataString = splitovano[10];
                    String taxiString = splitovano[11];

                    double plata = Double.parseDouble(plataString);


                    Automobil taxi = null;
                    for (Automobil automobil : sviAutomobili) {
                        if (automobil.getBrojTaksiVozila().equals(taxiString)) {
                            taxi = automobil;
                        }
                    }

                    Korisnik korisnik = new Vozac(jmbg, korisnickoIme, sifra, ime, prezime, adresa, pol, brojTelefona, brojClanskeKarte, plata, taxi, tipKorisnika);
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


                String datum = splitovano[0];
                Date date = formatter.parse(datum);
                String adresaPolaska = splitovano[1];
                String adresaDestinacije = splitovano[2];
                String statusVoznjeString = splitovano[3];
                String brKmString = splitovano[4];
                String trajanjeVoznjeString = splitovano[5];
                // ucitavamo ID-eve musterije i vozaca
                String musterijaKorisnickoIme = splitovano[6];
                String vozacKorisnickoIme = splitovano[7];
                StatusVoznje statusVoznje = StatusVoznje.valueOf(statusVoznjeString);

                double brKm = Double.parseDouble(brKmString);
                int trajanje = Integer.parseInt(trajanjeVoznjeString);

                Korisnik musterija = null;
                Vozac vozac = null;

                for (Korisnik korisnik : sviKorisnici) {
                    if (korisnik.getKorisnickoIme().equalsIgnoreCase(musterijaKorisnickoIme)) {
                        musterija = korisnik;
                    } else if (korisnik.getKorisnickoIme().equalsIgnoreCase(vozacKorisnickoIme)) {
                        vozac = (Vozac) korisnik;
                    }
                }

                Voznja voznja = new Voznja(date,adresaPolaska,adresaDestinacije,statusVoznje,brKm,trajanje, musterija ,vozac);
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
                String brojTaxiVozila = splitovano[0];
                String model = splitovano[1];
                String proizvodjac = splitovano[2];
                String registracija = splitovano[3];
                String tipVozilaString = splitovano[4];
                VrstaAutomobila tipVozila = VrstaAutomobila.valueOf(tipVozilaString);
                String godinaProizvodnjeString = splitovano[5];
                int godinaProizvodnje = Integer.parseInt(godinaProizvodnjeString);

                Automobil automobil = new Automobil(brojTaxiVozila, model, proizvodjac, registracija, tipVozila, godinaProizvodnje);
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


}
