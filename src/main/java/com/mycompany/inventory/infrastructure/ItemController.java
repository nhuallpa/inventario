package com.mycompany.inventory.infrastructure;

import com.mycompany.inventory.domain.Item;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  @PutMapping("/{id}/increment-stock")
  public ResponseEntity<Item> incrementStock(@PathVariable String id, @RequestBody OperationStockRequest request) throws Exception {

    Item itemFound = this.itemServiceImpl.incrementStock(id, request.getQuantity());
    if (itemFound == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(itemFound);
    }
  }

  @PutMapping("/{id}/decrement-stock")
  public ResponseEntity<Item> decrementStock(@PathVariable String id, @RequestBody OperationStockRequest request) throws Exception {
    Item itemFound = itemServiceImpl.decrementStock(id, request.getQuantity());
    if (itemFound == null) {
      return ResponseEntity.notFound().build();
    } else {
      return ResponseEntity.ok(itemFound);
    }
  }

}
