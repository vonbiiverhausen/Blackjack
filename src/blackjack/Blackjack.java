package blackjack;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Blackjack {
    
    public static void vertaa(PelaajanKasi pelaaja) {
        String viesti = "";
        
        if (pelaaja.onBlackjack()) {
            viesti += "Pelaajalla on blackjack!";
        } else if (pelaaja.selvitaSumma() < 22){
            viesti += "Pelaajalla on "+pelaaja.selvitaSumma()+" pistettä";
        } else {
            viesti += "Pelaaja sai yli 21 pistettä. Pelaaja hävisi";
        }
        
        javax.swing.JOptionPane.showMessageDialog(null,
        viesti);
        System.out.println(viesti);
    }
    
    public static void main(String[] args) {
        Korttipakka pakka = new Korttipakka(1);
        PelaajanKasi pelaaja = new PelaajanKasi();
        PelaajanKasi talo = new PelaajanKasi();
        BlackjackIkkuna peli = new BlackjackIkkuna();
        
        peli.lisaaPaneeli(pelaaja.peliPaneeliLayout, pelaaja.pelaajanPaneeli, pelaaja.lbPisteet, pelaaja.lbNaytaPisteet, pelaaja.lbKortit,
                pelaaja.lbPelaaja, pelaaja.lbNaytaKortit, pelaaja.bnOtaKortti, pelaaja.bnPelaaKasi);
        peli.add(pelaaja.pelaajanPaneeli);
        
        peli.lisaaPaneeli(talo.peliPaneeliLayout, talo.pelaajanPaneeli, talo.lbPisteet, talo.lbNaytaPisteet, talo.lbKortit,
                talo.lbPelaaja, talo.lbNaytaKortit, talo.bnOtaKortti, talo.bnPelaaKasi);
        peli.add(talo.pelaajanPaneeli);
        
        peli.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        peli.setVisible(true);
        peli.pack();
        pelaaja.bnOtaKortti.setEnabled(false);
        pelaaja.bnPelaaKasi.setEnabled(false);

        // Näytetään tilanne
        pelaaja.asetaKortit(pelaaja.naytaKasi());
        pelaaja.asetaPisteet(pelaaja.selvitaSumma());
        
        // ActionListenerit
        // 'Ota Kortti' painike
        pelaaja.bnOtaKortti.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                pelaaja.otaKortti(pakka.jaaKortti());
                pelaaja.asetaKortit(pelaaja.naytaKasi());
                pelaaja.asetaPisteet(pelaaja.selvitaSumma());
                if (pelaaja.selvitaSumma() > 21) {
                    // häviä
                    pelaaja.bnOtaKortti.setEnabled(false);
                    pelaaja.bnPelaaKasi.setEnabled(false);
                    vertaa(pelaaja);
                    System.out.println("Yli 21 pistettä! Hävisit!");
                }
            }
        });
        
        // 'Pelaa Käsi' Painike
        pelaaja.bnPelaaKasi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Painike 'Pelaa Käsi'
                // Vuoro siirtyy talolle
                // Disabloi painikkeet
                pelaaja.bnOtaKortti.setEnabled(false);
                pelaaja.bnPelaaKasi.setEnabled(false);
                
                vertaa(pelaaja);
            }
        });
        
        // MenuItem 'Pelaa'
        peli.miPelaa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                // Nollataan tiedot, käytetään uuden pelin alustuksessa
                pelaaja.nollaaKasi();
                pakka.nollaaPakka();

                // Aluksi saadaan kaksi korttia
                pelaaja.otaKortti(pakka.jaaKortti());
                pelaaja.otaKortti(pakka.jaaKortti());

                // Päivitetään käsi- ja pistekentät
                pelaaja.asetaKortit(pelaaja.naytaKasi());
                pelaaja.asetaPisteet(pelaaja.selvitaSumma());
                
                // Jos saadaan heti blackjack, mennään vertaukseen
                // Muutoin enabloidaan näppäimet, jotta voidaan pelata
                if (pelaaja.onBlackjack()) {
                    vertaa(pelaaja);
                } else {
                    pelaaja.bnOtaKortti.setEnabled(true);
                    pelaaja.bnPelaaKasi.setEnabled(true);
                }

                
            }
        });
        
        // 'Lopeta' MenuItem
        peli.miLopeta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.exit(0);
            }
        });
    }
}
