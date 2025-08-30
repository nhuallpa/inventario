package com.micompania.inventario.infraestructura;

import com.micompania.inventario.dominio.Item;
import com.micompania.inventario.dominio.ItemService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

}
