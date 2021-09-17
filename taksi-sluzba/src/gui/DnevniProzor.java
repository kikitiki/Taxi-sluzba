package gui;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;
import voznja.Voznja;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;

public class DnevniProzor extends JFrame {
    int broj = 15;

        private JLabel lblGodina = new JLabel("Unesite datum (2021-04-29T19:57) ");
        private JTextField godina = new JTextField(broj);



    private JButton btnPretraga = new JButton("pretrazi");


    public DnevniProzor() {
        setTitle("Sumirane statistike za dnevne voznje");
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
        add(lblGodina);
        add(godina);
        add(btnPretraga, "span 2");
    }


    private void initActions(){
        btnPretraga.addActionListener(e -> {
            try{
                ArrayList<Voznja> pronadjiVoznju = TaxiSluzba.dnevniIzvestaj(LocalDateTime.parse(godina.getText()));
                PronadjeneVoznje pronadjeneVoznje = new PronadjeneVoznje(pronadjiVoznju);
                pronadjeneVoznje.setVisible(true);
            }
            catch(DateTimeParseException ex) {
                System.out.println(ex.getMessage());
            };
        });
    }


}
