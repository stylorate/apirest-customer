package com.apirest.customer.controller.service;

import com.apirest.customer.client.CustomerClient;
import com.apirest.customer.constants.Constants;
import com.apirest.customer.controller.dto.CustomerDTO;
import com.apirest.customer.exception.NotLegalAgeException;
import com.apirest.customer.tools.Utils;
import com.apirest.customer.wsdl.Customer;
import com.apirest.customer.wsdl.GetCustomerListResponse;
import com.apirest.customer.wsdl.GetCustomerResponse;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomerService extends BaseService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    CustomerClient client;

    Logger log = (Logger) Logger.getLogger(CustomerService.class);

    public CustomerDTO sendCustomer(CustomerDTO customer) throws NotLegalAgeException {
        log.info("into service");
        customer.setAge(Utils.calculatorDiff(customer.getBirthDate()));
        customer.setBondingTime(Utils.calculatorDiff(customer.getBondingDate()));
        Utils.calculatorDiff(customer.getBirthDate());
        Utils.calculatorDiff(customer.getBondingDate());
        if (Utils.calculatorAnios(customer.getBirthDate()) < 18) {
            log.info("Menor de edad");
            throw new NotLegalAgeException(Constants.NOT_LEGAL_AGE_ERROR);
        }
        GetCustomerResponse response = client.getCustomer(mapper.map(customer, Customer.class));
        return mapper.map(response.getCustomer(), CustomerDTO.class)
                .setAge(customer.getAge()).setBondingTime(customer.getBondingTime());
    }

    public ArrayList<CustomerDTO> getList() {
        return (ArrayList<CustomerDTO>) mapList((ArrayList<Customer>)
                client.getList().getCustomer().getListCustomer(), CustomerDTO.class);
    }
}
