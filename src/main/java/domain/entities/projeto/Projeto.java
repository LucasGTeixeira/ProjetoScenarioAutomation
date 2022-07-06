package domain.entities.projeto;

import domain.entities.ambiente.Ambiente;

import java.util.List;

public class Projeto {
    private String nome;
    private List<Ambiente> ambientes;

    public Projeto(String nome) {
        this.nome = nome;
    }

    public Projeto(String nome, List<Ambiente> ambientes) {
        this.nome = nome;
        this.ambientes = ambientes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Ambiente> getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(List<Ambiente> ambientes) {
        this.ambientes = ambientes;
    }

    @Override
    public String toString() {
        return nome;
    }
}
