package com.lucascost.padaria.padaria.dto;

import com.lucascost.padaria.padaria.entities.ItemVendido;
import com.lucascost.padaria.padaria.entities.Produto;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

public class ItemVendidoDTO {
    
    private Long id;
    private String nome;
    private double preco;
    private int quantidade;
    private LocalDateTime data;

    public ItemVendidoDTO() {
    }

    public ItemVendidoDTO(ItemVendido entity) {
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

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ItemVendido{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                ", data=" + data +
                '}';
    }

}
