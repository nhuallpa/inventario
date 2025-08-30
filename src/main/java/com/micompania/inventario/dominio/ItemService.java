package com.micompania.inventario.dominio;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {
  public List<Item> getItems() {
    List<Item> items = List.of(
            new Item("1", "Laptop", 10),
            new Item("2", "Mouse", 50)
    );
    return items;
  }
}