package com.micompania.inventario.infraestructura;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class OperationStockRequest {
  private int quantity;
}
