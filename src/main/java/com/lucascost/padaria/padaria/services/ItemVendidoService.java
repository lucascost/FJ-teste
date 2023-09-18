package com.lucascost.padaria.padaria.services;

import com.lucascost.padaria.padaria.dto.ItemVendidoDTO;
import com.lucascost.padaria.padaria.dto.ProdutoDTO;
import com.lucascost.padaria.padaria.entities.ItemVendido;
import com.lucascost.padaria.padaria.entities.Produto;
import com.lucascost.padaria.padaria.repositories.ItemVendidoRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ItemVendidoService {

    @Autowired
    ItemVendidoRepository itemVendidoRepository;

    public List<ItemVendidoDTO> findAll(){
        List<ItemVendido> result = itemVendidoRepository.findAll();
        return result.stream().map(ItemVendidoDTO::new).toList();
    }

    public List<ItemVendidoDTO> findByNome(String query){
        List<ItemVendido> result = itemVendidoRepository.findByNomeIgnoreCaseContaining(query);
        return result.stream().map(ItemVendidoDTO::new).toList();
    }

    public List<ItemVendidoDTO> findByData(Date query){
        List<ItemVendido> result = itemVendidoRepository.findByData(query);
        return result.stream().map(ItemVendidoDTO::new).toList();
    }

    public ItemVendido saveItemVendido(ItemVendidoDTO item){
        ItemVendido itemVendido = new ItemVendido(item.getId(), item.getNome(), item.getPreco(), item.getQuantidade(), item.getData());
        return itemVendidoRepository.save(itemVendido);
    }

    public boolean deleteItemVendido(Long id){
        Optional<ItemVendido> itemVendido = itemVendidoRepository.findById(id);
        if(itemVendido.isPresent()){
            itemVendidoRepository.delete(itemVendido.get());
            return true;
        }
        return false;
    }


    public List<ItemVendidoDTO> filter(String nome, Date data) {
        List<ItemVendidoDTO> vendasPorNome = new ArrayList<>();
        List<ItemVendidoDTO> vendasPorData = new ArrayList<>();
        List<ItemVendidoDTO> resultado = new ArrayList<>();

        if (nome != null){
            vendasPorNome = itemVendidoRepository.findByNomeIgnoreCaseContaining(nome).stream().map(ItemVendidoDTO::new).toList();
        }
        if (data != null){
            vendasPorData = itemVendidoRepository.findByData(data).stream().map(ItemVendidoDTO::new).toList();
        }

        if (vendasPorNome.size() > 0){
            if(vendasPorData.size()>0){
                for (ItemVendidoDTO item : vendasPorNome) {
                    if ( vendasPorData.contains(item)) {
                        resultado.add(item);
                    }
                }
                return resultado;
            }
            else return vendasPorNome;
        }
        return vendasPorData;
    }
}
