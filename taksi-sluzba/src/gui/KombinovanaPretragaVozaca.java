package gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.util.ArrayList;

public class KombinovanaPretragaVozaca extends JFrame {
   int broj=20;
    private JLabel lblKorisnickiIme = new JLabel("Pretrazite vozaca po korisnickom imenu: ");
    private  JTextField txtIme = new JTextField(broj);
    private JLabel lblKorisnickoPrezime = new JLabel("Pretrazite vozaca po prezimenu: ");
    private  JTextField txtPrezime = new JTextField(broj);
    private JLabel lblPlataVozaca = new JLabel(" Pretrazite vozaca po plati: ");
    private JFormattedTextField txtPlataVozaca = new JFormattedTextField();
    private JLabel lbllozinka = new JLabel("Lozinka");
    private JLabel lblAuto = new JLabel("Pretrazite vozaca po automobilu: ");
    private JTextField txtAuto = new JTextField(broj);
    private JButton btnPretraga = new JButton("Pretrazi vozace");

    public KombinovanaPretragaVozaca(){
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
        add(lblPlataVozaca);
        add(txtPlataVozaca);
        txtPlataVozaca.setValue(Double.valueOf(0.0));
        txtPlataVozaca.setColumns(broj);
        add(lblAuto);
        add(txtAuto);
        add(btnPretraga);
    }

    private void initActions(){
        btnPretraga.addActionListener(e -> {
//            ArrayList<Vozaci> pretrazenVozac = Preduzece.pronadjiVozaca(txtKorisnickoIme.getText(), txtPrezime.getText(),
//                    Double.parseDouble(txtPlata.getText()), txtAuto.getText());
//            PronadjenVozac pronadjenVozac1 = new PronadjenVozac(pretrazenVozac);
//            pronadjenVozac1.setVisible(true);
        });
    }
}



