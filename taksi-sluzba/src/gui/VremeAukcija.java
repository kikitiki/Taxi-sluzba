package gui;

import korisnici.Vozac;
import net.miginfocom.swing.MigLayout;
import voznja.Ponuda;
import voznja.Voznja;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VremeAukcija extends JFrame {
    int broj = 15;

    private JFormattedTextField txtVreme = new JFormattedTextField(broj);
    private JLabel lblVreme = new JLabel("Vreme");
    private JButton btnOk = new JButton("OK");

    private Voznja voznja;
    private Vozac vozac;

    public VremeAukcija(Voznja voznja,Vozac vozac){
        this.voznja= voznja;
        this.vozac = vozac;
        setTitle("Vreme za voznju");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(500,500);
        setResizable(false);
        initGui();
        initActions();
    }

    public void initGui(){
        MigLayout mig = new MigLayout("wrap 2", "[][]","[]10[][]10[]");
        setLayout(mig);
        add(lblVreme);
        add(txtVreme);
        add(btnOk);
        txtVreme.setValue(0);
        txtVreme.setColumns(broj);
        //PERSIST-da se obrise vrednost iz polja
        txtVreme.setFocusLostBehavior(JFormattedTextField.COMMIT_OR_REVERT);
    }
    public void initActions(){
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("usao....");
                String vremeString = txtVreme.getText().trim();
                int vreme = Integer.parseInt(vremeString);

                if (vreme == 0) {
                    // izbaci ovde prozor da mora uneti broj koji je razllici od 0
                    System.out.println("Unesi br razlicit od nule");
                    return;
                }

                Ponuda p = new Ponuda(123123, vreme, vozac, voznja);

                // nakon sto smo napravili ponudu, potrebno je ponudu sacuvati u fajl
                // ovo cemo raditi tako sto cemo skroz do ove GUI komponente proslediti i TaxiSluzba objekat
                // a u TaxiSluzba treba napraviti metodu za citanje/upis ponuda u fajl
                // U Taxi Sluzbi nam je potrebna metoda koja na osnovu izabrane voznje pronalazi sve ponude koje su
                // vezane za tu voznju.

                System.out.println("Kreirana ponuda: ");
                System.out.println(p);
                // zatvoriti prozor na kraju
            }

        });
    }
}
