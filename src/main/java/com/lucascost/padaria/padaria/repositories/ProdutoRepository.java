package com.lucascost.padaria.padaria.repositories;

import com.lucascost.padaria.padaria.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
