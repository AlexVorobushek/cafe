package com.example.cafe.controller;

import com.example.cafe.dto.ClientDTO;
import com.example.cafe.repository.ClientRepository;
import com.example.cafe.service.ClientService;
import org.assertj.core.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@AutoConfigureMockMvc
class ClientControllerTest {
    @Autowired
    private ClientController clientController;

    @Test
    void test() throws Exception {
        assertThat(clientController).isNotNull();
    }
}


