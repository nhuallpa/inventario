package com.mycompany.inventory.infrastructure;

import com.mycompany.inventory.domain.Item;
import com.mycompany.inventory.domain.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InventarioControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private ItemRepository itemRepository;

  @BeforeEach
  public void setup() {
    when(itemRepository.getAllItems()).thenReturn(List.of(
            new Item("1", "Laptop", 10),
            new Item("2", "Mouse", 50)));
  }

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
  public void getItemById() throws Exception {
    this.mockMvc.perform(get(ItemController.ENDPOINT + "/{id}", "1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("Laptop"))
            .andExpect(jsonPath("$.stock").value(10));
  }

  @Test
  public void incrementStock() throws Exception {
    String requestBody = "{ \"quantity\": 5 }";
    this.mockMvc.perform(
                    put(ItemController.ENDPOINT + "/{id}/increment-stock", "1")
                            .contentType("application/json")
                            .content(requestBody)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("Laptop"))
            .andExpect(jsonPath("$.stock").value(15));
  }

  @Test
  public void decrementStock() throws Exception {
    String requestBody = "{ \"quantity\": 3 }";
    this.mockMvc.perform(
                    put(ItemController.ENDPOINT + "/{id}/decrement-stock", "1")
                            .contentType("application/json")
                            .content(requestBody)
            )
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id").value("1"))
            .andExpect(jsonPath("$.name").value("Laptop"))
            .andExpect(jsonPath("$.stock").value(7));
  }
}
