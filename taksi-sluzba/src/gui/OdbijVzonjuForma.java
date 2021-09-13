package gui;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;
import voznja.StatusVoznje;
import voznja.Voznja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static taxiSluzba.TaxiSluzba.izmeniVoznju;

public class OdbijVzonjuForma extends JFrame{

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private TaxiSluzba taxiSluzba;
    private Voznja voznja;

    public OdbijVzonjuForma(Voznja voznja,TaxiSluzba taxiSluzba){
        this.voznja = voznja;
        this.taxiSluzba = taxiSluzba;

        setTitle("Odbijanje voznje");
        setSize(300,300);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
        pack();
    }

    public void initMenu(){
        MigLayout mig = new MigLayout("wrap 2","[][]","[]10[][]10[]");
        setLayout(mig);

        add(btnOk);
        add(btnCancel);
    }
    public void initActions(){

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taxiSluzba.odbijVoznju(voznja.getId());
                OdbijVzonjuForma.this.dispose();
                OdbijVzonjuForma.this.setVisible(false);
            }


        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OdbijVzonjuForma.this.dispose();
                OdbijVzonjuForma.this.setVisible(false);
            }
        });

    }

}
