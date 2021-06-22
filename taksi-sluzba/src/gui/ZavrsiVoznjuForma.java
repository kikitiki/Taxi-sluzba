package gui;

import korisnici.Pol;
import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;
import voznja.StatusVoznje;
import voznja.Voznja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static taxiSluzba.TaxiSluzba.izmeniVoznju;
import static taxiSluzba.TaxiSluzba.voznjeFajl;

public class ZavrsiVoznjuForma extends JFrame {
   // private JLabel lblStatusVoznje = new JLabel("Status voznje");
  //  private JTextField txtStatusVoznje = new JTextField("ZAVRSENA");
    private JLabel lblBrojKm = new JLabel("Unesite broj predjenih km");
    private JTextField txtBrojKm = new JTextField(10);
    private JLabel lblTrajanjeVoznje = new JLabel("Unesite trajanje voznje");
    private JTextField txtTrajanjeVoznje = new JTextField(10);
    private JButton btnOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");

    private TaxiSluzba taxiSluzba;
    private Voznja voznja;

    public ZavrsiVoznjuForma(Voznja voznja,TaxiSluzba taxiSluzba){
        this.taxiSluzba = taxiSluzba;
        this.voznja = voznja;

        setTitle("Zavrsi voznje");
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

      //  add(lblStatusVoznje);
      //  add(txtStatusVoznje);
        add(lblBrojKm);
        add(txtBrojKm);
        add(lblTrajanjeVoznje);
        add(txtTrajanjeVoznje);
        add(btnOk);
        add(btnCancel);
    }

    public void initActions(){

        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (validacija()){
                   // StatusVoznje statusVoznje = StatusVoznje.valueOf(txtStatusVoznje.getText().trim());
                    Integer trajanjeVoznje = Integer.valueOf(txtTrajanjeVoznje.getText().trim());
                    Double brojKm = Double.valueOf(txtBrojKm.getText().trim());

                    //izmeniVoznju(voznja.getId(),voznja.getDatumKreirnja(),voznja.getAdresaPolaska(),voznja.getAdresaDestinacije(),statusVoznje,brojKm,trajanjeVoznje,voznja.getMusterija(),voznja.getVozac());
                    taxiSluzba.zavrsiVoznju(voznja.getId(),brojKm,trajanjeVoznje);
                    ZavrsiVoznjuForma.this.dispose();
                    ZavrsiVoznjuForma.this.setVisible(false);
                }


            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ZavrsiVoznjuForma.this.dispose();
                ZavrsiVoznjuForma.this.setVisible(false);
            }
        });

    }

    private boolean validacija(){
        boolean ok =true;
        String poruka = "Molimo ispravite sledece greske!";
        if(txtTrajanjeVoznje.getText().trim().equals("")){
            poruka += "- Unesite trajanje voznje\n";
            ok = false;
        }
        if(txtBrojKm.getText().trim().equals("")){
            poruka += "- Unesite broj predjenih km\n";
            ok = false;
        }
        if (ok == false){
            JOptionPane.showMessageDialog(null,poruka,"Neispravni podaci",JOptionPane.WARNING_MESSAGE);
        }
        return ok;
    }
}
