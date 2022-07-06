package domain.usecases.projeto;

import domain.entities.projeto.Projeto;
import domain.usecases.utils.exceptions.EntityAlreadyExistsException;

public class AdicionarProjetoUseCase {
    private final ProjetoDAO projetoDAO;
    private final ListarProjetosUseCase listarProjetosUsecase;

    public AdicionarProjetoUseCase(ProjetoDAO projetoDAO, ListarProjetosUseCase listarProjetosUsecase) {
        this.projetoDAO = projetoDAO;
        this.listarProjetosUsecase = listarProjetosUsecase;
    }

    public Integer insert(Projeto projeto){
        ProjetoValidator.validarProjeto(projeto);

        String nomeProjeto = projeto.getNome();
        boolean nomeProjetoAlreadyExists = listarProjetosUsecase.findByNome(nomeProjeto).isPresent();
        if(nomeProjetoAlreadyExists)
            throw new EntityAlreadyExistsException("Nome de projeto já existe no sistema. Escolha outro");

        return projetoDAO.create(projeto);
    }


}
