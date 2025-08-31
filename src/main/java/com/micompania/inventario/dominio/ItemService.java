package com.micompania.inventario.dominio;

import java.util.List;

public interface ItemService {
  List<Item> getAllItems();

  Item getItemById(String id);

  Item increaseStock(String id, int quantity);

  Item decreaseStock(String id, int quantity);

}
