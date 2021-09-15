package gui;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;

public class VremeAukcija extends JFrame {
    int broj = 15;

    private JLabel lblVreme = new JLabel("Unesite vreme:");
    private JTextField txtVreme = new JTextField(broj);
    private JButton btnOk = new JButton("OK");

    public VremeAukcija(){
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
    }
    public void initActions(){

    }
}
