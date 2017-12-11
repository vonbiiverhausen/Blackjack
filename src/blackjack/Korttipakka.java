package blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Korttipakka {
    final private ArrayList<Kortti> pakka;
    
    private void luoKortit(String maa) {
        for (int i = 2; i <= 10; i++) {
            this.pakka.add(new Kortti(i, maa+" "+i));
        }
        this.pakka.add(new Kortti(11, maa+"jätkä"));
        this.pakka.add(new Kortti(12, maa+"kuningatar"));
        this.pakka.add(new Kortti(13, maa+"kuningas"));
        this.pakka.add(new Kortti(1, maa+"ässä"));
    }
    
    public Korttipakka(int pakkojenLkm) {
        pakka = new ArrayList<>();
        for (int i = 0; i < pakkojenLkm; i++) {
            luoKortit("Hertta");
            luoKortit("Ruutu");
            luoKortit("Pata");
            luoKortit("Risti");
        }
    }
    
    public void sekoita() {
        Collections.shuffle(pakka);
    }
    
    public Kortti jaaKortti() {
        Kortti palautettavaKortti = this.pakka.get(0);
        this.pakka.remove(0);
        
        return palautettavaKortti;
    }
    
    // Testataan pakan täyttö korteilla
    public String testaaKortti(int indeksi) {
        return this.pakka.get(indeksi).toString();
    }
}
