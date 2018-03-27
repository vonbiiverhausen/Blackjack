package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackjackIkkuna extends JFrame {
    private JButton bnOtaKortti;
    private JButton bnPelaaKasi;
    private JScrollPane jScrollPelaajanKortit;
    private JList<String> jPelaajanKorttiLista;
    private JScrollPane jScrollTalonKortit;
    private JList<String> jTalonKorttiLista;
    private JLabel lbNaytaPelaajaPisteet;
    private JLabel lbNaytaTaloPisteet;
    private JLabel lbPelaaja;
    private JLabel lbPelaajaKortit;
    private JLabel lbPelaajaPisteet;
    private JLabel lbTalo;
    private JLabel lbTaloKortit;
    private JLabel lbTaloPisteet;
    private JMenu mPeli;
    private JMenuBar mbMain;
    private JMenuItem miLopeta;
    private JMenuItem miPelaa;
    private JPanel pelaajaPanel;
    private JPanel taloPanel;
    private PelaajanKasi pelaaja, talo;
    private Korttipakka korttipakka;
    private DefaultListModel listModelPelaaja, listModelTalo;
    
    public BlackjackIkkuna(PelaajanKasi pelaaja, PelaajanKasi talo, Korttipakka pakka) {
        this.pelaaja = pelaaja;
        this.talo = talo;
        this.korttipakka = pakka;
        listModelPelaaja = new DefaultListModel();
        listModelTalo = new DefaultListModel();
        
        pelaajaPanel = new JPanel();
        lbPelaaja = new JLabel();
        bnOtaKortti = new JButton();
        bnPelaaKasi = new JButton();
        taloPanel = new JPanel();
        lbTalo = new JLabel();
        lbTaloKortit = new JLabel();
        lbTaloPisteet = new JLabel();
        lbNaytaTaloPisteet = new JLabel();
        
        pelaajaPanel = new JPanel();
        lbPelaaja = new JLabel();
        lbPelaajaKortit = new JLabel();
        lbPelaajaPisteet = new JLabel();
        lbNaytaPelaajaPisteet = new JLabel();
        bnOtaKortti = new JButton();
        bnPelaaKasi = new JButton();
        jScrollPelaajanKortit = new JScrollPane();
        jPelaajanKorttiLista = new JList<>(listModelPelaaja);
        taloPanel = new JPanel();
        lbTalo = new JLabel();
        lbTaloKortit = new JLabel();
        lbTaloPisteet = new JLabel();
        lbNaytaTaloPisteet = new JLabel();
        jScrollTalonKortit = new JScrollPane();
        jTalonKorttiLista = new JList<>(listModelTalo);
        mbMain = new JMenuBar();
        mPeli = new JMenu();
        miPelaa = new JMenuItem();
        miLopeta = new JMenuItem();
        
        napitPaalle(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Blackjack");

        pelaajaPanel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pelaajaPanel.setPreferredSize(new Dimension(250, 300));

        lbPelaaja.setText(pelaaja.haeNimi());

        lbPelaajaKortit.setText("Kortit: ");

        lbPelaajaPisteet.setText("Pisteet: ");

        lbNaytaPelaajaPisteet.setText(""+pelaaja.selvitaSumma());

        bnOtaKortti.setText("Ota Kortti");
        bnOtaKortti.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                otaKortti(pelaaja);
            }
        });

        bnPelaaKasi.setText("Pelaa Käsi");
        bnPelaaKasi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Blackjack.pelaa(talo);
            }
        });

        jScrollPelaajanKortit.setViewportView(jPelaajanKorttiLista);

        GroupLayout pelaajaPanelLayout = new GroupLayout(pelaajaPanel);
        pelaajaPanel.setLayout(pelaajaPanelLayout);
        pelaajaPanelLayout.setHorizontalGroup(
            pelaajaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pelaajaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pelaajaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPelaajanKortit)
                    .addGroup(pelaajaPanelLayout.createSequentialGroup()
                        .addComponent(bnOtaKortti, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bnPelaaKasi))
                    .addGroup(pelaajaPanelLayout.createSequentialGroup()
                        .addGroup(pelaajaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(pelaajaPanelLayout.createSequentialGroup()
                                .addGroup(pelaajaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                    .addComponent(lbPelaaja)
                                    .addComponent(lbPelaajaPisteet))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNaytaPelaajaPisteet))
                            .addComponent(lbPelaajaKortit, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pelaajaPanelLayout.setVerticalGroup(
            pelaajaPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(pelaajaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPelaaja)
                .addGap(4, 4, 4)
                .addGroup(pelaajaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPelaajaPisteet)
                    .addComponent(lbNaytaPelaajaPisteet))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbPelaajaKortit)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPelaajanKortit, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pelaajaPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(bnOtaKortti)
                    .addComponent(bnPelaaKasi))
                .addContainerGap())
        );

        taloPanel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        taloPanel.setPreferredSize(new java.awt.Dimension(250, 300));

        lbTalo.setText(talo.haeNimi());

        lbTaloKortit.setText("Kortit: ");

        lbTaloPisteet.setText("Pisteet: ");

        lbNaytaTaloPisteet.setText(""+talo.selvitaSumma());

        jScrollTalonKortit.setViewportView(jTalonKorttiLista);

        GroupLayout taloPanelLayout = new GroupLayout(taloPanel);
        taloPanel.setLayout(taloPanelLayout);
        taloPanelLayout.setHorizontalGroup(
            taloPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(taloPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(taloPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollTalonKortit)
                    .addGroup(taloPanelLayout.createSequentialGroup()
                        .addGroup(taloPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(taloPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(lbTaloKortit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbTalo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(taloPanelLayout.createSequentialGroup()
                                .addComponent(lbTaloPisteet)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbNaytaTaloPisteet)))
                        .addGap(0, 59, Short.MAX_VALUE)))
                .addContainerGap())
        );
        taloPanelLayout.setVerticalGroup(
            taloPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(taloPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTalo)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(taloPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTaloPisteet)
                    .addComponent(lbNaytaTaloPisteet))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTaloKortit)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollTalonKortit, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        // Menubar
        mPeli.setText("Peli");

        miPelaa.setText("Uusi peli");
        miPelaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pakka.nollaaPakka();
                talo.nollaaKasi();
                pelaaja.nollaaKasi();
                
                lbNaytaTaloPisteet.setText(""+talo.selvitaSumma());
                lbNaytaPelaajaPisteet.setText(""+pelaaja.selvitaSumma());
                
                listModelTalo.clear();
                listModelPelaaja.clear();
                
                otaKortti(pelaaja);
                otaKortti(pelaaja);
                
                napitPaalle(true);
            }
        });
        mPeli.add(miPelaa);

        miLopeta.setText("Lopeta");
        miLopeta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.exit(0);
            }
        });
        mPeli.add(miLopeta);

        mbMain.add(mPeli);

        setJMenuBar(mbMain);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pelaajaPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(taloPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(pelaajaPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(taloPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }
    
    public void otaKortti(PelaajanKasi player) {
        player.otaKortti();
        
        int pisteet = player.selvitaSumma();
        if (player==this.talo) {
            listModelTalo.addElement(this.talo.naytaKasi());
            if (talo.onBlackjack()) {
                // Mertkitään että talo sai blackjackin. Siirrytään pisteiden vertaukseen
                lbNaytaTaloPisteet.setText("" + pisteet + " BLACKJACK!");
                Blackjack.vertaa(pelaaja, talo);
            } else if (pisteet > 21) {
                // Merkitään että talo ylitti 21 pistettä. Siirrytään pisteiden vertaukseen
                lbNaytaTaloPisteet.setText("" + pisteet + " YLI 21!");
                talo.saiYli21();
                Blackjack.vertaa(pelaaja, talo);
            } else {
                lbNaytaTaloPisteet.setText("" + pisteet);
            }
        } else {
            listModelPelaaja.addElement(player.naytaKasi());
            if (pelaaja.onBlackjack()) {
                // merkitään että on saatu blackjack. Vuoro siirtyy talolle
                lbNaytaPelaajaPisteet.setText("" + pisteet + " BLACKJACK!");
                Blackjack.pelaa(talo);
            } else if (pisteet > 21) {
                // merkitään että on ylitetty 21 pistettä. Vuoro siirtyy talolle
                lbNaytaPelaajaPisteet.setText("" + pisteet + " YLI 21!");
                pelaaja.saiYli21();
                Blackjack.pelaa(talo);
            } else {
                lbNaytaPelaajaPisteet.setText("" + pisteet);
            }
        }
    }
    
    public void napitPaalle(boolean paalle) {
        this.bnOtaKortti.setEnabled(paalle);
        this.bnPelaaKasi.setEnabled(paalle);
    }
}