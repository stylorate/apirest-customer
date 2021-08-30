package com.apirest.customer.controller;

import com.apirest.customer.constants.Constants;
import com.apirest.customer.controller.dto.CustomerDTO;
import com.apirest.customer.controller.dto.ResponseDTO;
import com.apirest.customer.controller.service.CustomerService;
import com.apirest.customer.exception.NotLegalAgeException;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = {"http://localhost:4200"},
        methods = {RequestMethod.GET})
@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    CustomerService service;

    @Autowired
    ModelMapper mapper;

    Logger log = (Logger) Logger.getLogger(CustomerController.class);

    @GetMapping
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody CustomerDTO request) {
        log.info("Into controller");
        try {
            return ResponseEntity.status(HttpStatus.CREATED.value())
                    .body(new ResponseDTO().setObject(
                            service.sendCustomer(request)).setMsg(HttpStatus.OK.getReasonPhrase()));
        } catch (NotLegalAgeException e) {
            log.error(Constants.NOT_LEGAL_AGE_ERROR, e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(
                    new ResponseDTO().setMsg(Constants.NOT_LEGAL_AGE_ERROR));
        }
    }
}
