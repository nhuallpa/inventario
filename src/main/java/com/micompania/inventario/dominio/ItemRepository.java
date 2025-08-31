package com.micompania.inventario.dominio;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepository {
  private static final List<Item> items = List.of(
          new Item("1", "Laptop", 10),
          new Item("2", "Mouse", 50)
  );

  public List<Item> getAllItems() {
    return items;
  }
}
