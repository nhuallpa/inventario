package com.mycompany.inventory.domain;

import java.util.List;

public interface ItemService {
  List<Item> getAllItems();

  Item getItemById(String id);

  Item incrementStock(String id, int quantity);

  Item decrementStock(String id, int quantity);

}
