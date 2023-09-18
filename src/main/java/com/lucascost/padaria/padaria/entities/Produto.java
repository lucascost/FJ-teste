package com.lucascost.padaria.padaria.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double preco;
    private int qtd_em_estoque;

    public Produto() {
    }

    public Produto(Long id, String nome, double preco, int qtd_em_estoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.qtd_em_estoque = qtd_em_estoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtd_em_estoque() {
        return qtd_em_estoque;
    }

    public void setQtd_em_estoque(int qtd_em_estoque) {
        this.qtd_em_estoque = qtd_em_estoque;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", qtd_em_estoque=" + qtd_em_estoque +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produto produto = (Produto) o;
        return Double.compare(produto.getPreco(), getPreco()) == 0 && getQtd_em_estoque() == produto.getQtd_em_estoque() && Objects.equals(getId(), produto.getId()) && Objects.equals(getNome(), produto.getNome());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNome(), getPreco(), getQtd_em_estoque());
    }
}
