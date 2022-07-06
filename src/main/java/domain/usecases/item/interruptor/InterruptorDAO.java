package domain.usecases.item.interruptor;

import domain.entities.item.interruptor.Interruptor;
import domain.usecases.utils.DAO;

import java.util.Optional;

public interface InterruptorDAO extends DAO<Interruptor, Integer> {

    Optional<Interruptor> findByNome(String nome);
}
