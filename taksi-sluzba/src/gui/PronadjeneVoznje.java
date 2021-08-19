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

    private JLabel lblSveVoznje = new JLabel("Ukupan broj voznji: ");
    private JTextField txtUkupanBrojVoznji = new JTextField(ukupanBrVoznji, broj);
    private JLabel lblVoznjeTel = new JLabel("Voznje porucne telefonom");
    private JTextField txtVoznjeTel = new JTextField(broj);
    private JLabel lblProsecanBrKmPoVoznji = new JLabel("Prosecan broj km po voznji: ");
    private JTextField txtProsecanBrKmPoVoznji = new JTextField(broj);




    public PronadjeneVoznje(ArrayList<Voznja> pronadjiVoznju) {
        this.pronadjeneVoznje = pronadjiVoznju;
        ukupanBrVoznji = String.valueOf(pronadjeneVoznje.size());
        prosekBrKmPoVoznji = String.valueOf(prosecanBrKmPoVoznji());
        voznjeTel = String.valueOf(VoznjeTelefonom().size());
        setTitle("Dnevni izvestaj");
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
        add(lblProsecanBrKmPoVoznji);
        add(txtProsecanBrKmPoVoznji);
        txtUkupanBrojVoznji.setEditable(false);
        add(lblVoznjeTel);
        add(txtVoznjeTel);
        txtVoznjeTel.setEditable(false);
    }

    public void initText(){
        txtUkupanBrojVoznji.setText(ukupanBrVoznji);
        txtProsecanBrKmPoVoznji.setText(prosekBrKmPoVoznji);
        txtVoznjeTel.setText(voznjeTel);

    }
}







