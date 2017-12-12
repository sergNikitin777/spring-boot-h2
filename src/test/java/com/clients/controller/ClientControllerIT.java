package com.clients.controller;

import com.clients.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by svnikitin on 12.12.2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ClientControllerIT {

    @LocalServerPort
    private int port;

    TestRestTemplate restTemplate = new TestRestTemplate();
    HttpHeaders headers = new HttpHeaders();

    @Test
    public void testRetrieveStudentCourse() {

        //Получение списка клиентов
        ResponseEntity<String> response = getClients();
        Assert.assertEquals(200,response.getStatusCode().value());


        //Добавление номера
        String answer = addClient();
        Assert.assertEquals("success",answer);

    }

    private ResponseEntity<String> getClients() {
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        return restTemplate.exchange(createURLWithPort("/clients"),
                HttpMethod.GET, entity, String.class);
    }

    private String addClient() {
        String requestJson = "{\"firstName\":\"Иванов\"}";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity1 = new HttpEntity<String>(requestJson,headers);
        return restTemplate.postForObject(createURLWithPort("/addclient"), entity1, String.class);
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
