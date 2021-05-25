package korisnici;


public class Dispecer extends Korisnik {
    private double plata;
    private String brojTelefonaLinije;
    private Odeljenje odeljenje;

    public Dispecer(double plata, String brojTelefonaLinije, Odeljenje odeljenje) {
        this.plata = plata;
        this.brojTelefonaLinije = brojTelefonaLinije;
        this.odeljenje = odeljenje;
    }

    public Dispecer(String jmbg, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, double plata, String brojTelefonaLinije, Odeljenje odeljenje,TipKorisnika tipKorisnika, boolean obrisan) {
        super(jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona,tipKorisnika, obrisan);
        this.plata = plata;
        this.brojTelefonaLinije = brojTelefonaLinije;
        this.odeljenje = odeljenje;
    }

    public Dispecer(String jmbg, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, double plata, String brojTelefonaLinije, Odeljenje odeljenje,TipKorisnika tipKorisnika) {
        super(jmbg, korisnickoIme, lozinka, ime, prezime, adresa, pol, brojTelefona,tipKorisnika);
        this.plata = plata;
        this.brojTelefonaLinije = brojTelefonaLinije;
        this.odeljenje = odeljenje;
    }



    public double getPlata() {
        return plata;
    }

    public void setPlata(double plata) {
        this.plata = plata;
    }

    @Override
    public String formatrajZaUpis() {
        String zaIspis = super.formatrajZaUpis();
        zaIspis = zaIspis + "|" + String.valueOf(this.plata) + "|" + this.brojTelefonaLinije + "|" + this.odeljenje;
        return zaIspis;
    }


}
