package domain.usecases.item.luminaria;

public class ControlarLuminariaUseCase {
    private final LuminariaDAO luminariaDAO;
    private final ListarLuminariasUseCase listarLuminariasUseCase;

    public ControlarLuminariaUseCase(LuminariaDAO luminariaDAO, ListarLuminariasUseCase listarLuminariasUseCase) {
        this.luminariaDAO = luminariaDAO;
        this.listarLuminariasUseCase = listarLuminariasUseCase;
    }

}
