package domain.entities.item.sensorMovimento;

import domain.entities.item.ItemAmbiente;

public class SensorMovimento extends ItemAmbiente {

    public SensorMovimento(String nome) {
        super(nome);
    }

    @Override
    public String toString() {
        return "SensorMovimento: " + super.toString();
    }
}
