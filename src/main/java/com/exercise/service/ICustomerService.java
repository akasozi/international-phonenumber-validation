package com.exercise.service;

import com.exercise.models.Customer;

import java.util.List;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
public interface ICustomerService {

    public List<Customer> getAllCustomers(int pageNo, int pageSize, String sortBy);
}
