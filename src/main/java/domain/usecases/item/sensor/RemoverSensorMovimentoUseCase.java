package domain.usecases.item.sensor;

import domain.entities.item.sensorMovimento.SensorMovimento;
import domain.usecases.ambiente.ListarAmbientesUseCase;
import domain.usecases.utils.exceptions.EntityNotFoundException;
import domain.usecases.utils.exceptions.ItemRelatedToAmbienteException;

public class RemoverSensorMovimentoUseCase {
    private final SensorMovimentoDAO sensorMovimentoDAO;
    private final ListarSensorMovimentoUseCase listarSensorMovimentoUseCase;
    private final ListarAmbientesUseCase listarAmbientesUseCase;

    public RemoverSensorMovimentoUseCase(SensorMovimentoDAO sensorMovimentoDAO, ListarSensorMovimentoUseCase listarSensorMovimentoUseCase, ListarAmbientesUseCase listarAmbientesUseCase) {
        this.sensorMovimentoDAO = sensorMovimentoDAO;
        this.listarSensorMovimentoUseCase = listarSensorMovimentoUseCase;
        this.listarAmbientesUseCase = listarAmbientesUseCase;
    }

    public boolean delete(SensorMovimento sensorMovimento){
        SensorMovimentoValidator.validarSensor(sensorMovimento);

        String nomeSensor = sensorMovimento.getNome();
        boolean nomeSensorNotFound = listarSensorMovimentoUseCase.findByNome(nomeSensor).isEmpty();
        if (nomeSensorNotFound)
            throw new EntityNotFoundException("nome de sensor não cadastrado no sistema");

        ProcurarRelacionamentos(sensorMovimento);

        return sensorMovimentoDAO.delete(sensorMovimento);
    }

    public boolean delete(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");

        boolean idSensorNotFound = listarSensorMovimentoUseCase.findOne(id).isEmpty();
        if(idSensorNotFound)
            throw new EntityNotFoundException("não há nenhum sensor com este Id no sistema");

        SensorMovimento sensorMovimento = listarSensorMovimentoUseCase.findOne(id).get();
        ProcurarRelacionamentos(sensorMovimento);

        return sensorMovimentoDAO.deleteByKey(id);
    }

    private void ProcurarRelacionamentos(SensorMovimento sensorMovimento) {
        boolean isSensorMovimentoRelatedToAmbiente = listarAmbientesUseCase.findItemByNome(sensorMovimento.getNome()).isPresent();
        if (isSensorMovimentoRelatedToAmbiente)
            throw new ItemRelatedToAmbienteException("Impossível deletar este sensorMovimento enquanto há ambientes usando ele");
    }
}
