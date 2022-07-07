package application.sockets;

import domain.entities.item.luminaria.Luminaria;

import java.io.Serializable;

public class Command implements Serializable {
    private Luminaria luminaria;
    private int tempo;

    public Command(Luminaria luminaria) {
        this.luminaria = luminaria;
    }

    public Command(Luminaria luminaria, int tempo) {
        this.luminaria = luminaria;
        this.tempo = tempo;
    }

    public Luminaria getLuminaria() {
        return luminaria;
    }

    public void setLuminaria(Luminaria luminaria) {
        this.luminaria = luminaria;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
