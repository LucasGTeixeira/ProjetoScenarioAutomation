package domain.usecases.ambiente;

import domain.entities.ambiente.Ambiente;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class AmbienteValidator extends Validator<Ambiente> {
    @Override
    public Notification validate(Ambiente ambiente) {
        Notification notification = new Notification();

        if(ambiente == null){
            notification.addError("Objeto Ambiente está nulo");
            return notification;
        }
        if(nullOrEmpty(ambiente.getNome()))
            notification.addError("Nome do ambiente não pode estar nulo ou vazio");
        return notification;
    }

    static void validarAmbiente(Ambiente ambiente) {
        Validator<Ambiente> validator = new AmbienteValidator();
        Notification notification = validator.validate(ambiente);
        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());
    }
}
