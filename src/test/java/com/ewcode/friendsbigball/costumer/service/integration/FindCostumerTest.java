package com.ewcode.friendsbigball.costumer.service.integration;

import com.ewcode.friendsbigball.common.IntegrationTest;
import com.ewcode.friendsbigball.common.entity.Costumer;
import com.ewcode.friendsbigball.costumer.resource.dto.CreateCostumerDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.jayway.jsonpath.JsonPath;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;

class FindCostumerTest extends IntegrationTest {
  private final String endpoint = "/costumer";

  @Test
  @DisplayName("Should find a costumer using a filter")
  @Sql(value = "/truncate_all_tables.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
  @Sql(value = "/truncate_all_tables.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
  void findACostumerUsingAFilter() throws Exception {
    CreateCostumerDto costumerToCreate = createSimpleCostumer();
    createACostumer(costumerToCreate);

    List<Costumer> costumers = findCostumers();

    Assertions.assertThatIterable(costumers)
        .allMatch(costumer -> costumer.getName().equals(costumerToCreate.getName()));
  }

  private List<Costumer> findCostumers() throws Exception {
    MultiValueMap<String, String> queryStringParams = new LinkedMultiValueMap<>();
    queryStringParams.add("filter", "email");
    queryStringParams.add("page", "0");
    queryStringParams.add("size", "5");

    MockHttpServletRequestBuilder findByFilterRequest =
        MockMvcRequestBuilders.get(endpoint.concat("/filter")).params(queryStringParams);

    MockHttpServletResponse response =
            mockMvc
            .perform(findByFilterRequest)
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse();

    String result = response.getContentAsString();
    String content = JsonPath.read(result, "$.content").toString();
    return objectMapper.readValue(content, new TypeReference<>() {});
  }

  private void createACostumer(CreateCostumerDto costumerToCreate) throws Exception {
    String createCostumerDto = objectMapper.writeValueAsString(costumerToCreate);
    MockHttpServletRequestBuilder createCostumerRequest = createPostRequest(createCostumerDto);
    mockMvc.perform(createCostumerRequest).andExpect(MockMvcResultMatchers.status().isOk());
  }

  private MockHttpServletRequestBuilder createPostRequest(String json) {
    return MockMvcRequestBuilders.post(endpoint)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(json);
  }

  private CreateCostumerDto createSimpleCostumer() {
    CreateCostumerDto costumerDto = new CreateCostumerDto();
    return costumerDto.setName("Simple name").setEmail("email@teste.com.br");
  }
}
