package com.lucascost.padaria.padaria.dto;

import com.lucascost.padaria.padaria.entities.Produto;
import org.springframework.beans.BeanUtils;

public class ProdutoDTO {
    private Long id;
    private String nome;
    private double preco;
    private int qtd_em_estoque;

    public ProdutoDTO() {
    }

    public ProdutoDTO(Produto entity){
        BeanUtils.copyProperties(entity, this);
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
        return "ProdutoDTO{" +
                "id=" + id +
                ", name='" + nome + '\'' +
                ", preco=" + preco +
                ", qtd_em_estoque=" + qtd_em_estoque +
                '}';
    }
}
