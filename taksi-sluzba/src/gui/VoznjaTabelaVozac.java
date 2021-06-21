package gui;

import korisnici.Korisnik;
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
import java.util.List;



public class VoznjaTabelaVozac extends JFrame {
        private JToolBar mainToolbar = new JToolBar();
        private JButton btnPrihvatiVoznju= new JButton("Prihvati voznju");
        private JButton btnOdbijVoznju = new JButton("Odbij voznju");
        private JButton btnZavrsiVoznju = new JButton("Zavrsi voznju");

        private DefaultTableModel tableModel;
        private JTable VoznjaTabelaVozac;

        private TaxiSluzba taxiSluzba;
        private Korisnik ulogovaniKorisnik;

        public VoznjaTabelaVozac(TaxiSluzba taxiSluzba, Korisnik ulogovaniKorisnik) {
            this.taxiSluzba = taxiSluzba;
            this.ulogovaniKorisnik = ulogovaniKorisnik;
            setSize(700, 500);
            setResizable(true);
            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            initMenu();
            initActions();
        }

    private void  initMenu(){
        mainToolbar.add(btnPrihvatiVoznju);
        mainToolbar.add(btnOdbijVoznju);
        mainToolbar.add(btnZavrsiVoznju);

        add(mainToolbar, BorderLayout.NORTH);
        mainToolbar.setFloatable(false);

       List<Voznja> nadjeneVoznje = taxiSluzba.voznjeVozaca(this.ulogovaniKorisnik.getKorisnickoIme());


        String[] zaglavlja = new String[]{"Id", "Datum kreiranja", "Adresa polaska", "Adresa Destinacije", "Status voznje", "Broj km", "Trajanje voznje", "Korisnik", "Vozac", "Tip kreirane voznje"};
        Object[][] sadrzaj = new Object[nadjeneVoznje.size()][zaglavlja.length];

        for (int i = 0; i < nadjeneVoznje.size(); i++) {
            Voznja voznja =nadjeneVoznje.get(i);

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
        VoznjaTabelaVozac = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(VoznjaTabelaVozac.getModel());

        VoznjaTabelaVozac.setRowSorter(sorter);
        VoznjaTabelaVozac.setRowSelectionAllowed(true);
        VoznjaTabelaVozac.setColumnSelectionAllowed(false);
        VoznjaTabelaVozac.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        VoznjaTabelaVozac.setDefaultEditor(Object.class, null);
        VoznjaTabelaVozac.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(VoznjaTabelaVozac);
        add(scrollPane, BorderLayout.CENTER);


    }

    private void initActions() {

        btnPrihvatiVoznju.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int red = VoznjaTabelaVozac.getSelectedRow();


                if (red == -1 ) {
                    JOptionPane.showMessageDialog(null, "Molimo izaberite red ",
                            "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id = (int) tableModel.getValueAt(red, 0);
                    Voznja voznja = TaxiSluzba.pronadjiVoznjuPoID(id);
                    String statusVoznje = (voznja.getStatus()).toString();
                    if (statusVoznje.equals("DODELJENA")){
//                        DodeljivanjeVoznjeForma dv = new DodeljivanjeVoznjeForma(voznja);
//                        dv.setVisible(true);
                        PrihvatiVoznjuForma pv =new PrihvatiVoznjuForma(voznja);
                        pv.setVisible(true);

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

