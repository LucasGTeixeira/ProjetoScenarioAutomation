package domain.usecases.item.luminaria;

import domain.entities.item.luminaria.Luminaria;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class LuminariaValidator extends Validator<Luminaria>{
    @Override
    public Notification validate(Luminaria luminaria) {
        Notification notification = new Notification();

        if(luminaria == null){
            notification.addError("Objeto luminaria está nulo");
            return notification;
        }
        if(nullOrEmpty(luminaria.getNome()))
            notification.addError("Nome da luminaria não pode estar nulo ou vazio");
        return notification;
    }

    static void validarLuminaria(Luminaria luminaria) {
        Validator<Luminaria> validator = new LuminariaValidator();
        Notification notification = validator.validate(luminaria);
        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());
    }

}
