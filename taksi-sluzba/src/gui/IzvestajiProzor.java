package gui;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class IzvestajiProzor extends JFrame {

    int broj = 15;

    private JMenuBar mainMenu = new JMenuBar();
    private JButton btnDnevni = new JButton("Dnevni izvestaj");
    private JButton btnNedeljni = new JButton("Nedelni izvestaj");
    private JButton btnMesecni = new JButton("Mesecni izvestaj");
    private JButton btnGodisnji = new JButton("Godisnji izvestaj");

    String ukupanBrVoznji = String.valueOf(TaxiSluzba.zavrseneVoznje().size());
    String brVoznjiApp = String.valueOf(TaxiSluzba.dobaviVoznjeKreiraneAplikacijom().size());
    String brVoznjiTel = String.valueOf(TaxiSluzba.dobaviVoznjeKreiraneTelefonom().size());

    private JLabel lblSveVoznje = new JLabel("Ukupan broj voznji: ");
    private JTextField txtUkupanBrojVoznji = new JTextField(ukupanBrVoznji, broj);
    private JLabel lblVoznjeApp = new JLabel("Broj voznji putem aplikacije: ");
    private JTextField txtUkupanBrojVoznjiApp = new JTextField(brVoznjiApp, broj);
    private JLabel lblVoznjeTel = new JLabel("Broj voznji putem telefona: ");
    private JTextField txtUkupanBrojVoznjiTel = new JTextField(brVoznjiTel, broj);
    private JButton btnLogout = new JButton("Logout");






    private TaxiSluzba taxiSluzba;

    public IzvestajiProzor(TaxiSluzba taxiSluzba){
        this.taxiSluzba = taxiSluzba;
        setTitle("Meni dispecera");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        setLocationRelativeTo(null);
        initMenu();
        initGUI();
        initActions();

    }
    public void initGUI() {
        MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
        setLayout(mig);
        add(btnDnevni);
        add(btnNedeljni);
        add(btnMesecni);
        add(btnGodisnji);
        add(lblSveVoznje);
        add(txtUkupanBrojVoznji);
        txtUkupanBrojVoznji.setEditable(false);
        add(lblVoznjeApp);
        add(txtUkupanBrojVoznjiApp);
        txtUkupanBrojVoznjiApp.setEditable(false);
        add(lblVoznjeTel);
        add(txtUkupanBrojVoznjiTel);
        txtUkupanBrojVoznjiTel.setEditable(false);
        add(btnLogout);
    }



    private void initMenu(){
        setJMenuBar(mainMenu);
    }

    private void initActions(){
        btnDnevni.addActionListener(e -> {
            try{
                DnevniProzor pronadjeneVoznje = new DnevniProzor();
                pronadjeneVoznje.setVisible(true);
            }
            catch(DateTimeParseException ex) {
                System.out.println(ex.getMessage());
            };
        });

        btnNedeljni.addActionListener(e -> {
            try{
                NedeljniProzor pronadjeneVoznje = new NedeljniProzor();
                pronadjeneVoznje.setVisible(true);
            }
            catch(DateTimeParseException ex) {
                System.out.println(ex.getMessage());
            };
        });

        btnMesecni.addActionListener(e -> {
            try{
                MesecniProzor pronadjeneVoznje = new MesecniProzor();
                pronadjeneVoznje.setVisible(true);
            }
            catch(DateTimeParseException ex) {
                System.out.println(ex.getMessage());
            };
        });

        btnLogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                IzvestajiProzor.this.dispose();
                LoginProzor loginProzor = new LoginProzor(taxiSluzba);
                loginProzor.setVisible(true);
            }
        });

    }
}
