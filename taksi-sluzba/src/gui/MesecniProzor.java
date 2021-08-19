package gui;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;
import voznja.Voznja;

import javax.swing.*;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class MesecniProzor extends JFrame{
    int broj = 15;

    private JLabel lblMesec = new JLabel("Unesite mesec :");
    private JTextField txtMesec = new JTextField(broj);
    private JLabel lblGodina = new JLabel("Unesite godinu :");
    private JTextField txtGodina = new JTextField(broj);



    private JButton btnPretraga = new JButton("pretrazi");

    public MesecniProzor(){
        setTitle("Sumirane statistike za mesecne voznje");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,500);
        setResizable(false);
        initGui();
        initActions();
    }

    private void initGui() {
        MigLayout mig = new MigLayout("wrap 2", "[][]","[]10[][]10[]");
        setLayout(mig);
        add(lblMesec);
        add(txtMesec);
        add(lblGodina);
        add(txtGodina);
        add(btnPretraga, "span 2");
    }

    private void initActions(){
        btnPretraga.addActionListener(e -> {
            try{
                int broj1 = Integer.parseInt(txtMesec.getText());
                int broj2 = Integer.parseInt(txtGodina.getText());
                ArrayList<Voznja> pronadjiVoznju = TaxiSluzba.mesecniIzvestaj(broj1,broj2);
                PronadjeneVoznje pronadjeneVoznje = new PronadjeneVoznje(pronadjiVoznju);
                pronadjeneVoznje.setVisible(true);
            }
            catch(DateTimeParseException ex) {
                System.out.println(ex.getMessage());
            };
        });
    }

}
