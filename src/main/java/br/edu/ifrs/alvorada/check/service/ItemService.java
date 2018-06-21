package br.edu.ifrs.alvorada.check.service;

import br.edu.ifrs.alvorada.check.domain.Item;
import br.edu.ifrs.alvorada.check.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item getOneById(Long id) {
        if (id == null)
            return null;
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.isPresent() ? optionalItem.get() : null;
    }

}
