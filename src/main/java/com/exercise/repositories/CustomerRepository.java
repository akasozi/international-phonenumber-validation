package com.exercise.repositories;

import com.exercise.models.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer> {

}
