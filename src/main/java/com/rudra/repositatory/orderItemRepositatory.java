package com.rudra.repositatory;

import com.rudra.model.orderIteam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderItemRepositatory extends JpaRepository<orderIteam,Integer> {
}
