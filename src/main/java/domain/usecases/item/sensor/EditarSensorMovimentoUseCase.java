package domain.usecases.item.sensor;

import domain.entities.item.sensorMovimento.SensorMovimento;
import domain.usecases.utils.exceptions.EntityNotFoundException;

public class EditarSensorMovimentoUseCase {
    private final SensorMovimentoDAO sensorMovimentoDAO;
    private final ListarSensorMovimentoUseCase listarSensorMovimentoUseCase;

    public EditarSensorMovimentoUseCase(SensorMovimentoDAO sensorMovimentoDAO, ListarSensorMovimentoUseCase listarSensorMovimentoUseCase) {
        this.sensorMovimentoDAO = sensorMovimentoDAO;
        this.listarSensorMovimentoUseCase = listarSensorMovimentoUseCase;
    }

    public boolean update(SensorMovimento sensorMovimento){
        SensorMovimentoValidator.validarSensor(sensorMovimento);

        String nomeSensor = sensorMovimento.getNome();
        boolean nomeSensorNotFound = listarSensorMovimentoUseCase.findByNome(nomeSensor).isEmpty();
        if(nomeSensorNotFound)
            throw new EntityNotFoundException("nome de sensor não encontrado no sistema");

        return sensorMovimentoDAO.update(sensorMovimento);
    }
}
