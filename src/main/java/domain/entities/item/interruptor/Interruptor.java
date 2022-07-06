package domain.entities.item.interruptor;

import domain.entities.item.ItemAmbiente;
import domain.entities.item.luminaria.Luminaria;

import java.util.List;

public class Interruptor extends ItemAmbiente {
    private List<Luminaria> modulos;

    public Interruptor(String nome) {
        super(nome);
    }

    public List<Luminaria> getModulos() {
        return modulos;
    }

    public void setModulos(List<Luminaria> modulos) {
        this.modulos = modulos;
    }

    @Override
    public String toString() {
        return "Modulos Interruptor: " + modulos;
    }
}
