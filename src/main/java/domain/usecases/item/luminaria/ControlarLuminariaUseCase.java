package domain.usecases.item.luminaria;

import domain.entities.item.luminaria.Luminaria;
import domain.usecases.utils.exceptions.EntityNotFoundException;

import java.util.Timer;
import java.util.TimerTask;

public class ControlarLuminariaUseCase {
    private final LuminariaDAO luminariaDAO;
    private final ListarLuminariasUseCase listarLuminariasUseCase;

    public ControlarLuminariaUseCase(LuminariaDAO luminariaDAO, ListarLuminariasUseCase listarLuminariasUseCase) {
        this.luminariaDAO = luminariaDAO;
        this.listarLuminariasUseCase = listarLuminariasUseCase;
    }

    public void ligarLuminaria(Luminaria luminaria, int tempo){
        LuminariaValidator.validarLuminaria(luminaria);
        verificaNomeLuminaria(luminaria);

        luminaria.setStatus(true);

        new Reminder(luminaria, tempo, luminariaDAO);
        System.out.println("Luminária ligada.");

        luminariaDAO.update(luminaria);
    }

    public void toggle(Luminaria luminaria){
        LuminariaValidator.validarLuminaria(luminaria);
        verificaNomeLuminaria(luminaria);

        luminaria.setStatus(!luminaria.getStatus());

        luminariaDAO.update(luminaria);
    }

    private void verificaNomeLuminaria(Luminaria luminaria) {
        String nomeLuminaria = luminaria.getNome();
        boolean nomeLuminariaNotFound = listarLuminariasUseCase.findByNome(nomeLuminaria).isEmpty();
        if(nomeLuminariaNotFound) {
            throw new EntityNotFoundException("luminaria não encnotrada no sistema");
        }
    }

    private static class Reminder {
        Timer timer;

        public Reminder(Luminaria luminaria, int seconds, LuminariaDAO luminariaDAO) {
            timer = new Timer();
            timer.schedule(new RemindTask(luminaria, luminariaDAO), seconds* 1000L);
        }

        class RemindTask extends TimerTask {
            Luminaria luminaria;
            LuminariaDAO luminariaDAO;
            public RemindTask(Luminaria luminaria, LuminariaDAO luminariaDAO) {
                this.luminariaDAO = luminariaDAO;
                this.luminaria = luminaria;
            }

            public void run() {
                luminaria.setStatus(false);
                luminariaDAO.update(luminaria);
                System.out.println("A Luminária se apagou");
                timer.cancel();
            }
        }
    }
}
