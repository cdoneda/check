package br.edu.ifrs.alvorada.check.repository;

import br.edu.ifrs.alvorada.check.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

}