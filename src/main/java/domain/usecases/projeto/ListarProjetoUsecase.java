package domain.usecases.projeto;

import domain.entities.projeto.Projeto;
import domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ListarProjetoUsecase {
    private final ProjetoDAO projetoDAO;

    public ListarProjetoUsecase(ProjetoDAO projetoDAO) {
        this.projetoDAO = projetoDAO;
    }

    public List<Projeto> findAll(){
        return projetoDAO.findAll();
    }

    public Optional<Projeto> findOne(Integer id){
        if(id == null)
            throw new IllegalArgumentException("Id de Projeto não pode ser nulo");
        return projetoDAO.findOne(id);
    }

    public Optional<Projeto> findByNome(String nome){
        if(Validator.nullOrEmpty(nome))
            throw new IllegalArgumentException("Nome não pode ser nulo ou vazio");
        return projetoDAO.findByNome(nome);
    }
}
