package domain.usecases.projeto;

import domain.entities.ambiente.Ambiente;
import domain.entities.projeto.Projeto;
import domain.usecases.utils.DAO;

import java.util.Optional;

public interface ProjetoDAO extends DAO<Projeto, Integer> {

    Optional<Ambiente> findAmbienteInProjetoByName(String nome);
}
