package korisnici;

public class Korisnik {
    private String jmbg;
    private String korisnickoIme;
    private String lozinka;
    private String ime;
    private String prezime;
    private String adresa;
    private Pol pol;
    private String brojTelefona;
    private TipKorisnika tipKorisnika;



    public Korisnik(){

    }

    public Korisnik(String jmbg, String korisnickoIme, String lozinka, String ime, String prezime, String adresa, Pol pol, String brojTelefona, TipKorisnika tipKorisnika) {
        super();
        this.jmbg = jmbg;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
        this.ime = ime;
        this.prezime = prezime;
        this.adresa = adresa;
        this.pol = pol;
        this.brojTelefona = brojTelefona;
        this.tipKorisnika = tipKorisnika;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public Pol getPol() {
        return pol;
    }

    public void setPol(Pol pol) {
        this.pol = pol;
    }

    public String getBrojTelefona() {
        return brojTelefona;
    }

    public void setBrojTelefona(String brojTelefona) {
        this.brojTelefona = brojTelefona;
    }

    public TipKorisnika getTipKorisnika() {
        return tipKorisnika;
    }

    public void setTipKorisnika(TipKorisnika tipKorisnika) {
        this.tipKorisnika = tipKorisnika;
    }

    public String formatrajZaUpis() {
        String zaUpis = String.format("%s|%s|%s|%s|%s|%s|%s|%s|%s", this.getIme(), this.getPrezime(), this.getKorisnickoIme(), this.getLozinka(),
                this.getAdresa(), this.getJmbg(), this.getPol(), this.getBrojTelefona(), this.getTipKorisnika());
        return zaUpis;
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "jmbg='" + jmbg + '\'' +
                ", korisnickoIme='" + korisnickoIme + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", adresa='" + adresa + '\'' +
                ", pol=" + pol +
                ", brojTelefona='" + brojTelefona + '\'' +
                ", tipKorisnika=" + tipKorisnika +
                '}';
    }
}



