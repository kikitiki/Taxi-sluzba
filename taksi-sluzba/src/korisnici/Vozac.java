package korisnici;


import automobil.Automobil;

public class Vozac extends Korisnik {
    private String brojClanskeKarte;
    private double plata;
    private Automobil taxi;



    public Vozac(){
        super();
        this.brojClanskeKarte ="";
        this.plata=0;
    }

    public Vozac(String jmbg, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, String brojClanskeKarte, double plata, Automobil taxi,TipKorisnika tipKorisnika) {
        super(jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona,tipKorisnika);
        this.brojClanskeKarte = brojClanskeKarte;
        this.plata = plata;
        this.taxi = taxi;
    }

    public String getBrojClanskeKarte() {
        return brojClanskeKarte;
    }

    public void setBrojClanskeKarte(String brojClanskeKarte) {
        this.brojClanskeKarte = brojClanskeKarte;
    }

    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    public Automobil getTaxi() {
        return taxi;
    }

    public void setTaxi(Automobil taxi) {
        this.taxi = taxi;
    }

    @Override
    public String toString() {

        return "Vozaci{" +
                "brojClanskeKarte='" + brojClanskeKarte + '\'' +
                ", plata=" + plata +
                ", taxi=" + taxi +
                '}';
    }

    @Override
    public String formatrajZaUpis() {
        String zaUpis = super.formatrajZaUpis();
        zaUpis = zaUpis + "|" + this.brojClanskeKarte + "|" + String.valueOf(this.plata) + "|" + this.taxi.getBrojTaksiVozila();
        return zaUpis;

    }
}
