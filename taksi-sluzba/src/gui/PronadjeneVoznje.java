package gui;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;
import voznja.Voznja;

import javax.swing.*;
import java.util.ArrayList;

import static taxiSluzba.TaxiSluzba.VoznjeTelefonom;
import static taxiSluzba.TaxiSluzba.prosecanBrKmPoVoznji;

public class PronadjeneVoznje extends JFrame {
    public static ArrayList<Voznja> pronadjeneVoznje = new ArrayList<>();
    int broj = 10;
    private String ukupanBrVoznji;
    private String prosekBrKmPoVoznji;
    private String voznjeTel;
    private String voznjeApl;
    private String prosecnoTrajanje;
    private String ukupnaZarada;
    private String prosecnaZarada;

    private JLabel lblSveVoznje = new JLabel("Ukupan broj voznji: ");
    private JTextField txtUkupanBrojVoznji = new JTextField(ukupanBrVoznji, broj);
    private JLabel lblVoznjeTel = new JLabel("Voznje porucne telefonom");
    private JTextField txtVoznjeTel = new JTextField(broj);
    private JLabel lblVoznjeApl = new JLabel("Voznje porucne aplikacijom");
    private JTextField txtVoznjeApl = new JTextField(broj);
    private JLabel lblProsecanBrKmPoVoznji = new JLabel("Prosecan broj km po voznji: ");
    private JTextField txtProsecanBrKmPoVoznji = new JTextField(broj);
    private JLabel lblProsecnoTrajanje = new JLabel("Prosecno trajanje voznji: ");
    private JTextField txtProsecnoTrajanje = new JTextField(prosecnoTrajanje, broj);
    private JLabel lblUkupnaZarada = new JLabel("Ukupna zarada: ");
    private JTextField txtUkupnaZarada = new JTextField(ukupnaZarada, broj);
    private JLabel lblProsecnaZarada = new JLabel("Prosecna zarada: ");
    private JTextField txtProsecnaZarada = new JTextField(prosecnaZarada, broj);




    public PronadjeneVoznje(ArrayList<Voznja> pronadjiVoznju) {
        this.pronadjeneVoznje = pronadjiVoznju;
        ukupanBrVoznji = String.valueOf(pronadjeneVoznje.size());
        prosekBrKmPoVoznji = String.valueOf(TaxiSluzba.prosecanBrKmPoVoznji());
        prosecnoTrajanje = String.valueOf(TaxiSluzba.prosecnoTrajanjeVoznji());
        voznjeTel = String.valueOf(VoznjeTelefonom().size());
        voznjeApl = String.valueOf(TaxiSluzba.VoznjeAplikacijom().size());
        ukupnaZarada = String.valueOf(TaxiSluzba.ukupnaZarada());
        prosecnaZarada = String.valueOf(TaxiSluzba.prosecnaZarada());
        setTitle("Izvestaj");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,500);
        setResizable(true);
        initGUI();
        initText();
        pack();
    }
    public void initGUI(){
        MigLayout mig = new MigLayout("wrap 2", "[][]","[]10[][]10[]");
        setLayout(mig);
        add(lblSveVoznje);
        add(txtUkupanBrojVoznji);
        txtUkupanBrojVoznji.setEditable(false);
        add(lblProsecanBrKmPoVoznji);
        add(txtProsecanBrKmPoVoznji);
        txtProsecanBrKmPoVoznji.setEditable(false);
        add(lblVoznjeTel);
        add(txtVoznjeTel);
        txtVoznjeTel.setEditable(false);
        add(lblVoznjeApl);
        add(txtVoznjeApl);
        txtVoznjeApl.setEditable(false);
        add(lblProsecnoTrajanje);
        add(txtProsecnoTrajanje);
        txtProsecnoTrajanje.setEditable(false);
        add(lblUkupnaZarada);
        add(txtUkupnaZarada);
        txtUkupnaZarada.setEditable(false);
        add(lblProsecnaZarada);
        add(txtProsecnaZarada);
        txtProsecnaZarada.setEditable(false);
    }

    public void initText(){
        txtUkupanBrojVoznji.setText(ukupanBrVoznji);
        txtProsecanBrKmPoVoznji.setText(prosekBrKmPoVoznji);
        txtProsecnoTrajanje.setText(prosecnoTrajanje);
        txtUkupnaZarada.setText(ukupnaZarada);
        txtProsecnaZarada.setText(prosecnaZarada);
        txtVoznjeTel.setText(voznjeTel);
        txtVoznjeApl.setText(voznjeApl);


    }
}







