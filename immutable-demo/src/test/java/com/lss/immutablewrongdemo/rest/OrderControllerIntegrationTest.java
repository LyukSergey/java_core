package com.lss.immutablewrongdemo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lss.immutablewrongdemo.ImmutableWrongDemoApplication;
import com.lss.immutablewrongdemo.entity.CartItem;
import com.lss.immutablewrongdemo.entity.ShoppingCart;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = {ImmutableWrongDemoApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.main.allow-bean-definition-overriding=true"})
class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testProcessCartAsync_SuccessfulProcessingWithDiscount() throws Exception {
        //Given
        List<CartItem> items = List.of(
                new CartItem("Product_1", 2, new BigDecimal("60.00")), // 120.00
                new CartItem("Product_2", 1, new BigDecimal("50.00"))  // 50.00
        );

        BigDecimal initialTotal = ShoppingCart.calculateInitialTotal(items);
        ShoppingCart initialCart = new ShoppingCart(
                "Shopping_cart_1",
                "User_1",
                items,
                initialTotal,
                initialTotal
        );

        // 170.00 - 17.00 = 153.00
        final BigDecimal DISCOUNT_RATE = new BigDecimal("0.10");
        final BigDecimal expectedFinalTotal = initialTotal
                .subtract(initialTotal.multiply(DISCOUNT_RATE))
                .setScale(2, RoundingMode.HALF_UP);

        //When, Then
        mockMvc.perform(post("/orders/async")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(initialCart)))

                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$.cartId").value(initialCart.cartId()))
                .andExpect(jsonPath("$.finalTotal").value(expectedFinalTotal.toPlainString()));
    }

}