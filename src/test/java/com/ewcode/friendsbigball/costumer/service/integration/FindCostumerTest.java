package com.ewcode.friendsbigball.costumer.service.integration;

import com.ewcode.friendsbigball.common.entity.Costumer;
import com.ewcode.friendsbigball.costumer.resource.dto.CreateCostumerDto;
import com.ewcode.friendsbigball.costumer.service.CostumerService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class FindCostumerTest {
  @Autowired CostumerService costumerService;
  @Autowired MockMvc mockMvc;

  @BeforeEach
  void setUpEach() {
    // clean database
  }

  @Test
  void findACostumerUsingAFilter() {
    CreateCostumerDto costumerToCreate = createSimpleCostumer();
    costumerService.create(costumerToCreate);

    Page<Costumer> costumers =
            costumerService.findByFilterPaginated("simple costumer", Pageable.ofSize(1));

    Assertions.assertThatIterable(costumers.getContent())
        .allMatch(costumer -> costumer.getName().equals(costumerToCreate.getName()));
  }

  private CreateCostumerDto createSimpleCostumer() {
    CreateCostumerDto costumerDto = new CreateCostumerDto();
    return costumerDto.setName("Simple costumer").setEmail("teste@teste.com.br");
  }

  // AfterEach
  @AfterEach
  void setupEach() {
    // clean database
  }
}
