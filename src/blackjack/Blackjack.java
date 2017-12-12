package blackjack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Blackjack {
    
    public static void main(String[] args) {
        Korttipakka pakka = new Korttipakka(1);
        PelaajanKasi pelaaja = new PelaajanKasi();
        BlackjackIkkuna peli = new BlackjackIkkuna();
        peli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        peli.setVisible(true);
        peli.bnOtaKortti.setEnabled(false);
        peli.bnPelaaKasi.setEnabled(false);

        // Näytetään tilanne
        peli.asetaKortit(pelaaja.naytaKasi());
        peli.asetaPisteet(pelaaja.selvitaSumma());
        
        // ActionListenerit
        
        // 'Ota Kortti' painike
        peli.bnOtaKortti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                pelaaja.otaKortti(pakka.jaaKortti());
                peli.asetaKortit(pelaaja.naytaKasi());
                peli.asetaPisteet(pelaaja.selvitaSumma());
                if (pelaaja.selvitaSumma() > 21) {
                    // häviä
                    
                    System.out.println("Yli 21 pistettä! Hävisit!");
                }
            }
        });
        
        // 'Pelaa Käsi' Painike
        peli.bnPelaaKasi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Painike 'Pelaa Käsi'
                // Vuoro siirtyy talolle
                // Disabloi painikkeet
            }
        });
        
        // MenuItem 'Pelaa'
        peli.miPelaa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // Nollataan tiedot, käytetään uuden pelin alustuksessa
                pelaaja.nollaaKasi();
                pakka.nollaaPakka();

                // Aluksi saadaan kaksi korttia
                pelaaja.otaKortti(pakka.jaaKortti());
                pelaaja.otaKortti(pakka.jaaKortti());

                // Päivitetään käsi- ja pistekentät
                peli.asetaKortit(pelaaja.naytaKasi());
                peli.asetaPisteet(pelaaja.selvitaSumma());
                
                if (pelaaja.onBlackjack()) {
                    System.out.println("Blackjack!");
                }

                // Enabloidaan näppäimet, jotta voidaan pelata
                peli.bnOtaKortti.setEnabled(true);
                peli.bnPelaaKasi.setEnabled(true);
            }
        });
        
        // 'Lopeta' MenuItem
        peli.miLopeta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
    }
}
