package gui;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;

import javax.swing.*;

public class Aukcija extends JFrame {
    private JMenuBar mainMenu = new JMenuBar();
    private JLabel lblVreme = new JLabel(" Unesite vreme:");
    private JTextField txtVreme = new JTextField(20);
    private JButton bntOk = new JButton("OK");
    private JButton btnCancel = new JButton("Cancel");
    private TaxiSluzba taxiSluzba;

    public Aukcija(){

        setTitle("Prijava ");
        setSize(500,500);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE );
        setLocationRelativeTo(null);
        initMenu();
        initGUI();
        initActions();
    }

    private void initMenu(){
        setJMenuBar(mainMenu);
    }

    public void initGUI(){
        MigLayout mig = new MigLayout("wrap 2", "[][]", "[]10[][]10[]");
        setLayout(mig);
        add(lblVreme);
        add(txtVreme);
        add(new JLabel());
        add(bntOk,"split 2");
        add(btnCancel);
       // getRootPane().setDefaultButton(bntOk);
    }
    public void initActions(){};
}
