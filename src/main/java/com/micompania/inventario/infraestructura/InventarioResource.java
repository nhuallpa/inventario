package com.micompania.inventario.infraestructura;

import com.micompania.inventario.dominio.Articulo;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/articulos")
public class InventarioResource {

  @RequestMapping(method = RequestMethod.GET, produces = "application/json", value = "/")
  public ResponseEntity<List<Articulo>> obtenerArticulos() {
    // Lógica para obtener la lista de artículos
    List<Articulo> articulos = List.of(
        new Articulo("1", "Laptop", 10),
        new Articulo("2", "Mouse", 50)
    );
    return ResponseEntity.ok(articulos);
  }
}
