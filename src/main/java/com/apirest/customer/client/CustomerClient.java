package com.apirest.customer.client;

import com.apirest.customer.wsdl.Customer;
import com.apirest.customer.wsdl.GetCustomerRequest;
import com.apirest.customer.wsdl.GetCustomerResponse;
import org.apache.log4j.Logger;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;


public class CustomerClient extends WebServiceGatewaySupport {

    private static final Logger log = Logger.getLogger(CustomerClient.class);

    public GetCustomerResponse getCustomer(Customer customer) {
        GetCustomerRequest request = new GetCustomerRequest();
        request.setCustomer(customer);

        log.info("Requesting location for " + customer);
        log.info("date: " + customer.getBondingDate());
        GetCustomerResponse response = (GetCustomerResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/apisoap-customer/v1/ws", request,
                        new SoapActionCallback(
                                "soap-web-service/GetCountryRequest"));
        return response;
    }
}
