package com.mycompany.inventory.infrastructure;

import com.mycompany.inventory.domain.Item;
import com.mycompany.inventory.domain.ItemRepository;
import com.mycompany.inventory.domain.ItemService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

  private final Log log = LogFactory.getLog(ItemServiceImpl.class);

  private final ItemRepository itemRepository;

  @Override
  public List<Item> getAllItems() {
    return itemRepository.getAllItems();
  }

  @Override
  @Cacheable(value = "items", key = "#id")
  public Item getItemById(String id) {
    List<Item> items = itemRepository.getAllItems();
    Item itemFound = null;
    for (Item item: items) {
      if (item.getId().equals(id)) {
        itemFound = item;
      }
    }
    log.info("Fetching item with id: " + id);
    return itemFound;
  }

  @CircuitBreaker(name = "incrementStock")
  @CacheEvict(value = "items", key = "#id")
  public Item incrementStock(String id, int quantity) {
    List<Item> items = getAllItems();
    Item itemFound = null;
    for (Item item: items) {
      if (item.getId().equals(id)) {
        item.setStock(item.getStock() + quantity);
        itemFound = item;
      }
    }
    log.info("increment item with id: " + id);
    return itemFound;
  }

  @CircuitBreaker(name = "decrementStock")
  @CacheEvict(value = "items", key = "#id")
  public Item decrementStock(String id, int quantity) {
    List<Item> items = getAllItems();
    Item itemFound = null;
    for (Item item: items) {
      if (item.getId().equals(id)) {
        item.setStock(item.getStock() - quantity);
        itemFound = item;
      }
    }
    log.info("decrement item with id: " + id);
    return itemFound;
  }
}