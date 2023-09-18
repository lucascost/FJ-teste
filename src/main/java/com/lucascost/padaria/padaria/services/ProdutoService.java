package com.lucascost.padaria.padaria.services;

import com.lucascost.padaria.padaria.dto.ProdutoDTO;
import com.lucascost.padaria.padaria.entities.Produto;
import com.lucascost.padaria.padaria.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;


    public List<ProdutoDTO> findAll(){
        List<Produto> result = produtoRepository.findAll();
        return result.stream().map(ProdutoDTO::new).toList();
    }

    public ProdutoDTO findById(Long id){
        Optional<Produto> result = produtoRepository.findById(id);
        return result.map(ProdutoDTO::new).orElse(null);
    }

    public Produto updateProduto(Long id, ProdutoDTO produtoDTO){
        Optional<Produto> produtoNoBanco = produtoRepository.findById(id);
        if (produtoNoBanco.isPresent()){
            Produto produto = produtoNoBanco.get();
            produto.setNome(produtoDTO.getNome());
            produto.setPreco(produtoDTO.getPreco());
            produto.setQtd_em_estoque(produtoDTO.getQtd_em_estoque());
            return produtoRepository.save(produto);
        }
        return null;
    }


    public Produto saveProduto(ProdutoDTO produto){
        Produto novoProduto = new Produto(produto.getId(),produto.getNome(),produto.getPreco(), produto.getQtd_em_estoque());
        return produtoRepository.save(novoProduto);
    }

    public boolean deleteProduto(Long id){
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isPresent()){
            produtoRepository.delete(produto.get());
            return true;
        }
        return false;
    }

    public boolean decreaseStock(Long id) {
        Optional<Produto> produtoNoBanco = produtoRepository.findById(id);

        if(produtoNoBanco.isPresent()){
            Produto produto = produtoNoBanco.get();
            int estoqueAtual = produto.getQtd_em_estoque();
            if (estoqueAtual > 0) {
                produto.setQtd_em_estoque(estoqueAtual - 1);
                produtoRepository.save(produto);
                return true;
            }
        }
        return false;
    }
}
