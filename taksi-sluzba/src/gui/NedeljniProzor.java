package gui;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;
import voznja.Voznja;

import javax.swing.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class NedeljniProzor extends JFrame {
    int broj = 15;

    private JLabel lblNedelja = new JLabel("Unesite nedelju :");
    private JTextField nedelja = new JTextField(broj);
    private JLabel lblGodina = new JLabel("Unesite godinu :");
    private JTextField godina = new JTextField(broj);



    private JButton btnPretraga = new JButton("pretrazi");

    public NedeljniProzor(){
        setTitle("Sumirane statistike za nedeljne voznje");
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
        add(lblNedelja);
        add(nedelja);
        add(lblGodina);
        add(godina);
        add(btnPretraga, "span 2");
    }

    private void initActions(){
        btnPretraga.addActionListener(e -> {
            try{
                int broj1 = Integer.parseInt(nedelja.getText());
                int broj2 = Integer.parseInt(godina.getText());
                ArrayList<Voznja> pronadjiVoznju = TaxiSluzba.nedeljniIzvestaj(broj1,broj2);
                PronadjeneVoznje pronadjeneVoznje = new PronadjeneVoznje(pronadjiVoznju);
                pronadjeneVoznje.setVisible(true);
            }
            catch(DateTimeParseException ex) {
                System.out.println(ex.getMessage());
            };
        });
    }
}
