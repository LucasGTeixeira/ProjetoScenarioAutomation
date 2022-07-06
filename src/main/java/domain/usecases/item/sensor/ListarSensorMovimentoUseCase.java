package domain.usecases.item.sensor;

import domain.entities.item.sensorMovimento.SensorMovimento;
import domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class ListarSensorMovimentoUseCase {
    private final SensorMovimentoDAO sensorMovimentoDAO;

    public ListarSensorMovimentoUseCase(SensorMovimentoDAO sensorMovimentoDAO) {
        this.sensorMovimentoDAO = sensorMovimentoDAO;
    }

    public List<SensorMovimento> findAll(){
        return sensorMovimentoDAO.findAll();
    }

    public Optional<SensorMovimento> findOne(Integer id){
        if(id == null)
            throw new IllegalArgumentException("id não pode ser nulo");
        return sensorMovimentoDAO.findOne(id);
    }

    public Optional<SensorMovimento> findByNome(String nomeSensor){
        if(Validator.nullOrEmpty(nomeSensor))
            throw new IllegalArgumentException("nome de sensor nulo ou vazio");
        return sensorMovimentoDAO.findByNome(nomeSensor);
    }
}
