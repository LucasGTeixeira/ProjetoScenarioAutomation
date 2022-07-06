package domain.usecases.projeto;

import domain.entities.projeto.Projeto;
import domain.usecases.utils.exceptions.EntityNotFoundException;

public class RemoverProjetoUseCase {
    private final ProjetoDAO projetoDAO;
    private final ListarProjetosUseCase listarProjetosUsecase;

    public RemoverProjetoUseCase(ProjetoDAO projetoDAO, ListarProjetosUseCase listarProjetosUsecase) {
        this.projetoDAO = projetoDAO;
        this.listarProjetosUsecase = listarProjetosUsecase;
    }

    public boolean delete(Projeto projeto){
        ProjetoValidator.validarProjeto(projeto);

        String nomeProjeto = projeto.getNome();
        boolean nomeProjetoNotFound = listarProjetosUsecase.findByNome(nomeProjeto).isEmpty();
        if(nomeProjetoNotFound)
            throw new EntityNotFoundException("Nome do projeto não foi encontrado no sistema");

        return projetoDAO.delete(projeto);
    }

    public boolean delete(Integer id){
        if (id == null)
            throw new IllegalArgumentException("id não pode ser nulo");

        boolean idProjetoNotFound = listarProjetosUsecase.findOne(id).isEmpty();
        if(idProjetoNotFound)
            throw new EntityNotFoundException("id do projeto não foi encontrado no sistema");

        return projetoDAO.deleteByKey(id);
    }




}
