package com.apirest.customer.controller.dto;

import com.apirest.customer.wsdl.Customer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConstructorBinding;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
@ToString
@ConstructorBinding
public class CustomerDTO {

    private int idCustomer;
    @NotEmpty
    @Size(min = 3, max = 10)
    private String name;
    @NotEmpty
    @Size(min = 3, max = 10)
    private String lastName;
    @NotEmpty
    @Size(min = 2, max = 2)
    private String documentType;
    @NotEmpty
    @Size(min = 7, max = 10)
    private String documentNumber;
//    @NotEmpty
    private Date birthDate;
//    @NotEmpty
    private Date bondingDate;
    @NotEmpty
    @Size(min = 3, max = 10)
    private String position;
//    @NotEmpty
    private Double salary;

    private String age;

    private String bondingTime;

    public CustomerDTO setAge(String age){
        this.age = age;
        return this;
    }

    public CustomerDTO setBondingTime(String age){
        this.bondingTime = age;
        return this;
    }
}
