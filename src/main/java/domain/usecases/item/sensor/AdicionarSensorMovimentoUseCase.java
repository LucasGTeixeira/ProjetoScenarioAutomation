package domain.usecases.item.sensor;

import domain.entities.item.sensorMovimento.SensorMovimento;
import domain.usecases.utils.exceptions.EntityAlreadyExistsException;

public class AdicionarSensorMovimentoUseCase {
    private final SensorMovimentoDAO sensorMovimentoDAO;
    private final ListarSensorMovimentoUseCase listarSensorMovimentoUseCase;

    public AdicionarSensorMovimentoUseCase(SensorMovimentoDAO sensorMovimentoDAO, ListarSensorMovimentoUseCase listarSensorMovimentoUseCase) {
        this.sensorMovimentoDAO = sensorMovimentoDAO;
        this.listarSensorMovimentoUseCase = listarSensorMovimentoUseCase;
    }

    public Integer insert(SensorMovimento sensorMovimento){
        SensorMovimentoValidator.validarSensor(sensorMovimento);

        String nomeSensor = sensorMovimento.getNome();
        boolean nomeSensorAlreadyExists = listarSensorMovimentoUseCase.findByNome(nomeSensor).isPresent();
        if(nomeSensorAlreadyExists)
            throw new EntityAlreadyExistsException("nome de sensor já cadastrado no sistema");

        return sensorMovimentoDAO.create(sensorMovimento);
    }
}
