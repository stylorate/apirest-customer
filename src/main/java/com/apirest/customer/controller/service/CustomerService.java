package com.apirest.customer.controller.service;

import com.apirest.customer.client.CustomerClient;
import com.apirest.customer.constants.Constants;
import com.apirest.customer.controller.CustomerController;
import com.apirest.customer.controller.dto.CustomerDTO;
import com.apirest.customer.exception.NotLegalAgeException;
import com.apirest.customer.wsdl.Customer;
import com.apirest.customer.wsdl.GetCustomerResponse;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Service
public class CustomerService {

    @Autowired
    ModelMapper mapper;

    @Autowired
    CustomerClient client;

    Logger log = (Logger) Logger.getLogger(CustomerService.class);

    public CustomerDTO sendCustomer(CustomerDTO customer) throws NotLegalAgeException {
        log.info("into service");
//        log.info("diff: " + calcularAnios(customer.getBirthDate()));
        customer.setAge(calcularDiff(customer.getBirthDate()));
        customer.setBondingTime(calcularDiff(customer.getBondingDate()));
        calcularDiff(customer.getBirthDate());
        calcularDiff(customer.getBondingDate());
        if (calcularAnios(customer.getBirthDate()) < 18) {
            log.info("Menor de edad");
            throw new NotLegalAgeException(Constants.NOT_LEGAL_AGE_ERROR);
        }
        GetCustomerResponse response = client.getCustomer(mapper.map(customer, Customer.class));
        return mapper.map(response.getCustomer(), CustomerDTO.class)
                .setAge(customer.getAge()).setBondingTime(customer.getBondingTime());
    }

    public int calcularAnios(Date date) {
        String pattern = "yyyy-MM-dd";
        String dateString = new SimpleDateFormat(pattern).format(date);
        int anio = Integer.parseInt(dateString.split("-")[0]);
        int mes = Integer.parseInt(dateString.split("-")[1]);
        int dia = Integer.parseInt(dateString.split("-")[2]);

        Calendar birth = Calendar.getInstance();
        birth.set(anio, mes - 1, dia);

        Calendar current = Calendar.getInstance();

        int diff = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR);

        return (birth.get(Calendar.DAY_OF_YEAR) > current.get(Calendar.DAY_OF_YEAR))
                ? diff-- : diff;
    }

    public String calcularDiff(Date date) {
        String pattern = "yyyy-MM-dd";
        String dateString = new SimpleDateFormat(pattern).format(date);
        int anio = Integer.parseInt(dateString.split("-")[0]);
        int mes = Integer.parseInt(dateString.split("-")[1]);
        int dia = Integer.parseInt(dateString.split("-")[2]);
        log.info("date format " + dateString);

        Calendar birth = Calendar.getInstance();
        birth.set(anio, mes - 1, dia);

        Calendar current = Calendar.getInstance();

        int diffYear = current.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
        diffYear = (birth.get(Calendar.DAY_OF_YEAR) > current.get(Calendar.DAY_OF_YEAR))
                ? diffYear-- : diffYear;

        int diffMonth = current.get(Calendar.MONTH) >= birth.get(Calendar.MONTH)
                ? current.get(Calendar.MONTH) - birth.get(Calendar.MONTH)
                : 12 - birth.get(Calendar.MONTH) + current.get(Calendar.MONTH);

        int diffDays = (current.get(Calendar.DAY_OF_MONTH) > birth.get(Calendar.DAY_OF_MONTH))
                ? current.get(Calendar.DAY_OF_MONTH) - birth.get(Calendar.DAY_OF_MONTH)
                : birth.get(Calendar.DAY_OF_MONTH) - current.get(Calendar.DAY_OF_MONTH);

        String diffYearS = diffYear < 9 ? "0" + diffYear : diffYear + "";
        String diffMonthS = diffMonth < 9 ? "0" + diffMonth : diffMonth + "";
        String diffDaysS = --diffDays < 9 ? "0" + diffDays : diffDays + "";
        return diffYearS + "-" + diffMonthS + "-" + diffDaysS;
    }
}
