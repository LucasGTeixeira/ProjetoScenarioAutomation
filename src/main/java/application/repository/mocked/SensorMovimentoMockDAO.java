package application.repository.mocked;

import domain.entities.item.sensorMovimento.SensorMovimento;
import domain.usecases.item.sensor.SensorMovimentoDAO;

import java.util.*;

public class SensorMovimentoMockDAO implements SensorMovimentoDAO {
    private static final Map<Integer, SensorMovimento> inMemoryDb = new LinkedHashMap<>();
    private static int autoIncrementId;

    @Override
    public Integer create(SensorMovimento sensor) {
        autoIncrementId++;
        sensor.setId(autoIncrementId);
        inMemoryDb.put(autoIncrementId, sensor);
        return autoIncrementId;
    }

    @Override
    public Optional<SensorMovimento> findOne(Integer key) {
        return inMemoryDb.values().stream()
                .filter(sensorMovimento -> sensorMovimento.getId().equals(key))
                .findAny();
    }

    @Override
    public List<SensorMovimento> findAll() {
        return new ArrayList<>(inMemoryDb.values());
    }

    @Override
    public Optional<SensorMovimento> findByNome(String nome) {
        return inMemoryDb.values().stream()
                .filter(sensorMovimento -> sensorMovimento.getNome().equals(nome))
                .findAny();
    }

    @Override
    public boolean update(SensorMovimento sensor) {
        Integer id = sensor.getId();
        boolean idFoundOnDb = inMemoryDb.containsKey(id);
        if(idFoundOnDb){
            inMemoryDb.replace(id, sensor);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        boolean idFoundOnDb = inMemoryDb.containsKey(key);
        if(idFoundOnDb){
            inMemoryDb.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(SensorMovimento sensor) {
        Integer key = sensor.getId();
        return deleteByKey(key);
    }
}
