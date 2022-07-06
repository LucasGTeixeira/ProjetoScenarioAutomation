package application.repository.mocked;

import domain.entities.item.interruptor.Interruptor;
import domain.usecases.item.interruptor.InterruptorDAO;

import java.util.*;

public class InterruptorMockDAO implements InterruptorDAO{
    private static final Map<Integer, Interruptor> inMemoryDb = new LinkedHashMap<>();
    private static int autoIncrementId;

    @Override
    public Integer create(Interruptor interruptor) {
        autoIncrementId++;
        interruptor.setId(autoIncrementId);
        inMemoryDb.put(autoIncrementId, interruptor);
        return autoIncrementId;
    }

    @Override
    public Optional<Interruptor> findOne(Integer key) {
        return inMemoryDb.values().stream()
                .filter(interruptor -> interruptor.getId().equals(key))
                .findAny();
    }

    @Override
    public List<Interruptor> findAll() {
        return new ArrayList<>(inMemoryDb.values());
    }

    @Override
    public Optional<Interruptor> findByNome(String nome) {
        return inMemoryDb.values().stream()
                .filter(interruptor -> interruptor.getNome().equals(nome))
                .findAny();
    }

    @Override
    public boolean update(Interruptor interruptor) {
        Integer id = interruptor.getId();
        boolean idFoundOnDb = inMemoryDb.containsKey(id);
        if(idFoundOnDb){
            inMemoryDb.replace(id, interruptor);
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
    public boolean delete(Interruptor interruptor) {
        Integer key = interruptor.getId();
        return deleteByKey(key);
    }
}
