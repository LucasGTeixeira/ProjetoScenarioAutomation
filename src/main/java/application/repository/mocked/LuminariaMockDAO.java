package application.repository.mocked;

import domain.entities.item.luminaria.Luminaria;
import domain.usecases.item.luminaria.LuminariaDAO;

import java.util.*;

public class LuminariaMockDAO implements LuminariaDAO {
    private static final Map<Integer, Luminaria> inMemoryDb = new LinkedHashMap<>();
    private static int autoIncrementId;

    @Override
    public Integer create(Luminaria luminaria) {
        autoIncrementId++;
        luminaria.setId(autoIncrementId);
        inMemoryDb.put(autoIncrementId, luminaria);
        return autoIncrementId;
    }

    @Override
    public Optional<Luminaria> findOne(Integer key) {
        return inMemoryDb.values().stream()
                .filter(luminaria -> luminaria.getId().equals(key))
                .findAny();
    }

    @Override
    public List<Luminaria> findAll() {
        return new ArrayList<>(inMemoryDb.values());
    }

    @Override
    public Optional<Luminaria> findByNome(String nome) {
        return inMemoryDb.values().stream()
                .filter(luminaria -> luminaria.getNome().equals(nome))
                .findAny();
    }

    @Override
    public boolean update(Luminaria luminaria) {
        Integer id = luminaria.getId();
        boolean idFoundOnDb = inMemoryDb.containsKey(id);
        if(idFoundOnDb){
            inMemoryDb.replace(id, luminaria);
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
    public boolean delete(Luminaria luminaria) {
        Integer key = luminaria.getId();
        return deleteByKey(key);
    }
}
