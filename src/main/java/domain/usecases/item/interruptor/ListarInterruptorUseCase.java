package domain.usecases.item.interruptor;

import domain.entities.item.interruptor.Interruptor;
import domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ListarInterruptorUseCase {
    private final InterruptorDAO interruptorDAO;

    public ListarInterruptorUseCase(InterruptorDAO interruptorDAO) {
        this.interruptorDAO = interruptorDAO;
    }

    public List<Interruptor> findAll(){
        return interruptorDAO.findAll();
    }

    public Optional<Interruptor> findOne(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");
        return interruptorDAO.findOne(id);
    }

    public Optional<Interruptor> findByNome(String nomeInterruptor) {
        if(Validator.nullOrEmpty(nomeInterruptor))
            throw new IllegalArgumentException("nome do Interruptor não pode ser nulo");
        return interruptorDAO.findByNome(nomeInterruptor);
    }
}
