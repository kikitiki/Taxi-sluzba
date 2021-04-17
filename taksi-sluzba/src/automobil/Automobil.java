package automobil;


public class Automobil {
    private String brojTaksiVozila;
    private String model;
    private String proizvodjac;
    private String registracija;
    private VrstaAutomobila tipAutomobila;
    private int godinaProizvodnje;

    public Automobil() {
    }

    public Automobil(String brojTaksiVozila, String model, String proizvodjac, String registracija, VrstaAutomobila tipAutomobila, int godinaProizvodnje) {
        this.brojTaksiVozila = brojTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.registracija = registracija;
        this.tipAutomobila = tipAutomobila;
        this.godinaProizvodnje = godinaProizvodnje;
    }

    public String getBrojTaksiVozila() {
        return brojTaksiVozila;
    }

    public void setBrojTaksiVozila(String brojTaksiVozila) {
        this.brojTaksiVozila = brojTaksiVozila;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProizvodjac() {
        return proizvodjac;
    }

    public void setProizvodjac(String proizvodjac) {
        this.proizvodjac = proizvodjac;
    }

    public String getRegistracija() {
        return registracija;
    }

    public void setRegistracija(String registracija) {
        this.registracija = registracija;
    }

    public VrstaAutomobila getTipAutomobila() {
        return tipAutomobila;
    }

    public void setTipAutomobila(VrstaAutomobila tipAutomobila) {
        this.tipAutomobila = tipAutomobila;
    }

    public int getGodinaProizvodnje() {
        return godinaProizvodnje;
    }

    public void setGodinaProizvodnje(int godinaProizvodnje) {
        this.godinaProizvodnje = godinaProizvodnje;
    }

    @Override
    public String toString() {
        return "Automobili{" +
                "brojTaksiVozila='" + brojTaksiVozila + '\'' +
                ", model='" + model + '\'' +
                ", proizvodjac='" + proizvodjac + '\'' +
                ", registracija='" + registracija + '\'' +
                ", tipAutomobila=" + tipAutomobila +
                '}';
    }
}
