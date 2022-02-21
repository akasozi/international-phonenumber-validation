package com.exercise.api;

import com.exercise.dto.PhoneNumberDTO;
import com.exercise.service.IPhoneNumberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
@CrossOrigin
@RestController
public class PhoneNumberController {
    private static final Logger log = LoggerFactory.getLogger(PhoneNumberController.class);

    private final IPhoneNumberService phoneNumberService;

    @Autowired
    public PhoneNumberController(IPhoneNumberService phoneNumberService) {
        this.phoneNumberService = phoneNumberService;
    }

    @ResponseStatus(value = HttpStatus.OK)
    @GetMapping("/api/v1/phone-numbers")
    public List<PhoneNumberDTO> getAllPhoneNumbers(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "") String country,
            @RequestParam(defaultValue = "VALID") String state) {

        log.info("getAllPhoneNumbers() | pageNo: {}, pageSize: {}, sortBy: {}, country: {}, state: {}", pageNo, pageSize, sortBy, country, state);
        if (country.equalsIgnoreCase("") || country == null) {
            log.info("getAllPhoneNumbers() | country: '{}' : calling method phoneNumberService.getAllPhoneNumbers()",country);
            return phoneNumberService.getAllPhoneNumbers(pageNo, pageSize, sortBy);
        } else {
            log.info("getAllPhoneNumbers() | country: '{}' : calling method phoneNumberService.getAllPhoneNumbersByCountryAndState()",country);
            return phoneNumberService.getAllPhoneNumbersByCountryAndState(pageNo, pageSize, sortBy, country, state);
        }
    }

}
