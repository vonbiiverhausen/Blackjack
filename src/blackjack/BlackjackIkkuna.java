package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackjackIkkuna extends JFrame {
    private JButton bnOtaKortti, 
            bnPelaaKasi;
    private JScrollPane jScrollPelaajanKortit,
            jScrollTalonKortit;
    private JList<String> jPelaajanKorttiLista,
            jTalonKorttiLista;
    private JLabel lbNaytaPelaajaPisteet,
            lbNaytaTaloPisteet,
            lbPelaaja,
            lbPelaajaKortit,
            lbPelaajaPisteet,
            lbTalo,
            lbTaloKortit,
            lbTaloPisteet,
            lbNaytaPanos,
            lbNaytaSaldo,
            lbPanos,
            lbSaldo;
    private JMenu mPeli;
    private JMenuBar mbMain;
    private JMenuItem miLopeta,
            miPelaa;
    private JPanel pelaajaPanel,
            taloPanel;
    //private PelaajanKasi pelaaja;
    private DefaultListModel listModelPelaaja, listModelTalo;
    private AsetaPanos panosIkkuna;
    private PeliMoottori pelimoottori;
    
    public BlackjackIkkuna(PeliMoottori pelimoottori) {
        //this.pelaaja = pelaaja;
        //this.talo = talo;
        this.pelimoottori = pelimoottori;
        this.panosIkkuna = new AsetaPanos();
        panosIkkuna.asetaPelimoottori(pelimoottori); // Kerrotaan Panosikkunalle mitä Pelimoottori-oliota käytetään
        
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
        lbSaldo = new JLabel();
        lbPanos = new JLabel();
        lbNaytaSaldo = new JLabel();
        lbNaytaPanos = new JLabel();
        
        napitPaalle(false);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Blackjack");

        pelaajaPanel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pelaajaPanel.setPreferredSize(new Dimension(250, 300));

        lbPelaajaKortit.setText("Kortit: ");

        lbPelaajaPisteet.setText("Pisteet: ");

        bnOtaKortti.setText("Ota Kortti");
        bnOtaKortti.addActionListener((ActionEvent evt) -> {
            this.pelimoottori.pelaajaOttaaKortin();
        });

        bnPelaaKasi.setText("Pelaa Käsi");
        bnPelaaKasi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                pelimoottori.taloPelaa();
            }
        });

        jScrollPelaajanKortit.setViewportView(jPelaajanKorttiLista);
        
        //lbNaytaSaldo.setText(String.valueOf(pelaaja.haeSaldo()));

        lbNaytaPanos.setText("0");

        lbPanos.setText("Panos:");

        lbSaldo.setText("Saldo:");

        GroupLayout pelaajaPanelLayout = new GroupLayout(pelaajaPanel);
        pelaajaPanel.setLayout(pelaajaPanelLayout);
        pelaajaPanel.setLayout(pelaajaPanelLayout);
        pelaajaPanelLayout.setHorizontalGroup(
            pelaajaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pelaajaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pelaajaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPelaajanKortit)
                    .addGroup(pelaajaPanelLayout.createSequentialGroup()
                        .addComponent(bnOtaKortti, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                        .addComponent(bnPelaaKasi))
                    .addGroup(pelaajaPanelLayout.createSequentialGroup()
                        .addComponent(lbPelaaja)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbSaldo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNaytaSaldo))
                    .addGroup(pelaajaPanelLayout.createSequentialGroup()
                        .addComponent(lbPelaajaPisteet)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNaytaPelaajaPisteet)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pelaajaPanelLayout.createSequentialGroup()
                        .addComponent(lbPelaajaKortit, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbPanos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNaytaPanos)))
                .addContainerGap())
        );
        pelaajaPanelLayout.setVerticalGroup(
            pelaajaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pelaajaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pelaajaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPelaaja)
                    .addComponent(lbNaytaSaldo)
                    .addComponent(lbSaldo))
                .addGap(4, 4, 4)
                .addGroup(pelaajaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPelaajaPisteet)
                    .addComponent(lbNaytaPelaajaPisteet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pelaajaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbPelaajaKortit)
                    .addComponent(lbNaytaPanos)
                    .addComponent(lbPanos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPelaajanKortit, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pelaajaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bnOtaKortti)
                    .addComponent(bnPelaaKasi))
                .addContainerGap())
        );

        taloPanel.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        taloPanel.setPreferredSize(new java.awt.Dimension(250, 300));

        // lbTalo.setText(talo.haeNimi());

        lbTaloKortit.setText("Kortit: ");

        lbTaloPisteet.setText("Pisteet: ");

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
                panosIkkuna.setVisible(true);
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
    
    public void asetaTalonNimi(String nimi) {
        lbTalo.setText(nimi);
    }
    
    public void asetaPelaajanNimi(String nimi) {
        lbPelaaja.setText(nimi);
    }
    
    public void napitPaalle(boolean paalle) {
        this.bnOtaKortti.setEnabled(paalle);
        this.bnPelaaKasi.setEnabled(paalle);
    }
    
    public void naytaSaldo(int saldo) {
        this.lbNaytaSaldo.setText(String.valueOf(saldo));
    }
    
    public void asetaOtsikko(String nimi) {
        this.setTitle("BlackJack - "+nimi);
    }
    
    public void asetaPisteet(int talonPisteet, int pelaajanPisteet) {
        lbNaytaTaloPisteet.setText(String.valueOf(talonPisteet));
        lbNaytaPelaajaPisteet.setText(String.valueOf(pelaajanPisteet));
    }
    
    public void nollaaListat() {
        listModelTalo.clear();
        listModelPelaaja.clear();
    }
    
    public void paivitaSaldo(int pelaajanSaldo, int panos) {
        this.lbNaytaSaldo.setText(String.valueOf(pelaajanSaldo));
        this.lbNaytaPanos.setText(String.valueOf(panos));
    }
    
    public void paivitaTalonKortit(String kortit) {
        listModelTalo.addElement(kortit);
    }
    
    public void paivitaPelaajanKortit(String kortit) {
        listModelPelaaja.addElement(kortit);
    }
    
    public void naytaTalonPisteet(String pisteet) {
        lbNaytaTaloPisteet.setText(String.valueOf(pisteet));
    }
    
    public void naytaPelaajanPisteet(String pisteet) {
        lbNaytaPelaajaPisteet.setText(String.valueOf(pisteet));
    }
}