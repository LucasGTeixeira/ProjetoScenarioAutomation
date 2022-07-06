package domain.usecases.ambiente;

import domain.entities.ambiente.Ambiente;
import domain.usecases.projeto.ListarProjetosUseCase;
import domain.usecases.utils.exceptions.AmbienteRelatedToProjetoException;
import domain.usecases.utils.exceptions.EntityNotFoundException;

public class RemoverAmbienteUseCase {
    private final AmbienteDAO ambienteDAO;
    private final ListarAmbientesUseCase listarAmbientesUseCase;
    private final ListarProjetosUseCase listarProjetosUseCase;

    public RemoverAmbienteUseCase(AmbienteDAO ambienteDAO, ListarAmbientesUseCase listarAmbientesUseCase, ListarProjetosUseCase listarProjetosUseCase) {
        this.ambienteDAO = ambienteDAO;
        this.listarAmbientesUseCase = listarAmbientesUseCase;
        this.listarProjetosUseCase = listarProjetosUseCase;
    }

    public boolean delete(Ambiente ambiente){
        AmbienteValidator.validarAmbiente(ambiente);

        String nomeAmbiente = ambiente.getNome();
        boolean nomeAmbienteNotFound = listarAmbientesUseCase.findByNome(nomeAmbiente).isEmpty();
        if (nomeAmbienteNotFound)
            throw new EntityNotFoundException("nome do ambiente não corresponde a nenhum outro no sistema");

        ProcuraRelacionamentos(ambiente);

        return ambienteDAO.delete(ambiente);
    }

    public boolean deleteByKey(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");

        boolean idAmbienteNotFound = listarAmbientesUseCase.findOne(id).isEmpty();
        if(idAmbienteNotFound)
            throw new EntityNotFoundException("id de ambiente não encontrado no sistema");

        Ambiente ambiente = listarAmbientesUseCase.findOne(id).get();
        ProcuraRelacionamentos(ambiente);

        return ambienteDAO.deleteByKey(id);
    }

    private void ProcuraRelacionamentos(Ambiente ambiente) {
        boolean isAmbienteRelatedToProjeto = listarProjetosUseCase.findAmbienteInProjetoByName(ambiente).isPresent();
        if(isAmbienteRelatedToProjeto)
            throw new AmbienteRelatedToProjetoException("impossível deletar ambiente, há projetos que usando ele");
    }
}
