package com.enset.bankaccountservice;

import com.enset.bankaccountservice.entities.BankAccount;
import com.enset.bankaccountservice.entities.Customer;
import com.enset.bankaccountservice.enums.AccountType;
import com.enset.bankaccountservice.repositories.BankAccountRepository;
import com.enset.bankaccountservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class ActivitePratiqueN1ImplementationUnMicroserviceAvecSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActivitePratiqueN1ImplementationUnMicroserviceAvecSpringbootApplication.class, args);
    }

    @Bean
    CommandLineRunner start(BankAccountRepository bankAccountRepository, CustomerRepository customerRepository){
        return args -> {
            Stream.of("Mohamed", "Yassine", "kawtar", "Sohail")
                    .forEach(c->{
                        Customer customer = Customer.builder()
                                .name(c)
                                .build();
                        customerRepository.save(customer);
                    });

            customerRepository.findAll().forEach(customer -> {
                for(int i = 0; i< 10; i++){
                    BankAccount bankAccount= BankAccount.builder()
                            .id(UUID.randomUUID().toString())
                            .type(Math.random()>0.5? AccountType.CURRENT_ACCOUNT:AccountType.SAVING_ACCOUNT)
                            .createdAt(new Date())
                            .balance(10000+Math.random()*90000)
                            .currency("MAD")
                            .customer(customer)
                            .build();

                    bankAccountRepository.save(bankAccount);
                }
            });

        };
    }
}
