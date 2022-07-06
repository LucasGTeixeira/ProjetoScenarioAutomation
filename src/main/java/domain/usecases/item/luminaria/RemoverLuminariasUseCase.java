package domain.usecases.item.luminaria;

import domain.entities.item.luminaria.Luminaria;
import domain.usecases.ambiente.ListarAmbientesUseCase;
import domain.usecases.utils.exceptions.EntityNotFoundException;
import domain.usecases.utils.exceptions.ItemRelatedToAmbienteException;

public class RemoverLuminariasUseCase {
    private final LuminariaDAO luminariaDAO;
    private final ListarLuminariasUseCase listarLuminariasUseCase;
    private final ListarAmbientesUseCase listarAmbientesUseCase;

    public RemoverLuminariasUseCase(LuminariaDAO luminariaDAO, ListarLuminariasUseCase listarLuminariasUseCase, ListarAmbientesUseCase listarAmbientesUseCase) {
        this.luminariaDAO = luminariaDAO;
        this.listarLuminariasUseCase = listarLuminariasUseCase;
        this.listarAmbientesUseCase = listarAmbientesUseCase;
    }

    public boolean delete(Luminaria luminaria){
        LuminariaValidator.validarLuminaria(luminaria);

        String nomeLuminaria = luminaria.getNome();
        boolean nomeLuminariaNotFound = listarLuminariasUseCase.findByNome(nomeLuminaria).isEmpty();
        if(nomeLuminariaNotFound)
            throw new EntityNotFoundException("não há nenhuma luminaria com este nome no sistema");

        ProcurarRelacionamento(luminaria);

        return luminariaDAO.delete(luminaria);
    }

    public boolean delete(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");

        boolean idLuminariaNotFound = listarLuminariasUseCase.findOne(id).isEmpty();
        if(idLuminariaNotFound)
            throw new EntityNotFoundException("id não corresponde a nanhuma luminaria do sistema");

        Luminaria luminaria = listarLuminariasUseCase.findOne(id).get();

        ProcurarRelacionamento(luminaria);

        return luminariaDAO.deleteByKey(id);
    }

    public void ProcurarRelacionamento(Luminaria luminaria){
        boolean isLuminariaRelatedToAmbiente = listarAmbientesUseCase.findItemByNome(luminaria.getNome()).isPresent();
        if(isLuminariaRelatedToAmbiente)
            throw new ItemRelatedToAmbienteException("Impossível deletar esta luminaria enquanto há ambientes usando ela");
    }
}
