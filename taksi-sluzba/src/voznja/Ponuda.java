package voznja;

import korisnici.Vozac;

public class Ponuda {

    private int id;
    private  int vreme;
    private Vozac vozac;
    private Voznja voznja;

    public Ponuda(){}
    public Ponuda(int id,int vreme,Vozac vozac,Voznja voznja){
        this.id = id;
        this.vreme = vreme;
        this.vozac = vozac;
        this.voznja = voznja;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVreme() {
        return vreme;
    }

    public void setVreme(int vreme) {
        this.vreme = vreme;
    }

    public Vozac getVozac() {
        return vozac;
    }

    public void setVozac(Vozac vozac) {
        this.vozac = vozac;
    }

    public Voznja getVoznja() {
        return voznja;
    }

    public void setVoznja(Voznja voznja) {
        this.voznja = voznja;
    }

    @Override
    public String toString() {
        return "Ponuda{" +
                "id=" + id +
                ", vreme=" + vreme +
                ", vozac=" + vozac +
                ", voznja=" + voznja +
                '}';
    }
}
