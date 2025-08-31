package com.micompania.inventario.infrastructure;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OperationStockRequest {
  private int quantity;
}
