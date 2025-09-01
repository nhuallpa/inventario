package com.mycompany.inventory.infrastructure;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OperationStockRequest {
  private int quantity;
}
