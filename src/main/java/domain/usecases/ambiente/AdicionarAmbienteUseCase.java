package domain.usecases.ambiente;

import domain.entities.ambiente.Ambiente;
import domain.usecases.utils.exceptions.EntityAlreadyExistsException;

public class AdicionarAmbienteUseCase {
    private final AmbienteDAO ambienteDAO;
    private final ListarAmbientesUseCase listarAmbientesUseCase;

    public AdicionarAmbienteUseCase(AmbienteDAO ambienteDAO, ListarAmbientesUseCase listarAmbientesUseCase) {
        this.ambienteDAO = ambienteDAO;
        this.listarAmbientesUseCase = listarAmbientesUseCase;
    }

    public Integer insert(Ambiente ambiente){
        AmbienteValidator.validarAmbiente(ambiente);

        String nomeAmbiente = ambiente.getNome();
        boolean nomeAmbienteAlreadyExists = listarAmbientesUseCase.findByNome(nomeAmbiente).isPresent();
        if(nomeAmbienteAlreadyExists)
            throw new EntityAlreadyExistsException("Nome do ambiente já existe no sistema");

        return ambienteDAO.create(ambiente);
    }
}
