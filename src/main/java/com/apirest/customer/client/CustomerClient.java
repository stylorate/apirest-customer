package com.apirest.customer.client;

import com.apirest.customer.wsdl.*;
import org.apache.log4j.Logger;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


public class CustomerClient extends WebServiceGatewaySupport {

    private static final Logger log = Logger.getLogger(CustomerClient.class);

    public GetCustomerResponse getCustomer(Customer customer) {
        GetCustomerRequest request = new GetCustomerRequest();
        request.setCustomer(customer);

        log.info("Requesting location for " + customer);
        log.info("date: " + customer.getBondingDate());
        GetCustomerResponse response = (GetCustomerResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/apisoap-customer/v1/ws", request,
                        new SoapActionCallback("soap-web-service/GetCustomerRequest"));
        return response;
    }


    public GetCustomerListResponse getList() {
        log.info("Requesting location getList");
        GetCustomerListRequest request = new GetCustomerListRequest();
        GetCustomerListResponse response = (GetCustomerListResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8085/apisoap-customer/v1/ws", request,
                        new SoapActionCallback("soap-web-service/GetCustomerListRequest"));
        return response;
    }
}
