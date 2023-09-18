package com.lucascost.padaria.padaria.controllers;

import com.lucascost.padaria.padaria.dto.ProdutoDTO;
import com.lucascost.padaria.padaria.entities.Produto;
import com.lucascost.padaria.padaria.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> findAll(){
        List<ProdutoDTO> produtos = produtoService.findAll();
        if (!produtos.isEmpty()) {
            return ResponseEntity.ok(produtos);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> saveProduto(@RequestBody ProdutoDTO produtoDTO){
        Produto novoProduto = produtoService.saveProduto(produtoDTO);
        if(novoProduto != null) {
            String uri = "produtos/" + novoProduto.getId();
            return ResponseEntity.created(URI.create(uri)).build();
        }

        return ResponseEntity.badRequest().body(null);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> updateProdtuto(@PathVariable Long id, @RequestBody ProdutoDTO produtoDTO){
        Produto produtoAtualizado = produtoService.updateProduto(id, produtoDTO);
        if (produtoAtualizado != null){
            return ResponseEntity.ok(produtoAtualizado);
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduto(@PathVariable Long id){
        boolean produtoRemovido = produtoService.deleteProduto(id);
        if (produtoRemovido)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body("Falha ao remover o produto.");
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> decreaseStock(@PathVariable Long id){
        boolean baixa = produtoService.decreaseStock(id);
        if(baixa){
            return ResponseEntity.ok("Ok.");
        }
        else return ResponseEntity.badRequest().build();
    }
}
