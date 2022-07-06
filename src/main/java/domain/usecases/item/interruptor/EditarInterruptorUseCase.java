package domain.usecases.item.interruptor;

import domain.entities.item.interruptor.Interruptor;
import domain.usecases.utils.exceptions.EntityNotFoundException;
import domain.usecases.utils.exceptions.LuminariasLimitReachedException;

public class EditarInterruptorUseCase {
    private final InterruptorDAO interruptorDAO;
    private final ListarInterruptorUseCase listarInterruptorUseCase;

    public EditarInterruptorUseCase(InterruptorDAO interruptorDAO, ListarInterruptorUseCase listarInterruptorUseCase) {
        this.interruptorDAO = interruptorDAO;
        this.listarInterruptorUseCase = listarInterruptorUseCase;
    }

    public boolean update(Interruptor interruptor){
        InterruptorValidator.validarInterruptor(interruptor);

        String nomeInterruptor = interruptor.getNome();
        boolean nomeInterruptorNotFound = listarInterruptorUseCase.findByNome(nomeInterruptor).isEmpty();
        if(nomeInterruptorNotFound)
            throw new EntityNotFoundException("nome de interruptor não corresponde a nenhum registro no sistema");

        if(LuminariasLimitExceeded(interruptor))
            throw new LuminariasLimitReachedException("não é possível associar mais luminárias a esse interruptor");

        return interruptorDAO.update(interruptor);
    }

    private boolean LuminariasLimitExceeded(Interruptor interruptor){
        int qtnLuminarias = interruptor.getModulos().size();
        return qtnLuminarias > 4;
    }
}
