package domain.usecases.ambiente;

import domain.entities.ambiente.Ambiente;
import domain.usecases.utils.exceptions.EntityNotFoundException;
import domain.usecases.utils.exceptions.ItemLimitReachedException;

public class EditarAmbienteUseCase {
    private final AmbienteDAO ambienteDAO;
    private final ListarAmbientesUseCase listarAmbientesUseCase;

    public EditarAmbienteUseCase(AmbienteDAO ambienteDAO, ListarAmbientesUseCase listarAmbientesUseCase) {
        this.ambienteDAO = ambienteDAO;
        this.listarAmbientesUseCase = listarAmbientesUseCase;
    }

    public boolean update(Ambiente ambiente){
        AmbienteValidator.validarAmbiente(ambiente);

        String nomeAmbiente = ambiente.getNome();
        boolean nomeAmbienteNotFound = listarAmbientesUseCase.findByNome(nomeAmbiente).isEmpty();
        if(nomeAmbienteNotFound)
            throw new EntityNotFoundException("nome de ambiente não corresponde a nenhum registro no sistema");

        if(isItemLimitReached(ambiente))
            throw new ItemLimitReachedException("limite de itens por ambiente alcaçado, operação cancelada");

        return ambienteDAO.update(ambiente);
    }

    private boolean isItemLimitReached(Ambiente ambiente){
        AmbienteValidator.validarAmbiente(ambiente);
        int qntItens = ambiente.getItens().size();
        return qntItens > 10;
    }

}
