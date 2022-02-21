package com.exercise.service.impl;

import com.exercise.dto.PhoneNumberDTO;
import com.exercise.models.Customer;
import com.exercise.service.ICustomerService;
import com.exercise.utils.PhoneNumberValidator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
class PhoneNumberServiceTest {

    private PhoneNumberService phoneNumberService;

    @Mock
    private ICustomerService customerService;
    @Mock
    private PhoneNumberValidator phoneNumberValidator;

    @Mock
    private CountryService countryService;

    // PhoneNumberService(ICustomerService customerService, ICountryService countryService, PhoneNumberValidator phoneNumberValidator)
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        phoneNumberService = new PhoneNumberService(customerService, countryService, phoneNumberValidator);
    }

    @Test
    void itShouldGetAllPhoneNumbers() {
        // Given
        int pageNo = 0;
        int pageSize = 1;
        String sortBy = "id";

        List<Customer> customerList = new ArrayList<Customer>();
        customerList.add(new Customer( 0, "Walid Hammadi", "(212) 6007989253"));
        List<PhoneNumberDTO> phoneNumberDTOList = new ArrayList<>();
        phoneNumberDTOList.add(new PhoneNumberDTO("(212) 6007989253", null));

        given(customerService.getAllCustomers(pageNo, pageSize, sortBy))
                .willReturn(customerList);
        // When
        given(phoneNumberService.getAllPhoneNumbers(pageNo, pageSize, sortBy))
                .willReturn(phoneNumberDTOList);

        // Then
        // assertThat(phoneNumberService.getAllPhoneNumbers(pageNo, pageSize, sortBy).size()).isEqualTo(1);

    }

    @Test
    void itShouldGetAllPhoneNumbersByCountryAndState() {
        // Given
        // When
        // Then
    }
}