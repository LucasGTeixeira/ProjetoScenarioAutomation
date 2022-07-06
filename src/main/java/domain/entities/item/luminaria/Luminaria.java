package domain.entities.item.luminaria;

import domain.entities.item.ItemAmbiente;
import domain.entities.item.interruptor.Interruptor;
import domain.entities.item.sensorMovimento.SensorMovimento;

public class Luminaria extends ItemAmbiente {
    private boolean status;
    private Interruptor canal;
    private SensorMovimento sensor;

    public Luminaria(String nome) {
        super(nome);
    }

    public Luminaria(String nome, boolean status, Interruptor canal, SensorMovimento sensor) {
        super(nome);
        this.status = status;
        this.canal = canal;
        this.sensor = sensor;
    }

    public Interruptor getCanal() {
        return canal;
    }

    public void setCanal(Interruptor canal) {
        this.canal = canal;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public SensorMovimento getSensor() {
        return sensor;
    }

    public void setSensor(SensorMovimento sensor) {
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "Luminaria: " + super.toString();
    }
}
