package domain.usecases.item.interruptor;

import domain.entities.item.interruptor.Interruptor;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class InterruptorValidator extends Validator<Interruptor> {
    @Override
    public Notification validate(Interruptor interruptor) {
        Notification notification = new Notification();

        if(interruptor == null){
            notification.addError("Objeto Interruptor está nulo");
            return notification;
        }
        if(nullOrEmpty(interruptor.getNome()))
            notification.addError("Nome do Interruptor não pode estar nulo ou vazio");
        return notification;
    }

    static void validarInterruptor(Interruptor interruptor) {
        Validator<Interruptor> validator = new InterruptorValidator();
        Notification notification = validator.validate(interruptor);
        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());
    }
}
