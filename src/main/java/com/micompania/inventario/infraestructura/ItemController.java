package com.micompania.inventario.infraestructura;

import com.micompania.inventario.dominio.Item;
import com.micompania.inventario.dominio.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ItemController.ENDPOINT)
@AllArgsConstructor
public class ItemController {

  public static final String ENDPOINT = "/items";

  private final ItemService itemService;

  @GetMapping
  public ResponseEntity<List<Item>> getAllItems() throws Exception {
    List<Item> items = itemService.getItems();
    return ResponseEntity.ok(items);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Item> getItemById(@PathVariable String id) throws Exception {
    List<Item> items = itemService.getItems();
    for (Item item: items) {
      if (item.getId().equals(id)) {
        return ResponseEntity.ok(item);
      }
    }
    return ResponseEntity.notFound().build();
  }
  @PostMapping("/{id}/increase-stock")
  public ResponseEntity<Item> increaseStock(@PathVariable String id, @RequestBody OperationStockRequest request) throws Exception {

    List<Item> items = itemService.getItems();
    for (Item item: items) {
      if (item.getId().equals(id)) {
        item.setStock(item.getStock() + request.getQuantity());
        return ResponseEntity.ok(item);
      }
    }
    return ResponseEntity.notFound().build();
  }

  @PostMapping("/{id}/decrease-stock")
  public ResponseEntity<Item> decreaseStock(@PathVariable String id, @RequestBody OperationStockRequest request) throws Exception {
    List<Item> items = itemService.getItems();
    for (Item item: items) {
      if (item.getId().equals(id)) {
        item.setStock(item.getStock() - request.getQuantity());
        return ResponseEntity.ok(item);
      }
    }
    return ResponseEntity.notFound().build();
  }

}
