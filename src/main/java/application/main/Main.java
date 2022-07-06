package application.main;

import application.repository.mocked.*;
import domain.entities.ambiente.Ambiente;
import domain.entities.item.ItemAmbiente;
import domain.entities.item.interruptor.Interruptor;
import domain.entities.item.luminaria.Luminaria;
import domain.entities.item.sensorMovimento.SensorMovimento;
import domain.entities.projeto.Projeto;
import domain.usecases.ambiente.*;
import domain.usecases.item.interruptor.*;
import domain.usecases.item.luminaria.*;
import domain.usecases.item.sensor.*;
import domain.usecases.projeto.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        //Projeto
        Projeto p1 = new Projeto("Casa1");
        Projeto p2 = new Projeto("Casa2");
        Projeto p3 = new Projeto("Casa3");
        Projeto p4 = new Projeto("Casa4");

        //Ambiente
        Ambiente a1 = new Ambiente("Sala");
        Ambiente a2 = new Ambiente("Banheiro");
        Ambiente a3 = new Ambiente("Cozinha");
        Ambiente a4 = new Ambiente("Quarto de Hospedes");

        //Luminaria
        Luminaria l1 = new Luminaria("led1");
        Luminaria l2 = new Luminaria("led2");
        Luminaria l3 = new Luminaria("led3");
        Luminaria l4 = new Luminaria("led4");
        Luminaria l5 = new Luminaria("led5");
        Luminaria l6 = new Luminaria("led6");

        //Interruptor
        Interruptor i1 = new Interruptor("mod1");
        Interruptor i2 = new Interruptor("mod2");
        Interruptor i3 = new Interruptor("mod3");
        Interruptor i4 = new Interruptor("mod4");

        //SensorMovimento
        SensorMovimento s1 = new SensorMovimento("mov1");
        SensorMovimento s2 = new SensorMovimento("mov2");
        SensorMovimento s3 = new SensorMovimento("mov3");
        SensorMovimento s4 = new SensorMovimento("mov4");

        //Populando Hashmap
        Integer proj1 = adicionarProjetoUseCase.insert(p1);
        Integer proj2 = adicionarProjetoUseCase.insert(p2);
        Integer proj3 = adicionarProjetoUseCase.insert(p3);

        Integer amb1 = adicionarAmbienteUseCase.insert(a1);
        Integer amb2 = adicionarAmbienteUseCase.insert(a2);
        Integer amb3 = adicionarAmbienteUseCase.insert(a3);

        Integer mod1 = adicionarInterruptorUseCase.insert(i1);
        Integer mod2 = adicionarInterruptorUseCase.insert(i2);
        Integer mod3 = adicionarInterruptorUseCase.insert(i3);

        Integer lumi1 = adicionarLuminariaUseCase.insert(l1);
        Integer lumi2 = adicionarLuminariaUseCase.insert(l2);
        Integer lumi3 = adicionarLuminariaUseCase.insert(l3);
        Integer lumi4 = adicionarLuminariaUseCase.insert(l4);
        Integer lumi5 = adicionarLuminariaUseCase.insert(l5);
        Integer lumi6 = adicionarLuminariaUseCase.insert(l6);

        Integer sens1 = adicionarSensorMovimentoUseCase.insert(s1);
        Integer sens2 = adicionarSensorMovimentoUseCase.insert(s2);
        Integer sens3 = adicionarSensorMovimentoUseCase.insert(s3);

        Optional<Projeto> findProjetoPreUpdate = listarProjetosUseCase.findOne(proj1);
        findProjetoPreUpdate.ifPresent(System.out::println);
        List<Ambiente> ambientesProjeto = new ArrayList<>();
        ambientesProjeto.add(a1);
        ambientesProjeto.add(a2);
        ambientesProjeto.add(a3);
        Projeto projetoUpdate = new Projeto(1, "Casa1", ambientesProjeto);
        editarProjetoUseCase.update(projetoUpdate);
        Optional<Projeto> findProjetoPosUpdate = listarProjetosUseCase.findOne(proj1);
        findProjetoPosUpdate.ifPresent(System.out::println);


        Optional<Ambiente> findAmbientePreUpdate = listarAmbientesUseCase.findOne(amb1);
        findAmbientePreUpdate.ifPresent(System.out::println);
        List<ItemAmbiente> itensAmbiente = new ArrayList<>();
        itensAmbiente.add(l1);
        itensAmbiente.add(i1);
        itensAmbiente.add(s1);
        itensAmbiente.add(l2);
        itensAmbiente.add(l3);
        itensAmbiente.add(l4);
        itensAmbiente.add(s2);
        itensAmbiente.add(s3);
        itensAmbiente.add(i2);
        itensAmbiente.add(i3);
        //itensAmbiente.add(i4);
        Ambiente ambienteUpdate = new Ambiente(1, "Sala", itensAmbiente);
        editarAmbienteUseCase.update(ambienteUpdate);
        Optional<Ambiente> findAmbientePosUpdate = listarAmbientesUseCase.findOne(amb1);
        findAmbientePosUpdate.ifPresent(System.out::println);


        Optional<Interruptor> interruptorOptionalPre = listarInterruptorUseCase.findOne(mod1);
        interruptorOptionalPre.ifPresent(System.out::println);
        List<Luminaria> luminarias = new ArrayList<>();
        luminarias.add(l1);
        luminarias.add(l2);
        luminarias.add(l3);
        luminarias.add(l4);
        Interruptor interruptorUpdate = new Interruptor(1, "mod1", luminarias);
        editarInterruptorUseCase.update(interruptorUpdate);
        Optional<Interruptor> interruptorOptionalPost = listarInterruptorUseCase.findOne(mod1);
        interruptorOptionalPost.ifPresent(System.out::println);
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
