package domain.usecases.projeto;

import domain.entities.projeto.Projeto;
import domain.usecases.utils.exceptions.EntityNotFoundException;

public class EditarProjetoUseCase {
    private final ProjetoDAO projetoDAO;
    private final ListarProjetosUseCase listarProjetosUsecase;

    public EditarProjetoUseCase(ProjetoDAO projetoDAO, ListarProjetosUseCase listarProjetosUsecase) {
        this.projetoDAO = projetoDAO;
        this.listarProjetosUsecase = listarProjetosUsecase;
    }

    public boolean update(Projeto projeto){
        ProjetoValidator.validarProjeto(projeto);

        String nomeProjeto = projeto.getNome();
        boolean nomeProjetoNotFound = listarProjetosUsecase.findByNome(nomeProjeto).isEmpty();
        if(nomeProjetoNotFound)
            throw new EntityNotFoundException("Projeto não encontrado na aplicação, impossível editá-lo");

        return projetoDAO.update(projeto);
    }
}
