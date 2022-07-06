package domain.entities.ambiente;

import domain.entities.item.ItemAmbiente;

import java.util.ArrayList;
import java.util.List;

public class Ambiente {
    private Integer id;
    private String nome;
    private List<ItemAmbiente> itens;

    public Ambiente(String nome) {
        this.nome = nome;
        this.setItens(new ArrayList<>());
    }

    public Ambiente(Integer id, String nome, List<ItemAmbiente> itens) {
        this.id = id;
        this.nome = nome;
        this.itens = itens;
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

    public List<ItemAmbiente> getItens() {
        return itens;
    }

    public void setItens(List<ItemAmbiente> itens) {
        this.itens = itens;
    }

    @Override
    public String toString() {
        return "Ambiente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", itens=" + itens +
                '}';
    }
}
