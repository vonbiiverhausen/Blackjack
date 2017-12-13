package blackjack;

import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class PelaajanKasi {
    final private ArrayList<Kortti> kortit;
    private boolean onBlackJack;
    private String nimi;
    
    JPanel pelaajanPaneeli;
    JLabel lbKortit;
    JLabel lbNaytaKortit;
    JLabel lbNaytaPisteet;
    JLabel lbPelaaja;
    JLabel lbPisteet;
    JButton bnOtaKortti;
    JButton bnPelaaKasi;
    GroupLayout peliPaneeliLayout; 
    public PelaajanKasi(String nimi) {
        this.kortit = new ArrayList<>();
        this.nimi = nimi;
        this.onBlackJack = false;
        
        pelaajanPaneeli = new JPanel();
        lbPelaaja = new JLabel();
        lbKortit = new JLabel();
        lbPisteet = new JLabel();
        lbNaytaPisteet = new JLabel();
        lbNaytaKortit = new JLabel();
        bnOtaKortti = new JButton();
        bnPelaaKasi = new JButton();
        peliPaneeliLayout = new GroupLayout(pelaajanPaneeli);
        
        this.asetaPaneeli();
    }
    
    public boolean onBlackjack() {
        return this.onBlackJack;
    }
    
    public void otaKortti(Kortti kortti) {
        this.kortit.add(kortti);
    }
    
    public int selvitaSumma() {
    // kortin arvo 2-10:        käden summaan lisätään kortin numeroa vastaava arvo(2 lisätään 2, 3 lisätään 3.)
    // kortin arvo 11-13:       käden summaan lisätään 10
    // kortin arvo 1 (ässä):    käden summaan lisätään 11
        int summa = 0;
        
        for (Kortti kortti : kortit) {
            if (kortti.getArvo() == 1) {
                summa += 11;
            } else if (kortti.getArvo() >= 11 && kortti.getArvo() <= 13) {
                summa += 10;
            } else {
                summa += kortti.getArvo();
            }
        }
        
        if (kortit.size() == 2 && summa == 22) {
            summa -= 10;
        }
        
        if (kortit.size()==2 && summa == 21) {
            this.onBlackJack = true;
        }
        
        return summa;
    }
    
    // palauttaa pelaajan käden kortin indeksissä 'indeksi'
    public String testaaKortti(int indeksi) {
        return this.kortit.get(indeksi).toString();
    }
    
    public String naytaKasi() {
        String kasi = "";
        for (Kortti kortti : kortit) {
            kasi += kortti.getMaa()+" ";
        }
        return kasi;
    }
    
    public void nollaaKasi() {
        this.kortit.clear();
        this.onBlackJack = false;
    }
    
    public void asetaPisteet(int pisteet) {
        this.lbNaytaPisteet.setText("" + pisteet);
    }

    public void asetaKortit(String kasi) {
        this.lbNaytaKortit.setText(kasi);
    }
    
    // Pelaajan paneeli ja sen komponentit
    public void asetaPaneeli() {
        
        lbPelaaja.setText(this.nimi);
        lbKortit.setText("Kortit: ");
        lbNaytaKortit.setText("Kortit tähän");
        lbPisteet.setText("Pisteet: ");
        lbNaytaPisteet.setText("Pisteet tähän");
        bnOtaKortti.setText("Ota Kortti");
        bnPelaaKasi.setText("Pelaa Käsi");

        GroupLayout peliPaneeliLayout = new GroupLayout(pelaajanPaneeli);
        pelaajanPaneeli.setLayout(peliPaneeliLayout);
        peliPaneeliLayout.setHorizontalGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(peliPaneeliLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(peliPaneeliLayout.createSequentialGroup()
                        .addComponent(lbPisteet)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbNaytaPisteet))
                    .addGroup(peliPaneeliLayout.createSequentialGroup()
                        .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbKortit, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbPelaaja, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbNaytaKortit))
                    .addComponent(bnOtaKortti)
                    .addComponent(bnPelaaKasi))
                .addContainerGap(267, Short.MAX_VALUE))
        );

        peliPaneeliLayout.setVerticalGroup(
                peliPaneeliLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(peliPaneeliLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbPelaaja)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbKortit)
                                        .addComponent(lbNaytaKortit))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(peliPaneeliLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(lbPisteet)
                                        .addComponent(lbNaytaPisteet))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bnOtaKortti)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bnPelaaKasi)
                                .addContainerGap(129, Short.MAX_VALUE))
        );
    }
}
