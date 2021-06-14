package gui;

import korisnici.Vozac;
import taxiSluzba.TaxiSluzba;
import voznja.StatusVoznje;
import voznja.Voznja;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;
import java.util.List;

import static taxiSluzba.TaxiSluzba.obrisiVoznju;

public class VoznjaTabela extends JFrame {
    private JToolBar mainToolbar = new JToolBar();
    private JButton btnAdd = new JButton("Dodaj");
    private JButton btnEdit = new JButton("Izmeni");
    private JButton btnDelete = new JButton("Obrisi");

    private DefaultTableModel tableModel;
    private JTable voznjeTabela;

    private TaxiSluzba taxiSluzba;

    public VoznjaTabela() {
        // this.taxiSluzba = taxiSluzba;
        setTitle("Voznje");
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGui();
        initActions();
    }

    private void initGui() {

        mainToolbar.add(btnAdd);
        mainToolbar.add(btnEdit);
        mainToolbar.add(btnDelete);
        add(mainToolbar, BorderLayout.NORTH);
        mainToolbar.setFloatable(false);

        String[] zaglavlja = new String[]{"Id", "Datum kreiranja", "Adresa polaska", "Adresa Destinacije", "Status voznje", "Broj km", "Trajanje voznje", "Korisnik", "Vozac", "Tip kreirane voznje"};
        Object[][] sadrzaj = new Object[TaxiSluzba.getSveVoznje().size()][zaglavlja.length];

        List<Voznja> sveVoznje = TaxiSluzba.getSveVoznje();

        for (int i = 0; i < sveVoznje.size(); i++) {
            Voznja voznja = sveVoznje.get(i);

            sadrzaj[i][0] = voznja.getId();
            sadrzaj[i][1] = voznja.getDatumKreirnja();
            sadrzaj[i][2] = voznja.getAdresaPolaska();
            sadrzaj[i][3] = voznja.getAdresaDestinacije();
            sadrzaj[i][4] = voznja.getStatus();
            sadrzaj[i][5] = voznja.getBrojKM();
            sadrzaj[i][6] = voznja.getTrajanjeVoznje();
            sadrzaj[i][7] = voznja.getMusterija().getKorisnickoIme();
            sadrzaj[i][8] = voznja.getVozac();
            sadrzaj[i][9] = voznja.getTipKreiraneVoznje();
        }

        tableModel = new DefaultTableModel(sadrzaj, zaglavlja);
        voznjeTabela = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(voznjeTabela.getModel());

        voznjeTabela.setRowSorter(sorter);
        voznjeTabela.setRowSelectionAllowed(true);
        voznjeTabela.setColumnSelectionAllowed(false);
        voznjeTabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        voznjeTabela.setDefaultEditor(Object.class, null);
        voznjeTabela.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(voznjeTabela);
        add(scrollPane, BorderLayout.CENTER);


    }

    private void initActions() {
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int red = voznjeTabela.getSelectedRow();

                if (red == -1) {
                    JOptionPane.showMessageDialog(null, "Molimo izaberite red",
                            "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id = (int) tableModel.getValueAt(red, 0);
                    Voznja voznja = TaxiSluzba.pronadjiVoznjuPoID(id);

                    int izbor = JOptionPane.showConfirmDialog(null,
                            "Da li ste sigurni da zelite da obrisete voznju?", voznja.getId() +
                                    " - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
                    if (izbor == JOptionPane.YES_OPTION) {
                        TaxiSluzba.obrisiVoznju(voznja.getId());
                        tableModel.removeRow(red);
                    }
                }

            }


        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int red = voznjeTabela.getSelectedRow();
//                int id = (int) tableModel.getValueAt(red, 0);
//                Voznja voznja = TaxiSluzba.pronadjiVoznjuPoID(id);
//                StatusVoznje statusVoznje = voznja.getStatus();

                if (red == -1 ) {
                    JOptionPane.showMessageDialog(null, "Molimo izaberite red ",
                            "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id = (int) tableModel.getValueAt(red, 0);
                    Voznja voznja = TaxiSluzba.pronadjiVoznjuPoID(id);
                    String statusVoznje = (voznja.getStatus()).toString();
                    if (statusVoznje.equals("KREIRANA")){
                        DodeljivanjeVoznjeForma dv = new DodeljivanjeVoznjeForma(voznja.getAdresaPolaska(),voznja.getAdresaDestinacije(),voznja.getMusterija().getKorisnickoIme());
                        dv.setVisible(true);

                        TaxiSluzba.izmeniVoznju(voznja.getId(), voznja.getDatumKreirnja(), voznja.getAdresaPolaska(), voznja.getAdresaDestinacije(),
                                voznja.getStatus(), voznja.getBrojKM(), voznja.getTrajanjeVoznje(), voznja.getMusterija(), voznja.getVozac());
                    }else {
                        JOptionPane.showMessageDialog(null, "Molimo izaberite red ",
                                "Greska", JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });

    }
}
