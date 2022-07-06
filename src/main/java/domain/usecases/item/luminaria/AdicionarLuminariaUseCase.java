package domain.usecases.item.luminaria;

import domain.entities.item.luminaria.Luminaria;
import domain.usecases.utils.exceptions.EntityAlreadyExistsException;

public class AdicionarLuminariaUseCase {
    private final LuminariaDAO luminariaDAO;
    private final ListarLuminariasUseCase listarLuminariasUseCase;

    public AdicionarLuminariaUseCase(LuminariaDAO luminariaDAO, ListarLuminariasUseCase listarLuminariasUseCase) {
        this.luminariaDAO = luminariaDAO;
        this.listarLuminariasUseCase = listarLuminariasUseCase;
    }

    public Integer insert(Luminaria luminaria){
        LuminariaValidator.validarLuminaria(luminaria);

        String nomeLuminaria = luminaria.getNome();
        boolean nomeLuminariaAlreadyExists = listarLuminariasUseCase.findByNome(nomeLuminaria).isPresent();
        if(nomeLuminariaAlreadyExists)
            throw new EntityAlreadyExistsException("nome de luminaria já está cadastrado no sistema");

        return luminariaDAO.create(luminaria);
    }

}
