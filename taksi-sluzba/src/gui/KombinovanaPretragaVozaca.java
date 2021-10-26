package gui;

import korisnici.Vozac;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class KombinovanaPretragaVozaca extends JFrame {
   int broj=20;
    private JLabel lblKorisnickiIme = new JLabel("Pretrazite vozaca po imenu: ");
    private  JTextField txtIme = new JTextField(broj);
    private JLabel lblKorisnickoPrezime = new JLabel("Pretrazite vozaca po prezimenu: ");
    private  JTextField txtPrezime = new JTextField(broj);
    private JLabel lblPlataVozacaOd = new JLabel(" Pretrazite vozaca po plati(min): ");
    private JFormattedTextField txtPlataVozacaOd = new JFormattedTextField(broj);
    private JLabel lblPlataVozacaDo = new JLabel(" Pretrazite vozaca po plati(max): ");
    private JFormattedTextField txtPlataVozacaDo = new JFormattedTextField(broj);
    private JLabel lblAuto = new JLabel("Pretrazite vozaca po automobilu: ");
    private JTextField txtAuto = new JTextField(broj);
    private JButton btnPretraga = new JButton("Pretrazi vozace");

    private TaxiSluzba taxiSluzba;

    public KombinovanaPretragaVozaca(TaxiSluzba taxiSluzba){
        setTitle("Pretraga vozaca");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,500);
        setResizable(false);
        initGui();
        initActions();
    }

    private void initGui(){
        MigLayout mig = new MigLayout("wrap 2", "[][]","[]10[][]10[]");
        setLayout(mig);
        add(lblKorisnickiIme);
        add(txtIme);
        add(lblKorisnickoPrezime);
        add(txtPrezime);
        add(lblPlataVozacaOd);
        add(txtPlataVozacaOd);
        txtPlataVozacaOd.setValue(null);
        txtPlataVozacaOd.setColumns(broj);
        //PERSIST-da se obrise vrednost iz polja
        txtPlataVozacaOd.setFocusLostBehavior(JFormattedTextField.PERSIST);
        add(lblPlataVozacaDo);
        add(txtPlataVozacaDo);
        txtPlataVozacaDo.setValue(null);
        txtPlataVozacaDo.setColumns(broj);
        txtPlataVozacaDo.setFocusLostBehavior(JFormattedTextField.PERSIST);
        add(lblAuto);
        add(txtAuto);
        add(btnPretraga);
    }

    private void initActions(){
        btnPretraga.addActionListener(e -> {
            Double plataOd;
            String plataStringOd = txtPlataVozacaOd.getText().trim();
            plataStringOd = plataStringOd.replaceAll(",", "");
            if (plataStringOd.equals("")) {
                plataOd = null;
            } else {
                plataOd = Double.parseDouble(plataStringOd);
            }

            Double plataDo;
            String plataStringDo = txtPlataVozacaDo.getText().trim();
            plataStringDo = plataStringDo.replaceAll(",", "");
            if (plataStringDo.equals("")) {
                plataDo = null;
            } else {
                plataDo = Double.parseDouble(plataStringDo);
            }

            ArrayList<Vozac> pretrazenVozac = TaxiSluzba.kombinovanaPretraga(txtIme.getText(), txtPrezime.getText(),
                    plataOd,plataDo, txtAuto.getText());
            System.out.println(pretrazenVozac);
            PronadjeniVozac pronadjenVozac = new PronadjeniVozac(pretrazenVozac);
            pronadjenVozac.setVisible(true);
            System.out.println();
        });

    }
}
