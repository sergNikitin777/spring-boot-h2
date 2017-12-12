package com.clients.controller;

import com.clients.model.ClientDetails;
import com.clients.model.Phone;
import com.clients.service.ClientService;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Get clients
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<List<ClientDetails>> clientsDetails() {
        List<ClientDetails> clientDetails = clientService.getClientsDetails();
        return new ResponseEntity<List<ClientDetails>>(clientDetails, HttpStatus.OK);
    }

    // Get client
    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public ResponseEntity<List<Phone>> clientDetails() {
        ClientDetails clientDetails = clientService.getClientDetails(1);
        List<Phone> phone = clientDetails.getPhones();
        return new ResponseEntity<List<Phone>>(phone, HttpStatus.OK);
    }


    // Add new Client
    @RequestMapping(value = "/addclient", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addClient(@Valid @RequestBody ClientDetails client) {
        Pair<Boolean, String> pair = clientService.addClient(client);
        if (pair.getKey()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(pair.getValue());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(pair.getValue());
        }
    }

    // Add new Phone to Client
    @RequestMapping(value = "/addphone", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPhone(@Valid @RequestBody Phone phone) {
        Pair<Boolean, String> pair = clientService.addPhone(phone);
        if (pair.getKey()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(pair.getValue());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(pair.getValue());
        }
    }
}
