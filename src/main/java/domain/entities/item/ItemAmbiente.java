package domain.entities.item;

public abstract class ItemAmbiente {
    private Integer id;
    private String nome;

    public ItemAmbiente(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return nome;
    }
}
