package com.micompania.inventario.infrastructure;

import com.micompania.inventario.dominio.Item;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = ItemController.ENDPOINT)
@AllArgsConstructor
public class ItemController {

  public static final String ENDPOINT = "/items";

  private final ItemServiceImpl itemServiceImpl;

  @GetMapping
  public ResponseEntity<List<Item>> getAllItems() throws Exception {
    List<Item> items = itemServiceImpl.getAllItems();
    return ResponseEntity.ok(items);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Item> getItemById(@PathVariable String id) throws Exception {
    Item itemFound = itemServiceImpl.getItemById(id);
    if (itemFound == null)
      return ResponseEntity.notFound().build();
    else
      return ResponseEntity.ok(itemFound);
  }

  @PostMapping("/{id}/increase-stock")
  public ResponseEntity<Item> increaseStock(@PathVariable String id, @RequestBody OperationStockRequest request) throws Exception {

    Item itemFound = this.itemServiceImpl.increaseStock(id, request.getQuantity());
    if (itemFound == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(itemFound);
    }
  }

  @PostMapping("/{id}/decrease-stock")
  public ResponseEntity<Item> decreaseStock(@PathVariable String id, @RequestBody OperationStockRequest request) throws Exception {
    Item itemFound = itemServiceImpl.decreaseStock(id, request.getQuantity());
    if (itemFound == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(itemFound);
    }
  }

}
