package domain.usecases.item.interruptor;

import domain.entities.item.interruptor.Interruptor;
import domain.usecases.utils.exceptions.EntityAlreadyExistsException;

public class AdicionarInterruptorUseCase {
    private final InterruptorDAO interruptorDAO;
    private final ListarInterruptorUseCase listarInterruptorUseCase;

    public AdicionarInterruptorUseCase(InterruptorDAO interruptorDAO, ListarInterruptorUseCase listarInterruptorUseCase) {
        this.interruptorDAO = interruptorDAO;
        this.listarInterruptorUseCase = listarInterruptorUseCase;
    }

    public Integer insert(Interruptor interruptor){
        InterruptorValidator.validarInterruptor(interruptor);

        String nomeInterruptor = interruptor.getNome();
        boolean nomeInterruptorAlreadyExists = listarInterruptorUseCase.findByNome(nomeInterruptor).isPresent();
        if(nomeInterruptorAlreadyExists)
            throw new EntityAlreadyExistsException("nome de interruptor já cadastrado no sistema");

        return interruptorDAO.create(interruptor);
    }
}
