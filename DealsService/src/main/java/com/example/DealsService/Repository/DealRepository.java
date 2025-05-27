package com.example.DealsService.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.DealsService.Model.Deal;


@Repository
public interface DealRepository  extends JpaRepository<Deal, Long>{

	List<Deal> findByCategoryAndActive(String category, boolean b);


	 
}
