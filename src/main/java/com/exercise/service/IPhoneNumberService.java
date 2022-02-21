package com.exercise.service;

import com.exercise.dto.PhoneNumberDTO;

import java.util.List;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
public interface IPhoneNumberService {

    public List<PhoneNumberDTO> getAllPhoneNumbers(int pageNo, int pageSize, String sortBy);

    public List<PhoneNumberDTO> getAllPhoneNumbersByCountryAndState(int pageNo, int pageSize, String sortBy, String country, String state);

}
