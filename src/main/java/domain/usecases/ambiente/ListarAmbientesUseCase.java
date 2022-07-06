package domain.usecases.ambiente;

import domain.entities.ambiente.Ambiente;
import domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ListarAmbientesUseCase {
    private final AmbienteDAO ambienteDAO;

    public ListarAmbientesUseCase(AmbienteDAO ambienteDAO) {
        this.ambienteDAO = ambienteDAO;
    }

    public List<Ambiente> findAll(){
        return ambienteDAO.findAll();
    }

    public Optional<Ambiente> findOne(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");
        return ambienteDAO.findOne(id);
    }

    public Optional<Ambiente> findByNome(String nome){
       if (Validator.nullOrEmpty(nome))
           throw new IllegalArgumentException("nome não pode ser nulo ou vazio");
       return ambienteDAO.findByNome(nome);
    }

}
