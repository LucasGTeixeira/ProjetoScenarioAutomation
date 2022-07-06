package application.repository.mocked;

import domain.entities.ambiente.Ambiente;
import domain.entities.projeto.Projeto;
import domain.usecases.projeto.ProjetoDAO;

import java.util.*;

public class ProjetoMockDAO implements ProjetoDAO {

    private static final Map<Integer, Projeto> inMemoryDb = new LinkedHashMap<>();
    private static int autoIncrementId;


    @Override
    public Integer create(Projeto projeto) {
        autoIncrementId++;
        projeto.setId(autoIncrementId);
        inMemoryDb.put(autoIncrementId, projeto);
        return autoIncrementId;
    }

    @Override
    public Optional<Ambiente> findAmbienteInProjetoByName(String nomeAmbiente) {
        List<Projeto> projetos = (List<Projeto>) inMemoryDb.values();
        for(Projeto projeto : projetos){
            for(Ambiente ambiente : projeto.getAmbientes()) {
                if (ambiente.getNome().equals(nomeAmbiente))
                    return Optional.of(ambiente);
            }
        }
        return Optional.empty();
    }

    @Override
    public Optional<Projeto> findOne(Integer key) {
        return inMemoryDb.values().stream()
                .filter(projeto -> projeto.getId().equals(key))
                .findAny();
    }

    @Override
    public List<Projeto> findAll() {
        return new ArrayList<>(inMemoryDb.values());
    }

    @Override
    public Optional<Projeto> findByNome(String nomeProjeto) {
        return inMemoryDb.values().stream()
                .filter(projeto -> projeto.getNome().equals(nomeProjeto))
                .findAny();
    }

    @Override
    public boolean update(Projeto projeto) {
        Integer id = projeto.getId();
        boolean idFoundOnDb = inMemoryDb.containsKey(id);
        if(idFoundOnDb){
            inMemoryDb.replace(id, projeto);
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
    public boolean delete(Projeto projeto) {
        Integer key = projeto.getId();
        return deleteByKey(key);
    }
}
