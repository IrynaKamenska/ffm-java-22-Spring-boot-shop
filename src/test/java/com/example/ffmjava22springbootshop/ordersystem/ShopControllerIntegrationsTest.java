package com.example.ffmjava22springbootshop.ordersystem;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ShopControllerIntegrationsTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private ObjectMapper objectMapper;


    @Test
    @DirtiesContext
    void getProducts() throws Exception {
        String body = """
                [
                    {
                        "id": "1",
                        "name": "Apfel"
                    },
                    {
                        "id": "2",
                        "name": "Banane"
                    },
                    {
                        "id": "3",
                        "name": "Zitrone"
                    },
                    {
                        "id": "4",
                        "name": "Mandarine"
                    }
                ]
                """;

        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(body));

    }


    @Test
    @DirtiesContext
    void addProduct() throws Exception {
        String body = """
                    {
                        "id": "5",
                        "name": "Birne"
                    }
                """;

        String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();

        System.out.println(content);

//        Product product = objectMapper.readValue(content,Product.class);

        String expected = """
                [
                    {
                      "id": "1","name": "Apfel"
                    },
                    {
                     "id": "2","name": "Banane"
                    },
                    {
                    "id": "3","name": "Zitrone"
                    },
                    {
                    "id": "4","name": "Mandarine"
                    },
                    {"id":"5","name":"Birne"}
                ]
                """;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(expected));

    }


    @Test
    @DirtiesContext
    void getOrdersAndExpectEmptyList() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));

    }

    @Test
    @DirtiesContext
    void addOrder() throws Exception {
        //     @PostMapping("/orders/{id}")
        //    public Order addOrder(@RequestBody List<String> list,
        //                          @PathVariable String id){
        //        return shopService.addOrder(id,list);

        String expectedOrder = """
            [{"id":"17","products":[{"id":"1","name":"Apfel"},{"id":"2","name":"Banane"}]}]
            """;

        String id = "17";
        String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/"+id)
              .contentType(MediaType.APPLICATION_JSON)
              .content("[1,2]"))
              .andExpect(status().isOk())
              .andReturn().getResponse().getContentAsString();
        System.out.println(content);


//        Order order = objectMapper.readValue(content, Order.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOrder));
    }

    @Test
    @DirtiesContext
    void deleteOrder() throws Exception {
        String id = "77";
        String content = mockMvc.perform(MockMvcRequestBuilders.post("/api/orders/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[1,2,3]"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
        System.out.println(content);


        // when
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/orders/" + id))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/" + id))
                .andExpect(status().isOk())
                .andExpect(content().string(""));

    }


}