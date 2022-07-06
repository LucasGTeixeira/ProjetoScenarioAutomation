package domain.usecases.projeto;

import domain.entities.projeto.Projeto;
import domain.usecases.utils.exceptions.EntityNotFoundException;

public class EditarProjetoUseCase {
    private final ProjetoDAO projetoDAO;
    private final ListarProjetoUsecase listarProjetoUsecase;

    public EditarProjetoUseCase(ProjetoDAO projetoDAO, ListarProjetoUsecase listarProjetoUsecase) {
        this.projetoDAO = projetoDAO;
        this.listarProjetoUsecase = listarProjetoUsecase;
    }

    public boolean update(Projeto projeto){
        ProjetoValidator.validarProjeto(projeto);

        String nomeProjeto = projeto.getNome();
        boolean nomeProjetoNotFound = listarProjetoUsecase.findByNome(nomeProjeto).isEmpty();
        if(nomeProjetoNotFound)
            throw new EntityNotFoundException("Projeto não encontrado na aplicação, impossível editá-lo");

        return projetoDAO.update(projeto);
    }
}
