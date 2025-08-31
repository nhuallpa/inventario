package com.micompania.inventario.infrastructure;

import com.micompania.inventario.dominio.Item;
import com.micompania.inventario.dominio.ItemRepository;
import com.micompania.inventario.dominio.ItemService;
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

  @CircuitBreaker(name = "increaseStock")
  @CacheEvict(value = "items", key = "#id")
  public Item increaseStock(String id, int quantity) {
    List<Item> items = getAllItems();
    Item itemFound = null;
    for (Item item: items) {
      if (item.getId().equals(id)) {
        item.setStock(item.getStock() + quantity);
        itemFound = item;
      }
    }
    log.info("Increase item with id: " + id);
    return itemFound;
  }

  @CircuitBreaker(name = "decreaseStock")
  @CacheEvict(value = "items", key = "#id")
  public Item decreaseStock(String id, int quantity) {
    List<Item> items = getAllItems();
    Item itemFound = null;
    for (Item item: items) {
      if (item.getId().equals(id)) {
        item.setStock(item.getStock() - quantity);
        itemFound = item;
      }
    }
    log.info("Decrease item with id: " + id);
    return itemFound;
  }
}