package com.lucascost.padaria.padaria.controllers;

import com.lucascost.padaria.padaria.dto.ItemVendidoDTO;
import com.lucascost.padaria.padaria.dto.ProdutoDTO;
import com.lucascost.padaria.padaria.entities.ItemVendido;
import com.lucascost.padaria.padaria.services.ItemVendidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/vendidos")
public class ItemVendidoControllers {

    @Autowired
    ItemVendidoService itemVendidoService;

    @GetMapping
    public ResponseEntity<List<ItemVendidoDTO>> findAll(){
        List<ItemVendidoDTO> itens = itemVendidoService.findAll();
        if (!itens.isEmpty()) {
            return ResponseEntity.ok(itens);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/busca")
    public ResponseEntity<List<ItemVendidoDTO>> filter(@RequestParam(name ="nome", required = false) String nome,
                                                       @RequestParam(name ="data", required = false) Date data){
        List<ItemVendidoDTO> itens = itemVendidoService.filter(nome, data);
        if (!itens.isEmpty()) {
            return ResponseEntity.ok(itens);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping
    public ResponseEntity<ProdutoDTO> saveItemVendido(@RequestBody ItemVendidoDTO itemVendidoDTO){
        ItemVendido novoItem = itemVendidoService.saveItemVendido(itemVendidoDTO);
        if(novoItem != null) {
            String uri = "vendidos/" + novoItem.getId();
            return ResponseEntity.created(URI.create(uri)).build();
        }
        return ResponseEntity.badRequest().body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteItemVendido(@PathVariable Long id){
        boolean itemRemovido = itemVendidoService.deleteItemVendido(id);
        if (itemRemovido)
            return ResponseEntity.noContent().build();
        else
            return ResponseEntity.badRequest().body("Falha ao remover o item vendido.");
    }

}
