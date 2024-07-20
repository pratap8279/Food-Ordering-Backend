package com.rudra.repositatory;

import com.rudra.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepositatory extends JpaRepository<Address ,Integer> {
}
