package domain.entities.item.interruptor;

import domain.entities.item.ItemAmbiente;
import domain.entities.item.luminaria.Luminaria;

import java.util.List;

public class Interruptor extends ItemAmbiente {
    private List<Luminaria> modulos;

    public Interruptor(String nome) {
        super(nome);
    }

    public Interruptor(Integer id, String nome, List<Luminaria> modulos) {
        super(id, nome);
        this.modulos = modulos;
    }

    public Interruptor(String nome, List<Luminaria> modulos) {
        super(nome);
        this.modulos = modulos;
    }

    public List<Luminaria> getModulos() {
        return modulos;
    }

    public void setModulos(List<Luminaria> modulos) {
        this.modulos = modulos;
    }

    @Override
    public String toString() {
        return "Interruptor{" +
                "modulos=" + modulos +
                '}';
    }
}
