package com.micompania.inventario.infraestructura;

import com.micompania.inventario.dominio.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest({ItemController.class, ItemService.class})
public class InventarioControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getAllItems() throws Exception {
    this.mockMvc.perform(get(ItemController.ENDPOINT)).andExpect(status().isOk())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value("1"))
            .andExpect(jsonPath("$[0].name").value("Laptop"))
            .andExpect(jsonPath("$[1].id").value("2"))
            .andExpect(jsonPath("$[1].name").value("Mouse"));
  }

  @Test
  public void getInventarioById() throws Exception {
    this.mockMvc.perform(get("/inventario/{id}", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.nombre").value("Articulo 1"))
            .andExpect(jsonPath("$.cantidad").value(10));
  }
}
