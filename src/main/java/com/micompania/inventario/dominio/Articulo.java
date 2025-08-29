package com.micompania.inventario.dominio;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Articulo {

  private String id;
  private String nombre;
  private int cantidad;

}
