package com.micompania.inventario.dominio;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
  private static final List<Item> items = List.of(
          new Item("1", "Laptop", 10),
          new Item("2", "Mouse", 50)
  );

  public List<Item> getItems() {
    return items;
  }
}