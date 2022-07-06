package domain.usecases.projeto;

import domain.entities.projeto.Projeto;
import domain.usecases.utils.exceptions.EntityNotFoundException;

public class RemoverProjetoUsecase {
    private final ProjetoDAO projetoDAO;
    private final ListarProjetoUsecase listarProjetoUsecase;

    public RemoverProjetoUsecase(ProjetoDAO projetoDAO, ListarProjetoUsecase listarProjetoUsecase) {
        this.projetoDAO = projetoDAO;
        this.listarProjetoUsecase = listarProjetoUsecase;
    }

    public boolean delete(Projeto projeto){
        ProjetoValidator.validarProjeto(projeto);

        String nomeProjeto = projeto.getNome();
        boolean nomeProjetoNotFound = listarProjetoUsecase.findByNome(nomeProjeto).isEmpty();
        if(nomeProjetoNotFound)
            throw new EntityNotFoundException("Nome do projeto não foi encontrado no sistema");

        return projetoDAO.delete(projeto);
    }

    public boolean delete(Integer id){
        if (id == null)
            throw new IllegalArgumentException("id não pode ser nulo");

        boolean idProjetoNotFound = listarProjetoUsecase.findOne(id).isEmpty();
        if(idProjetoNotFound)
            throw new EntityNotFoundException("id do projeto não foi encontrado no sistema");

        return projetoDAO.deleteByKey(id);
    }




}
