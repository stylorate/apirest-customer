package com.apirest.customer.config;

import com.apirest.customer.controller.dto.CustomerDTO;
//import com.apirest.customer.persistence.entity.Customer;
import com.apirest.customer.wsdl.Customer;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.xml.datatype.XMLGregorianCalendar;
import java.util.Date;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(CustomerDTO.class, Customer.class);
        modelMapper.addMappings(new PropertyMap<CustomerDTO, Customer>() {
            @Override
            protected void configure() {
                skip(destination.getIdCustomer());
            }
        });

        modelMapper.getConfiguration().setAmbiguityIgnored(true).setMatchingStrategy(MatchingStrategies.STRICT);
        return modelMapper;
    }
}
