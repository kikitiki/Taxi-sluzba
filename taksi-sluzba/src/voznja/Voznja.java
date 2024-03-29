package voznja;

import korisnici.Korisnik;
import korisnici.Vozac;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Voznja implements Comparable<Voznja>{
    private int id;
    private LocalDateTime datumKreirnja;
    private String adresaPolaska;
    private String adresaDestinacije;
    private  StatusVoznje status;
    private double brojKM;
    private int trajanjeVoznje;
    private  Korisnik musterija;
    private Vozac vozac;
    private TipKreiraneVoznje tipKreiraneVoznje;
    private boolean obrisan = false;

    public Voznja() {
    }

    public Voznja(int id, LocalDateTime datumKreirnja, String adresaPolaska, String adresaDestinacije, StatusVoznje status, double brojKM, int trajanjeVoznje, Korisnik musterija, Vozac vozac, TipKreiraneVoznje tipKreiraneVoznje,  boolean obrisan) {
        this.datumKreirnja = datumKreirnja;
        this.adresaPolaska = adresaPolaska;
        this.adresaDestinacije = adresaDestinacije;
        this.status = status;
        this.brojKM = brojKM;
        this.trajanjeVoznje = trajanjeVoznje;
        this.musterija = musterija;
        this.vozac = vozac;
        this.id = id;
        this.tipKreiraneVoznje = tipKreiraneVoznje;
        this.obrisan = obrisan;
    }

    public LocalDateTime getDatumKreirnja() {
        return datumKreirnja;
    }

    public void setDatumKreirnja(LocalDateTime datumKreirnja) {
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

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public boolean isObrisan() {
        return obrisan;
    }

    public void setObrisan(boolean obrisan) {
        this.obrisan = obrisan;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public TipKreiraneVoznje getTipKreiraneVoznje() {
        return tipKreiraneVoznje;
    }

    public void setTipKreiraneVoznje(TipKreiraneVoznje tipKreiraneVoznje) {
        this.tipKreiraneVoznje = tipKreiraneVoznje;
    }



    public  String formatirajZaUpis() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        String datumFormatiran = datumKreirnja.format(formatter);
        String musterija = "";
        String vozac = "";

        if (this.getVozac() == null) {
            vozac = "null";
        } else {
            vozac = this.getVozac().getKorisnickoIme();
        }

        if (this.getMusterija() == null) {
            musterija = "null";
        } else {
            musterija = this.getMusterija().getKorisnickoIme();
        }

        String zaUpis = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s|%s|%s",this.id, datumFormatiran,this.adresaPolaska,this.adresaDestinacije,
                this.status,this.brojKM,this.trajanjeVoznje, musterija, vozac, this.getTipKreiraneVoznje(), this.isObrisan());

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
                ", tipKreiraneVoznje=" + tipKreiraneVoznje +
                '}';
    }

    @Override
    public int compareTo(Voznja drugaVoznja) {
        if (this.getId() < drugaVoznja.getId()){
            return -1;
        }else if (this.getId() > drugaVoznja.getId()){
            return 1;
        }else {
            return 0;
        }

    }
}
