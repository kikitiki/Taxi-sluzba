package automobil;


public class Automobil implements Comparable<Automobil> {
    private int id;
    private String brojTaksiVozila;
    private String model;
    private String proizvodjac;
    private String registracija;
    private VrstaAutomobila tipAutomobila;
    private int godinaProizvodnje;
    private boolean obrisan = false;

    public Automobil() {
    }

    public Automobil(int id, String brojTaksiVozila, String model, String proizvodjac, String registracija, VrstaAutomobila tipAutomobila, int godinaProizvodnje, boolean obrisan) {
        this.brojTaksiVozila = brojTaksiVozila;
        this.model = model;
        this.proizvodjac = proizvodjac;
        this.registracija = registracija;
        this.tipAutomobila = tipAutomobila;
        this.godinaProizvodnje = godinaProizvodnje;
        this.obrisan = obrisan;
        this.id = id;
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

    public String formatirajZaUpisAutomobila(){
        String zaUpis = String.format("%s|%s|%s|%s|%s|%s|%s|%s",this.getId(), this.getBrojTaksiVozila(),this.getModel(),this.getProizvodjac(),this.getRegistracija(),
                this.getTipAutomobila(),this.getGodinaProizvodnje(), this.isObrisan());
        return zaUpis;
    }

    @Override
    public String toString() {
        return "Automobil{" +
                "brojTaksiVozila='" + brojTaksiVozila + '\'' +
                ", model='" + model + '\'' +
                ", proizvodjac='" + proizvodjac + '\'' +
                ", registracija='" + registracija + '\'' +
                ", tipAutomobila=" + tipAutomobila +
                '}';
    }

    @Override
    public int compareTo(Automobil o) {
        if (this.getId() < o.getId()) {
            return -1;
        } else if (this.getId() > o.getId()) {
            return 1;
        }
        else {
            return 0;
        }
       // return this.getId() - o.getId();
    }
}
