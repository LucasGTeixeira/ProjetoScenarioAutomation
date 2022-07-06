package domain.usecases.item.luminaria;

import domain.entities.item.luminaria.Luminaria;
import domain.usecases.utils.exceptions.EntityNotFoundException;

public class EditarLuminariaUseCase {
    private final LuminariaDAO luminariaDAO;
    private final ListarLuminariasUseCase listarLuminariasUseCase;

    public EditarLuminariaUseCase(LuminariaDAO luminariaDAO, ListarLuminariasUseCase listarLuminariasUseCase) {
        this.luminariaDAO = luminariaDAO;
        this.listarLuminariasUseCase = listarLuminariasUseCase;
    }

    public boolean update(Luminaria luminaria){
        LuminariaValidator.validarLuminaria(luminaria);

        String nomeLuminaria = luminaria.getNome();
        boolean nomeLuminariaNotFound = listarLuminariasUseCase.findByNome(nomeLuminaria).isEmpty();
        if(nomeLuminariaNotFound)
            throw new EntityNotFoundException("não há nenhuma luminaria com este nome no sistema");

        return luminariaDAO.update(luminaria);
    }
}
