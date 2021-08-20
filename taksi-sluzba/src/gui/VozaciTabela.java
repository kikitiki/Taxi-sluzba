package gui;

import korisnici.Vozac;
import taxiSluzba.TaxiSluzba;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VozaciTabela extends JFrame {
    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton("Dodaj");
    private JButton btnDelete = new JButton("Obrisi");
    private JButton btnEdit = new JButton("Izmeni");
    private JButton btnKombinovanaPretraga = new JButton(("Kombinovana pretraga"));

    private DefaultTableModel tableModel;
    private JTable vozaciTabela;

    private TaxiSluzba taxiSluzba;

    public VozaciTabela(TaxiSluzba taxiSluzba){
        this.taxiSluzba = taxiSluzba;
        setSize(700,500);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initMenu();
        initActions();
    }



    private void initMenu(){
        mainToolbar.add(btnAdd);
        mainToolbar.add(btnDelete);
        mainToolbar.add(btnEdit);
        mainToolbar.add(btnKombinovanaPretraga);
        add(mainToolbar, BorderLayout.NORTH);
        mainToolbar.setFloatable(false);

        String[] zaglavlja = new String[]{"Tip Korisnika","Korisnicko ime","Ime","Prezime","JMBG","Adresa","Pol","Broj telefeona","Plata"};
        Object[][] sadrzaj = new Object[TaxiSluzba.dobaviVozace().size()][zaglavlja.length];

        for (int i =0 ;  i < TaxiSluzba.dobaviVozace().size(); i++){
            Vozac vozac = TaxiSluzba.dobaviVozace().get(i);
            sadrzaj[i][0]= vozac.getTipKorisnika();
            sadrzaj[i][1] = vozac.getKorisnickoIme();
            sadrzaj[i][2] = vozac.getIme();
            sadrzaj[i][3] = vozac.getPrezime();
            sadrzaj[i][4] = vozac.getJmbg();
            sadrzaj[i][5] = vozac.getAdresa();
            sadrzaj[i][6] = vozac.getPol();
            sadrzaj[i][7] = vozac.getBrojTelefona();
            sadrzaj[i][8] = vozac.getPlata();
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

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = vozaciTabela.getSelectedRow();

                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Molimo izaberite red",
                            "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    String jmbg = tableModel.getValueAt(red, 4).toString();
                    Vozac vozac = TaxiSluzba.pronadjiVozacaIzTabele(jmbg);

                    int izbor = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da zelite da obrisete vozaca?", vozac.getKorisnickoIme() +
                                    " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                    if (izbor == JOptionPane.YES_OPTION){
                        TaxiSluzba.obrisiVozaca(vozac.getBrojClanskeKarte());
                        tableModel.removeRow(red);
                        TaxiSluzba.sacuvajKorisnike(TaxiSluzba.korisniciFajl);
                    }
                }

            }


        });

        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DodajIzmeniVozaca vf = new DodajIzmeniVozaca(taxiSluzba,null);
                vf.setVisible(true);
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = vozaciTabela.getSelectedRow();
                if(red == -1){
                    JOptionPane.showMessageDialog(null, "Molimo izaberite red",
                            "Greska", JOptionPane.WARNING_MESSAGE);
                }else {
                    String korisnickoIme = tableModel.getValueAt(red,1).toString();
                    Vozac vozac = TaxiSluzba.pronadjiVozacaPoKorisnickomImenu(korisnickoIme);
                    if(vozac == null){
                        JOptionPane.showMessageDialog(null,
                                "Greska prilikom pronalazenja vozaca sa tim korisnickim imenom",
                                "Greska", JOptionPane.WARNING_MESSAGE);
                    }else {
                        DodajIzmeniVozaca vf = new DodajIzmeniVozaca(taxiSluzba,vozac);
                        vf.setVisible(true);
                    }
                }
            }
        });

        btnKombinovanaPretraga.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                KombinovanaPretragaVozaca kombinovanaPretragaVozaca= new KombinovanaPretragaVozaca();
                kombinovanaPretragaVozaca.setVisible(true);
            }
        });




    }
}
