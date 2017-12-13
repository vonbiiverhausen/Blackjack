package blackjack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BlackjackIkkuna extends JFrame {
    private JFrame peliIkkuna;
//    private JPanel pelaajanPaneeli;
//    private JLabel lbKortit;
//    private JLabel lbNaytaKortit;
//    private JLabel lbNaytaPisteet;
//    private JLabel lbPelaaja;
//    private JLabel lbPisteet;
//    JButton bnOtaKortti;
//    JButton bnPelaaKasi;
    private JMenu mPeli;
    private JMenuBar mbMain;
    JMenuItem miLopeta;
    JMenuItem miPelaa;
    
    GroupLayout layout;
    
    public BlackjackIkkuna() {
        peliIkkuna = new JFrame();
//        pelaajanPaneeli = new JPanel();
//        lbPelaaja = new JLabel();
//        lbKortit = new JLabel();
//        lbPisteet = new JLabel();
//        lbNaytaPisteet = new JLabel();
//        lbNaytaKortit = new JLabel();
//        bnOtaKortti = new JButton();
//        bnPelaaKasi = new JButton();
        mbMain = new JMenuBar();
        mPeli = new JMenu();
        miPelaa = new JMenuItem();
        miLopeta = new JMenuItem();
        
//        lbPelaaja.setText("Pelaaja");
//        lbKortit.setText("Kortit: ");
//        lbNaytaKortit.setText("Kortit tähän");
//        lbPisteet.setText("Pisteet: ");
//        lbNaytaPisteet.setText("Pisteet tähän");
//        bnOtaKortti.setText("Ota Kortti");
//        bnPelaaKasi.setText("Pelaa Käsi");
        mPeli.setText("Peli");
        miPelaa.setText("Pelaa");
        mPeli.add(miPelaa);
        miLopeta.setText("Lopeta");
        mPeli.add(miLopeta);
        mbMain.add(mPeli);
        setJMenuBar(mbMain);
        
//        GroupLayout peliPaneeliLayout = new GroupLayout(pelaajanPaneeli);
//        pelaajanPaneeli.setLayout(peliPaneeliLayout);
//        peliPaneeliLayout.setHorizontalGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addGroup(peliPaneeliLayout.createSequentialGroup()
//                .addContainerGap()
//                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                    .addGroup(peliPaneeliLayout.createSequentialGroup()
//                        .addComponent(lbPisteet)
//                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(lbNaytaPisteet))
//                    .addGroup(peliPaneeliLayout.createSequentialGroup()
//                        .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
//                            .addComponent(lbKortit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                            .addComponent(lbPelaaja, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
//                        .addComponent(lbNaytaKortit))
//                    .addComponent(bnOtaKortti)
//                    .addComponent(bnPelaaKasi))
//                .addContainerGap(267, Short.MAX_VALUE))
//        );
        
//        peliPaneeliLayout.setVerticalGroup(
//            peliPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//            .addGroup(peliPaneeliLayout.createSequentialGroup()
//                .addContainerGap()
//                .addComponent(lbPelaaja)
//                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
//                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                    .addComponent(lbKortit)
//                    .addComponent(lbNaytaKortit))
//                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                    .addComponent(lbPisteet)
//                    .addComponent(lbNaytaPisteet))
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(bnOtaKortti)
//                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                .addComponent(bnPelaaKasi)
//                .addContainerGap(129, Short.MAX_VALUE))
//        );
        layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
//        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addComponent(pelaajanPaneeli, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
//        
//        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addComponent(pelaajanPaneeli, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//        );
        
        peliIkkuna.setSize(400, 400);
//        pack();
    }
    
    public void lisaaPaneeli(GroupLayout peliPaneeliLayout,JPanel paneeli, JLabel lbPisteet, JLabel lbNaytaPisteet, JLabel lbKortit,
            JLabel lbPelaaja, JLabel lbNaytaKortit, JButton bnOtaKortti, JButton bnPelaaKasi) {

//        paneeli.setLayout(peliPaneeliLayout);
//        peliPaneeliLayout.setHorizontalGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addGroup(peliPaneeliLayout.createSequentialGroup()
//                .addContainerGap()
//                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                    .addGroup(peliPaneeliLayout.createSequentialGroup()
//                        .addComponent(lbPisteet)
//                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                        .addComponent(lbNaytaPisteet))
//                    .addGroup(peliPaneeliLayout.createSequentialGroup()
//                        .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
//                            .addComponent(lbKortit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                            .addComponent(lbPelaaja, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
//                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
//                        .addComponent(lbNaytaKortit))
//                    .addComponent(bnOtaKortti)
//                    .addComponent(bnPelaaKasi))
//                .addContainerGap(267, Short.MAX_VALUE))
//        );
//        
//        peliPaneeliLayout.setVerticalGroup(
//                peliPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
//                        .addGroup(peliPaneeliLayout.createSequentialGroup()
//                                .addContainerGap()
//                                .addComponent(lbPelaaja)
//                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
//                                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                        .addComponent(lbKortit)
//                                        .addComponent(lbNaytaKortit))
//                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
//                                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
//                                        .addComponent(lbPisteet)
//                                        .addComponent(lbNaytaPisteet))
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(bnOtaKortti)
//                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
//                                .addComponent(bnPelaaKasi)
//                                .addContainerGap(129, Short.MAX_VALUE))
//        );
        
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(paneeli, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(paneeli, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }
    
//    public void asetaPisteet(int pisteet) {
//        this.lbNaytaPisteet.setText(""+pisteet);
//    }
//    
//    public void asetaKortit(String kasi) {
//        this.lbNaytaKortit.setText(kasi);
//    }
}
