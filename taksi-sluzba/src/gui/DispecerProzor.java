package gui;

import taxiSluzba.TaxiSluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DispecerProzor extends JFrame {
    private JMenuBar mainMenu = new JMenuBar();
    private JButton vozaci = new JButton("Vozaci");
    private JButton voznje = new JButton("Voznje");

    private TaxiSluzba taxiSluzba;
   // TaxiSluzba taxiSluzba
    public DispecerProzor(){
         //this.taxiSluzba = taxiSluzba;
         setTitle("Meni dispecera");
         setSize(500,500);
         setResizable(false);
         setDefaultCloseOperation(DISPOSE_ON_CLOSE );
         setLocationRelativeTo(null);
         initMenu();
         initActions();
    }



    private void initMenu(){
        setJMenuBar(mainMenu);
        mainMenu.add(vozaci);
        mainMenu.add(voznje);
    }

    private void initActions(){
        vozaci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VozaciTabela pp = new VozaciTabela();
                pp.setVisible(true);
            }
        });

        voznje.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VoznjaTabela vp = new VoznjaTabela();
                vp.setVisible(true);
            }
        });

    }


}
