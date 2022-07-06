package application.repository.mocked;

import domain.entities.ambiente.Ambiente;
import domain.entities.item.ItemAmbiente;
import domain.usecases.ambiente.AmbienteDAO;

import java.util.*;

public class AmbienteMockDAO implements AmbienteDAO {
    private static final Map<Integer, Ambiente> inMemoryDb = new LinkedHashMap<>();
    private static int autoIncrementId;

    @Override
    public Integer create(Ambiente ambiente) {
        autoIncrementId++;
        ambiente.setId(autoIncrementId);
        inMemoryDb.put(autoIncrementId, ambiente);
        return autoIncrementId;
    }

    @Override
    public Optional<ItemAmbiente> findItemByNome(String nomeItem) {
        List<Ambiente> ambientes = (List<Ambiente>) inMemoryDb.values();
        for(Ambiente a : ambientes){
            for(ItemAmbiente item : a.getItens()) {
                if (item.getNome().equals(nomeItem))
                    return Optional.of(item);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Ambiente> findOne(Integer key) {
        return inMemoryDb.values().stream()
                .filter(ambiente -> ambiente.getId().equals(key))
                .findAny();
    }

    @Override
    public List<Ambiente> findAll() {
        return new ArrayList<>(inMemoryDb.values());
    }

    @Override
    public Optional<Ambiente> findByNome(String nome) {
        return inMemoryDb.values().stream()
                .filter(ambiente -> ambiente.getNome().equals(nome))
                .findAny();
    }

    @Override
    public boolean update(Ambiente ambiente) {
        Integer id = ambiente.getId();
        boolean idFoundOnDb = inMemoryDb.containsKey(id);
        if(idFoundOnDb){
            inMemoryDb.replace(id, ambiente);
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
    public boolean delete(Ambiente ambiente) {
        Integer key = ambiente.getId();
        return deleteByKey(key);
    }
}
