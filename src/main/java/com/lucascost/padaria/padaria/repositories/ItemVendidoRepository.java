package com.lucascost.padaria.padaria.repositories;

import com.lucascost.padaria.padaria.entities.ItemVendido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ItemVendidoRepository extends JpaRepository<ItemVendido, Long> {
    List<ItemVendido> findByNomeIgnoreCaseContaining(String query);

    List<ItemVendido> findByData(Date query);
}
