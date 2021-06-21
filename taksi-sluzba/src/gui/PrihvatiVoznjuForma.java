package gui;

import korisnici.Vozac;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;
import voznja.StatusVoznje;
import voznja.Voznja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static taxiSluzba.TaxiSluzba.izmeniVoznju;
import static taxiSluzba.TaxiSluzba.pronadjiVozacaPoKorisnickomImenu;

public class PrihvatiVoznjuForma extends JFrame {

    private JLabel lblStatusVoznje = new JLabel("Status voznje");
    private JTextField txtStatusVoznje = new JTextField("PRIHVACENA");

    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private TaxiSluzba taxiSluzba;
    private Voznja voznja;

    public PrihvatiVoznjuForma(Voznja voznja){
        this.taxiSluzba = taxiSluzba;
        this.voznja =voznja;

        txtStatusVoznje.setEditable(false);

        setTitle("Prihvatanje voznje");
        setSize(300,300);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        txtStatusVoznje.setEditable(false);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
        pack();
    }

    public void initMenu(){
        MigLayout mig = new MigLayout("wrap 2","[][]","[]10[][]10[]");
        setLayout(mig);

        add(lblStatusVoznje);
        add(txtStatusVoznje);
        add(btnOk);
        add(btnCancel);
    }
    public void initActions(){

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                StatusVoznje statusVoznje = StatusVoznje.valueOf(txtStatusVoznje.getText().trim());

                izmeniVoznju(voznja.getId(),voznja.getDatumKreirnja(),voznja.getAdresaPolaska(),voznja.getAdresaDestinacije(),statusVoznje,voznja.getBrojKM(),voznja.getTrajanjeVoznje(),voznja.getMusterija(),voznja.getVozac());
                PrihvatiVoznjuForma.this.dispose();
                PrihvatiVoznjuForma.this.setVisible(false);
            }


        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PrihvatiVoznjuForma.this.dispose();
                PrihvatiVoznjuForma.this.setVisible(false);
            }
        });
    }
}
