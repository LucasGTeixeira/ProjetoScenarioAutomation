package domain.entities.projeto;

import domain.entities.ambiente.Ambiente;

import java.util.List;

public class Projeto {
    private Integer id;
    private String nome;
    private List<Ambiente> ambientes;

    public Projeto(String nome) {
        this.nome = nome;
    }

    public Projeto(Integer id, String nome, List<Ambiente> ambientes) {
        this.id = id;
        this.nome = nome;
        this.ambientes = ambientes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "Projeto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", ambientes=" + ambientes +
                '}';
    }
}
