package blackjack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Blackjack {
    
    private static void bnOtaKorttiActionPerformed(ActionEvent evt, PelaajanKasi pelaaja, BlackjackIkkuna peli, Korttipakka pakka) {                                            
        pelaaja.otaKortti(pakka.jaaKortti());
        peli.asetaKortit(pelaaja.naytaKasi());
        peli.asetaPisteet(pelaaja.selvitaSumma());
    }
    
    private static void bnPelaaKasiActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    } 
    
    public static void main(String[] args) {
        Korttipakka pakka = new Korttipakka(1);
        PelaajanKasi pelaaja = new PelaajanKasi();
        BlackjackIkkuna peli = new BlackjackIkkuna();
        peli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        peli.setVisible(true);
        
        // Pelaaja saa alussa kaksi korttia
        pelaaja.otaKortti(pakka.jaaKortti());
        pelaaja.otaKortti(pakka.jaaKortti());
        
        // Näytetään tilanne
        peli.asetaKortit(pelaaja.naytaKasi());
        peli.asetaPisteet(pelaaja.selvitaSumma());
        
        peli.bnOtaKortti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                bnOtaKorttiActionPerformed(evt, pelaaja, peli, pakka);
            }
        });
        
        peli.bnPelaaKasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bnPelaaKasiActionPerformed(evt);
            }
        });
    }
}
