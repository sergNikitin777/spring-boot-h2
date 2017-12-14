package com.clients.controller;

import com.clients.model.Client;
import com.clients.model.Phone;
import com.clients.service.ClientService;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@Controller
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Get clients
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> clients(@PathParam("id") Integer id) {
            List<Client> client = clientService.getClients(id);
            return new ResponseEntity<List<Client>>(client, HttpStatus.OK);
    }

    // Add new Client
    @RequestMapping(value = "/addclient", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addClient(@Valid @RequestBody Client client) {
        Pair<Boolean, String> pair = clientService.addClient(client);
        if (pair.getKey()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(pair.getValue());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(pair.getValue());
        }
    }

    // Add new Phone to Client
    @RequestMapping(value = "/addphone/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> addPhone(@PathVariable("id") Integer clientId, @Valid @RequestBody Phone phone) {

        Pair<Boolean, String> result = getNormalizePhoneNumber(phone);

        if (result.getKey()) {
            phone.setPhoneNumber(result.getValue());
            Pair<Boolean, String> pair = clientService.addPhone(clientId, phone);
            if (pair.getKey()) {
                return ResponseEntity.status(HttpStatus.CREATED)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(pair.getValue());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                        .contentType(MediaType.TEXT_PLAIN)
                        .body(pair.getValue());
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(result.getValue());
        }
    }

    // Update Client Phone
    @RequestMapping(value = "/editphone", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<String> editPhone(@Valid @RequestBody Phone phone) {
        Pair<Boolean, String> pair = clientService.editPhone(phone);
        if (pair.getKey()) {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(pair.getValue());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_MODIFIED)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(pair.getValue());
        }
    }

    private Pair<Boolean, String> getNormalizePhoneNumber(Phone phone) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber phoneNumber = null;
        try {
            String clearPhone = phone.getPhoneNumber().replaceAll("[^+0-9]", "");
            phoneNumber =  phoneUtil.parse(clearPhone,"RU");
        } catch (NumberParseException e) {
            return  new Pair<Boolean, String>(false,e.getMessage());
        }
        return  new Pair<Boolean, String>(true,"+"+phoneNumber.getCountryCode() + phoneNumber.getNationalNumber());
    }
}
