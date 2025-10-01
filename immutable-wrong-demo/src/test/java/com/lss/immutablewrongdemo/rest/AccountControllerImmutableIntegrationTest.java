package com.lss.immutablewrongdemo.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.lss.immutablewrongdemo.ImmutableWrongDemoApplication;
import com.lss.immutablewrongdemo.config.InitTestDataConfig;
import com.lss.immutablewrongdemo.entity.Account;
import com.lss.immutablewrongdemo.entity.Balance;
import com.lss.immutablewrongdemo.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
@SpringBootTest(classes = {ImmutableWrongDemoApplication.class,
        InitTestDataConfig.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        properties = {"spring.main.allow-bean-definition-overriding=true", "app.mutable=false"})
class AccountControllerImmutableIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    void calculatePortfolio_ShouldReturnExpectedResponse() throws Exception {
        // Given
        final List<Account> accounts = accountRepository.findAll();
        final BigDecimal expectedResult = accounts.stream()
                .map(Account::getBalance)
                .map(Balance::getTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // When & Then
        mockMvc.perform(get("/immutable/portfolio"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalBalance").value(expectedResult));
    }
}