package application.main;

import application.repository.mocked.*;
import domain.usecases.ambiente.*;
import domain.usecases.item.interruptor.*;
import domain.usecases.item.luminaria.*;
import domain.usecases.item.sensor.*;
import domain.usecases.projeto.*;

public class Main {
    private static ListarLuminariasUseCase listarLuminariasUseCase;
    private static AdicionarLuminariaUseCase adicionarLuminariaUseCase;
    private static EditarLuminariaUseCase editarLuminariaUseCase;
    private static RemoverLuminariasUseCase removerLuminariasUseCase;

    private static ListarAmbientesUseCase listarAmbientesUseCase;
    private static AdicionarAmbienteUseCase adicionarAmbienteUseCase;
    private static EditarAmbienteUseCase editarAmbienteUseCase;
    private static RemoverAmbienteUseCase removerAmbienteUseCase;

    private static ListarProjetosUseCase listarProjetosUseCase;
    private static AdicionarProjetoUseCase adicionarProjetoUseCase;
    private static EditarProjetoUseCase editarProjetoUseCase;
    private static RemoverProjetoUseCase removerProjetoUseCase;

    private static ListarSensorMovimentoUseCase listarSensorMovimentoUseCase;
    private static AdicionarSensorMovimentoUseCase adicionarSensorMovimentoUseCase;
    private static EditarSensorMovimentoUseCase editarSensorMovimentoUseCase;
    private static RemoverSensorMovimentoUseCase removerSensorMovimentoUseCase;

    private static ListarInterruptorUseCase listarInterruptorUseCase;
    private static AdicionarInterruptorUseCase adicionarInterruptorUseCase;
    private static EditarInterruptorUseCase editarInterruptorUseCase;
    private static RemoverInterruptorUseCase removerInterruptorUseCase;

    public static void main(String[] args) {
        injecaoDeDependencia();

        //TODO instanciar testes
    }

    private static void injecaoDeDependencia() {
        AmbienteDAO ambienteDAO = new AmbienteMockDAO();
        ProjetoDAO projetoDAO = new ProjetoMockDAO();
        InterruptorDAO interruptorDAO = new InterruptorMockDAO();
        LuminariaDAO luminariaDAO = new LuminariaMockDAO();
        SensorMovimentoDAO sensorMovimentoDAO = new SensorMovimentoMockDAO();

        listarInterruptorUseCase = new ListarInterruptorUseCase(interruptorDAO);
        listarAmbientesUseCase = new ListarAmbientesUseCase(ambienteDAO);
        listarLuminariasUseCase = new ListarLuminariasUseCase(luminariaDAO);
        listarProjetosUseCase = new ListarProjetosUseCase(projetoDAO);
        listarSensorMovimentoUseCase = new ListarSensorMovimentoUseCase(sensorMovimentoDAO);

        adicionarAmbienteUseCase = new AdicionarAmbienteUseCase(ambienteDAO, listarAmbientesUseCase);
        editarAmbienteUseCase = new EditarAmbienteUseCase(ambienteDAO, listarAmbientesUseCase);
        removerAmbienteUseCase = new RemoverAmbienteUseCase(ambienteDAO, listarAmbientesUseCase, listarProjetosUseCase);

        adicionarProjetoUseCase = new AdicionarProjetoUseCase(projetoDAO, listarProjetosUseCase);
        editarProjetoUseCase = new EditarProjetoUseCase(projetoDAO, listarProjetosUseCase);
        removerProjetoUseCase = new RemoverProjetoUseCase(projetoDAO, listarProjetosUseCase);

        adicionarInterruptorUseCase = new AdicionarInterruptorUseCase(interruptorDAO, listarInterruptorUseCase);
        editarInterruptorUseCase = new EditarInterruptorUseCase(interruptorDAO, listarInterruptorUseCase);
        removerInterruptorUseCase = new RemoverInterruptorUseCase(interruptorDAO, listarInterruptorUseCase, listarAmbientesUseCase);

        adicionarLuminariaUseCase = new AdicionarLuminariaUseCase(luminariaDAO, listarLuminariasUseCase);
        editarLuminariaUseCase = new EditarLuminariaUseCase(luminariaDAO, listarLuminariasUseCase);
        removerLuminariasUseCase = new RemoverLuminariasUseCase(luminariaDAO, listarLuminariasUseCase, listarAmbientesUseCase);

        adicionarSensorMovimentoUseCase = new AdicionarSensorMovimentoUseCase(sensorMovimentoDAO, listarSensorMovimentoUseCase);
        editarSensorMovimentoUseCase = new EditarSensorMovimentoUseCase(sensorMovimentoDAO, listarSensorMovimentoUseCase);
        removerSensorMovimentoUseCase = new RemoverSensorMovimentoUseCase(sensorMovimentoDAO, listarSensorMovimentoUseCase, listarAmbientesUseCase);
    }


}
