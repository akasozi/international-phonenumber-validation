package com.exercise.service.impl;

import com.exercise.common.PhoneNumberStatus;
import com.exercise.dto.PhoneNumberDTO;
import com.exercise.models.Country;
import com.exercise.models.Customer;
import com.exercise.service.ICountryService;
import com.exercise.service.ICustomerService;
import com.exercise.service.IPhoneNumberService;
import com.exercise.utils.PhoneNumberValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
@Service
public class PhoneNumberService implements IPhoneNumberService {

    private static final Logger log = LoggerFactory.getLogger(PhoneNumberService.class);
    private final ICustomerService customerService;
    private final ICountryService countryService;
    private final PhoneNumberValidator phoneNumberValidator;

    @Autowired
    public PhoneNumberService(ICustomerService customerService, ICountryService countryService, PhoneNumberValidator phoneNumberValidator) {
        this.customerService = customerService;
        this.countryService = countryService;
        this.phoneNumberValidator = phoneNumberValidator;
    }

    @Override
    public List<PhoneNumberDTO> getAllPhoneNumbers(int pageNo, int pageSize, String sortBy) {
        List<Customer> customersList = customerService.getAllCustomers(pageNo, pageSize, sortBy);
        return customersList.stream().map(obj -> {
            PhoneNumberDTO phoneNumberObj = new PhoneNumberDTO();
            phoneNumberObj.setPhoneNumber(obj.getPhone());
            // phoneNumberObj.setStatus(PhoneNumberStatus.VALID);
            return phoneNumberObj;
        }).collect(Collectors.toList());
    }

    @Override
    public List<PhoneNumberDTO> getAllPhoneNumbersByCountryAndState(int pageNo, int pageSize, String sortBy, String country, String state) {

        List<PhoneNumberDTO> phoneNumbersList = new ArrayList<>();
        Optional<Country> optionalCountry = countryService.findByName(country);
        if (!optionalCountry.isPresent()) {
            log.info("getAllPhoneNumbersByCountryAndState() | contry: {} is missing!", country );
            return phoneNumbersList;
        }
        if (!state.equalsIgnoreCase("VALID") && !state.equalsIgnoreCase("NOT_VALID")) {
            log.info("getAllPhoneNumbersByCountryAndState() | state: {} not valid!", state);
            return phoneNumbersList;
        }
        // Retrieve the regex, country code associated with the countryName
        Country countryObj = optionalCountry.get();
        // Retrieve all phoneNumbers as per the pagination parameters
        List<Customer> customersList = customerService.getAllCustomers(pageNo, pageSize, sortBy);
        // Filter based on country and state
        if (customersList.size() > 0) {
             for (Customer customer: customersList) {
                 boolean isValidPhoneNumberCountryCode = phoneNumberValidator.isPhoneNumberCountryCodeValid(customer.getPhone(), countryObj.getCode());
                 if (isValidPhoneNumberCountryCode) {
                     boolean isValidPhoneNumber = phoneNumberValidator.isPhoneNumberValid(customer.getPhone().trim(), countryObj.getRegex().trim());
                     if (state.equalsIgnoreCase("VALID")) {
                         if (isValidPhoneNumber) {
                             PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();
                             phoneNumberDTO.setStatus(PhoneNumberStatus.VALID);
                             phoneNumberDTO.setPhoneNumber(customer.getPhone());
                             phoneNumbersList.add(phoneNumberDTO);
                         }
                     } else if (state.equalsIgnoreCase("NOT_VALID")) {
                         if (!isValidPhoneNumber) {
                             PhoneNumberDTO phoneNumberDTO = new PhoneNumberDTO();
                             phoneNumberDTO.setStatus(PhoneNumberStatus.NOT_VALID);
                             phoneNumberDTO.setPhoneNumber(customer.getPhone());
                             phoneNumbersList.add(phoneNumberDTO);
                         }
                     }
                 }
             }
             return phoneNumbersList;
        }
        return phoneNumbersList;
    }
}
