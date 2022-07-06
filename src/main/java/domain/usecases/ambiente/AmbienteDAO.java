package domain.usecases.ambiente;

import domain.entities.ambiente.Ambiente;
import domain.entities.item.ItemAmbiente;
import domain.usecases.utils.DAO;

import java.util.Optional;

public interface AmbienteDAO extends DAO<Ambiente, Integer> {
    Optional<Ambiente> findByNome(String nome);

    Optional<ItemAmbiente> findItemByNome(String nomeItem);
}
