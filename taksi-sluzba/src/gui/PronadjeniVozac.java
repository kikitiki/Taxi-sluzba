package gui;

import korisnici.Korisnik;
import korisnici.TipKorisnika;
import korisnici.Vozac;
import taxiSluzba.TaxiSluzba;
import voznja.Voznja;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PronadjeniVozac extends JFrame {
    private ArrayList<Vozac> pronadjenVozac = new ArrayList<>();
    private JToolBar mainToolbar = new JToolBar();
//    private JButton btnDelete = new JButton("Obrisi");
//    private JButton btnEdit = new JButton("Izmeni");

    private DefaultTableModel tableModel;
    private JTable vozaciTabela;

    private TaxiSluzba taxiSluzba;
    private Vozac vozac;

    public PronadjeniVozac(ArrayList<Vozac> pronadjenVozac){
        this.pronadjenVozac = pronadjenVozac;
        this.taxiSluzba = taxiSluzba;
        setSize(700,500);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
    }



    private void initMenu(){
        add(mainToolbar, BorderLayout.NORTH);
        mainToolbar.setFloatable(false);

        String[] zaglavlja = new String[]{"JMBG", "Korisnicko ime", "Ime", "Prezime", "Adresa", "Pol", "Broj telefona", "Broj clanske karte", "Plata", "Vozilo"};
        Object[][] sadrzaj = new Object[this.pronadjenVozac.size()][zaglavlja.length];
        for (int i = 0; i < this.pronadjenVozac.size(); i++) {
            Vozac vozac = this.pronadjenVozac.get(i);
            sadrzaj[i][0] = vozac.getJmbg();
            sadrzaj[i][1] = vozac.getKorisnickoIme();
            sadrzaj[i][2] = vozac.getIme();
            sadrzaj[i][3] = vozac.getPrezime();
            sadrzaj[i][4] = vozac.getAdresa();
            sadrzaj[i][5] = vozac.getPol();
            sadrzaj[i][6] = vozac.getBrojTelefona();
            sadrzaj[i][7] = vozac.getBrojClanskeKarte();
            sadrzaj[i][8] = vozac.getPlata();
            sadrzaj[i][9] = vozac.getTaxi();
        }

        tableModel = new DefaultTableModel(sadrzaj,zaglavlja);
        vozaciTabela = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(vozaciTabela.getModel());

        vozaciTabela.setRowSorter(sorter);
        vozaciTabela.setRowSelectionAllowed(true);


        vozaciTabela.setRowSelectionAllowed(true);
        vozaciTabela.setColumnSelectionAllowed(false);
        vozaciTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        vozaciTabela.setDefaultEditor(Object.class,null);
        vozaciTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(vozaciTabela);
        add(scrollPane,BorderLayout.CENTER);

    }
    private  void initActions(){


    }
}
