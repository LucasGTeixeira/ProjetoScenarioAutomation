package domain.usecases.item.luminaria;

import domain.entities.item.luminaria.Luminaria;
import domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ListarLuminariasUseCase {
    private final LuminariaDAO luminariaDAO;

    public ListarLuminariasUseCase(LuminariaDAO luminariaDAO) {
        this.luminariaDAO = luminariaDAO;
    }

    public List<Luminaria> findAll(){return luminariaDAO.findAll();}

    public Optional<Luminaria> findOne(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");
        return luminariaDAO.findOne(id);
    }

    public Optional<Luminaria> findByNome(String nomeLuminaria){
        if(Validator.nullOrEmpty(nomeLuminaria))
            throw new IllegalArgumentException("nome de luminaria nulo ou vazio");
        return luminariaDAO.findByNome(nomeLuminaria);
    }
}
