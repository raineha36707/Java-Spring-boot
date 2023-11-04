package com.infytel.repository;

import java.util.List;
import java.util.Set;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.infytel.entity.PlanEntity;

@Repository
public interface PlanRepository extends JpaRepository<PlanEntity, Integer> 
{
	
	@Query("SELECT t FROM PlanEntity t WHERE t.localRate IN (?1)")
    public List<PlanEntity> findByLocalRates(Set<Integer> localRates);

}
