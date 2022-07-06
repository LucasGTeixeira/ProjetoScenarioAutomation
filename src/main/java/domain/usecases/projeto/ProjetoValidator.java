package domain.usecases.projeto;

import domain.entities.projeto.Projeto;
import domain.usecases.utils.Notification;
import domain.usecases.utils.Validator;

public class ProjetoValidator extends Validator<Projeto> {
    @Override
    public Notification validate(Projeto projeto) {
        Notification notification = new Notification();

        if(projeto == null){
            notification.addError("Objeto Projeto está nulo");
            return notification;
        }
        if(nullOrEmpty(projeto.getNome()))
            notification.addError("Nome do projeto não pode estar nulo ou vazio");
        return notification;
    }

    static void validarProjeto(Projeto projeto) {
        Validator<Projeto> validator = new ProjetoValidator();
        Notification notification = validator.validate(projeto);
        if(notification.hasErros())
            throw new IllegalArgumentException(notification.errorMessage());
    }
}
