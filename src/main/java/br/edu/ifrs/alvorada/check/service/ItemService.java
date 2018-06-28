package br.edu.ifrs.alvorada.check.service;

import br.edu.ifrs.alvorada.check.config.Messages;
import br.edu.ifrs.alvorada.check.domain.Item;
import br.edu.ifrs.alvorada.check.domain.Status;
import br.edu.ifrs.alvorada.check.domain.User;
import br.edu.ifrs.alvorada.check.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;
    private final Messages messages;

    public Item getOneById(Long id) {
        if (id == null)
            return null;
        Optional<Item> optionalItem = itemRepository.findById(id);
        return optionalItem.isPresent() ? optionalItem.get() : null;
    }

    public Item getOneById(Long id, BindingResult bindingResult, User user) {
        if (id == null || user == null)
            return null;

        Optional<Item> optionalItem = itemRepository.findById(id);

        // TODO RNG001 Cassiano
        // É um item cadastrado
        if (!optionalItem.isPresent()) {
            bindingResult.addError(new FieldError("search", "criteria", messages.get("field.not.founded")));
            return null;
        }

        // TODO RNG005 Cassiano
        // Item está ativo
        if (!isActive(optionalItem.get())) {
            bindingResult.addError(new FieldError("search", "criteria", messages.get("field.not.active")));
            return null;
        }

        return optionalItem.get();

    }

    public boolean isActive(Item item) {
        return item.getStatus().equals(Status.ACTIVE) ? true : false;
    }


}
