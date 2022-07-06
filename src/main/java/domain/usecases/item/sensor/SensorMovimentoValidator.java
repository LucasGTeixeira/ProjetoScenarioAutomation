package domain.usecases.item.sensor;

import domain.entities.item.sensorMovimento.SensorMovimento;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class SensorMovimentoValidator extends Validator<SensorMovimento> {
    @Override
    public Notification validate(SensorMovimento sensorMovimento) {
        Notification notification = new Notification();

        if(sensorMovimento == null){
            notification.addError("Objeto Sensor de Movimento está nulo");
            return notification;
        }
        if(nullOrEmpty(sensorMovimento.getNome()))
            notification.addError("Nome do Sensor de Movimento não pode estar nulo ou vazio");
        return notification;
    }

    static void validarSensor(SensorMovimento sensorMovimento) {
        Validator<SensorMovimento> validator = new SensorMovimentoValidator();
        Notification notification = validator.validate(sensorMovimento);
        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());
    }
}
