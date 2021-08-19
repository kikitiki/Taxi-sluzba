package gui;

import taxiSluzba.TaxiSluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DispecerProzor extends JFrame {
    private JMenuBar mainMenu = new JMenuBar();
    private JButton vozaci = new JButton("Vozaci");
    private JButton voznje = new JButton("Voznje");
    private JButton izvestaji = new JButton("Izvestaji");
    private JButton pretragaVozaca = new JButton("Pretraga vozaca");
    private JButton btnLogout = new JButton("Logout");

    private TaxiSluzba taxiSluzba;

    public DispecerProzor(TaxiSluzba taxiSluzba){
         this.taxiSluzba = taxiSluzba;
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
        mainMenu.add(izvestaji);
        mainMenu.add(btnLogout);
        mainMenu.add(pretragaVozaca);
    }

    private void initActions(){
        vozaci.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VozaciTabela pp = new VozaciTabela(taxiSluzba);
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
        izvestaji.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DispecerProzor.this.dispose();
                IzvestajiProzor izvestajiProzor = new IzvestajiProzor(taxiSluzba);
                izvestajiProzor.setVisible(true);
            }
        });
        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DispecerProzor.this.dispose();
                LoginProzor loginProzor = new LoginProzor(taxiSluzba);
                loginProzor.setVisible(true);
            }
        });



    }


}
