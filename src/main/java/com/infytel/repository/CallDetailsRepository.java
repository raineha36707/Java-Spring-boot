package com.infytel.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.infytel.entity.CallDetailsEntity;
@Repository
public interface CallDetailsRepository extends JpaRepository<CallDetailsEntity, Long> 
{
	@Query("SELECT t FROM CallDetailsEntity t WHERE t.calledBy=?1 AND t.calledOn=?2")
	List<CallDetailsEntity> fetchCallDetails(long calledBy, LocalDate calledOn);

}
