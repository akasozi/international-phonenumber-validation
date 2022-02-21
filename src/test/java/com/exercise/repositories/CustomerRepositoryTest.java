package com.exercise.repositories;

import com.exercise.models.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Abu Bizibu
 * @created
 * @project
 */
@DataJpaTest(
        properties = {
                "spring.jpa.properties.javax.persistence.validation.mode=none"
        }
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    @DisplayName("It should get all customers")
    void itShouldSelectAllCustomers() {
        // Given
        int pageNo = 0;
        int pageSize = 5;
        String sortBy = "id";
        // Then
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Customer> pagedResult = customerRepository.findAll(paging);
        // A resultset was found
        assertThat(pagedResult.hasContent()).isTrue();
        assertThat(pagedResult.getNumberOfElements()).isEqualTo(5);
    }
}