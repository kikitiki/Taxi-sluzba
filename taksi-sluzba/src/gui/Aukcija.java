package gui;

import net.miginfocom.swing.MigLayout;
import taxiSluzba.TaxiSluzba;
import voznja.Voznja;
import korisnici.Korisnik;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Aukcija extends JFrame {
    private JToolBar mainToolbar = new JToolBar();
     private JButton btnPrihvati = new JButton("Prihvati aukciju");

    private TaxiSluzba taxiSluzba;
    private Voznja voznja;

    private DefaultTableModel tableModel;
    private JTable Aukcija;

    public Aukcija(){
       // this.taxiSluzba= taxiSluzba;
        setTitle("Aukcija");
        setSize(500, 300);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initGui();
        initActions();
    }


    public void initGui(){
        mainToolbar.add(btnPrihvati);
        add(mainToolbar, BorderLayout.NORTH);
        mainToolbar.setFloatable(false);

        String[] zaglavlja = new String[]{"Id", "Datum kreiranja", "Adresa polaska", "Adresa Destinacije", "Status voznje", "Broj km", "Trajanje voznje", "Korisnik", "Vozac", "Tip kreirane voznje"};
        Object[][] sadrzaj = new Object[TaxiSluzba.dobaviVoznjeKreiraneTelefonAukcija().size()][zaglavlja.length];

        List<Voznja> sveVoznje = TaxiSluzba.dobaviVoznjeKreiraneTelefonAukcija();

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
        Aukcija = new JTable(tableModel);
        TableRowSorter<TableModel> sorter = new TableRowSorter<TableModel>(Aukcija.getModel());

        Aukcija.setRowSorter(sorter);
        Aukcija.setRowSelectionAllowed(true);
        Aukcija.setColumnSelectionAllowed(false);
        Aukcija.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        Aukcija.setDefaultEditor(Object.class, null);
        Aukcija.getTableHeader().setReorderingAllowed(false);

        JScrollPane scrollPane = new JScrollPane(Aukcija);
        add(scrollPane, BorderLayout.CENTER);

    }
    public void initActions(){
        btnPrihvati.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                int red = Aukcija.getSelectedRow();

                if (red == -1 ) {
                    JOptionPane.showMessageDialog(null, "Molimo izaberite red ",
                            "Greska", JOptionPane.WARNING_MESSAGE);
                } else {
                    int id = (int) tableModel.getValueAt(red, 0);
                    Voznja voznja = TaxiSluzba.pronadjiVoznjuPoID(id);
                    String statusVoznje = (voznja.getStatus()).toString();
                    if (statusVoznje.equals("KREIRANA")){
                        VremeAukcija vremeAukcija = new VremeAukcija();
                        vremeAukcija.setVisible(true);
                    }else {
                        JOptionPane.showMessageDialog(null, "Molimo izaberite red ",
                                "Greska", JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        });

    };
}
