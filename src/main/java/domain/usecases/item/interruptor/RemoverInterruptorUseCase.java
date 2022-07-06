package domain.usecases.item.interruptor;

import domain.entities.item.interruptor.Interruptor;
import domain.usecases.ambiente.ListarAmbientesUseCase;
import domain.usecases.utils.exceptions.EntityNotFoundException;
import domain.usecases.utils.exceptions.ItemRelatedToAmbienteException;

public class RemoverInterruptorUseCase {
    private final InterruptorDAO interruptorDAO;
    private final ListarInterruptorUseCase listarInterruptorUseCase;
    private final ListarAmbientesUseCase listarAmbientesUseCase;

    public RemoverInterruptorUseCase(InterruptorDAO interruptorDAO, ListarInterruptorUseCase listarInterruptorUseCase, ListarAmbientesUseCase listarAmbientesUseCase) {
        this.interruptorDAO = interruptorDAO;
        this.listarInterruptorUseCase = listarInterruptorUseCase;
        this.listarAmbientesUseCase = listarAmbientesUseCase;
    }

    public boolean delete(Interruptor interruptor){
        InterruptorValidator.validarInterruptor(interruptor);

        String nomeInterruptor = interruptor.getNome();
        boolean nomeInterruptorNotFound = listarInterruptorUseCase.findByNome(nomeInterruptor).isEmpty();
        if (nomeInterruptorNotFound)
            throw new EntityNotFoundException("nome de interruptor não corresponde a nenhum outro no sistema");

        ProcurarRelacionamentos(interruptor);
        return interruptorDAO.delete(interruptor);
    }

    public boolean delete(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");

        boolean idInterruptorNotFound = listarInterruptorUseCase.findOne(id).isEmpty();
        if(idInterruptorNotFound)
            throw new EntityNotFoundException("não há nenhum interruptor com este id no sistema");

        Interruptor interruptor = listarInterruptorUseCase.findOne(id).get();
        ProcurarRelacionamentos(interruptor);

        return interruptorDAO.deleteByKey(id);
    }

    private void ProcurarRelacionamentos(Interruptor interruptor) {
        boolean isInterruptorRelatedToAmbiente = listarAmbientesUseCase.findItemByNome(interruptor.getNome()).isPresent();
        if (isInterruptorRelatedToAmbiente)
            throw new ItemRelatedToAmbienteException("Impossível deletar este interruptor enquanto há ambientes usando ele");
    }
}
