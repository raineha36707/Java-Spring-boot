package com.infytel.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.infytel.entity.CustomerEntity;
@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> 
{
	CustomerEntity findByPhoneNo(Long phoneNo);
}
