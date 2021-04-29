package voznja;

import korisnici.Korisnik;
import korisnici.Vozac;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Voznja {
    private Date datumKreirnja;
    private String adresaPolaska;
    private String adresaDestinacije;
    private  StatusVoznje status;
    private double brojKM;
    private int trajanjeVoznje;
    private  Korisnik musterija;
    private Vozac vozac;

    public Voznja() {
    }

    public Voznja(Date datumKreirnja, String adresaPolaska, String adresaDestinacije, StatusVoznje status, double brojKM, int trajanjeVoznje, Korisnik musterija, Vozac vozac) {
        this.datumKreirnja = datumKreirnja;
        this.adresaPolaska = adresaPolaska;
        this.adresaDestinacije = adresaDestinacije;
        this.status = status;
        this.brojKM = brojKM;
        this.trajanjeVoznje = trajanjeVoznje;
        this.musterija = musterija;
        this.vozac = vozac;
    }

    public Date getDatumKreirnja() {
        return datumKreirnja;
    }

    public void setDatumKreirnja(Date datumKreirnja) {
        this.datumKreirnja = datumKreirnja;
    }

    public String getAdresaPolaska() {
        return adresaPolaska;
    }

    public void setAdresaPolaska(String adresaPolaska) {
        this.adresaPolaska = adresaPolaska;
    }

    public String getAdresaDestinacije() {
        return adresaDestinacije;
    }

    public void setAdresaDestinacije(String adresaDestinacije) {
        this.adresaDestinacije = adresaDestinacije;
    }

    public StatusVoznje getStatus() {
        return status;
    }

    public void setStatus(StatusVoznje status) {
        this.status = status;
    }

    public double getBrojKM() {
        return brojKM;
    }

    public void setBrojKM(double brojKM) {
        this.brojKM = brojKM;
    }

    public int getTrajanjeVoznje() {
        return trajanjeVoznje;
    }

    public void setTrajanjeVoznje(int trajanjeVoznje) {
        this.trajanjeVoznje = trajanjeVoznje;
    }

    public Korisnik getMusterija() {
        return musterija;
    }

    public void setMusterija(Korisnik musterija) {
        this.musterija = musterija;
    }

    public Vozac getVozaci() {
        return vozac;
    }

    public void setVozaci(Vozac vozaci) {
        this.vozac = vozaci;
    }

    public String formatirajZaUpis() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        String datumFormatiran = formatter.format(datumKreirnja);
        String zaUpis = String.format("%s|%s|%s|%s|%s|%s|%s|%s",datumFormatiran,this.adresaPolaska,this.adresaDestinacije,
                this.status,this.brojKM,this.trajanjeVoznje,this.musterija.getKorisnickoIme(),this.vozac.getKorisnickoIme());

        return zaUpis;
    }

    @Override
    public String toString() {
        return "Voznja{" +
                "datumKreirnja=" + datumKreirnja +
                ", adresaPolaska='" + adresaPolaska + '\'' +
                ", adresaDestinacije='" + adresaDestinacije + '\'' +
                ", status=" + status +
                ", brojKM=" + brojKM +
                ", trajanjeVoznje='" + trajanjeVoznje + '\'' +
                ", musterija=" + musterija +
                ", vozac=" + vozac +
                '}';
    }
}
