package com.apirest.customer.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class CustomerConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
        marshaller.setContextPath("com.apirest.customer.wsdl");
        return marshaller;
    }

    @Bean
    public CustomerClient countryClient(Jaxb2Marshaller marshaller) {
        CustomerClient client = new CustomerClient();
        client.setDefaultUri("http://localhost:8085/apisoap-customer/v1/ws/");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
