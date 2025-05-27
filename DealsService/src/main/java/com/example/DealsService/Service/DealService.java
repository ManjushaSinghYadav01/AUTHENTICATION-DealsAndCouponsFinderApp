package com.example.DealsService.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DealsService.Exception.DealNotFound;
//import com.example.DealsService.FeignClients.TransactionClient;
import com.example.DealsService.Model.Deal;
import com.example.DealsService.Repository.DealRepository;

@Service
public class DealService {
	@Autowired
		DealRepository repository;
	
    public Deal createDeal(Deal deal) {
        return repository.save(deal);
    }
    
    public List<Deal> createDeals(List<Deal> deals) {
		return repository.saveAll(deals);
	}
	
    public List<Deal> getAllDeals() {
        return repository.findAll();
    }
    
    public List<Deal> getActiveDealsByCategory(String category) {
        return repository.findByCategoryAndActive(category, true);
    }
   
    public Optional<Deal> getDealById(Long id) {
        return repository.findById(id);
    }
   
    public Deal updateDeal(Long id, Deal deal) {
    Deal existing = repository.findById(id).orElseThrow(() -> new DealNotFound("Deal with ID " + id + " not found"));

        existing.setName(deal.getName());
        existing.setCategory(deal.getCategory());
        existing.setPrice(deal.getPrice());
        existing.setDiscount(deal.getDiscount());
        existing.setActive(deal.isActive());
        existing.setExpiryDate(deal.getExpiryDate());
        
        return repository.save(existing);
  
}
    
    public void deleteDeal(Long id) {
        Deal deal = repository.findById(id)
            .orElseThrow(() -> new DealNotFound("Deal with ID " + id + " not found"));
        
        if (LocalDate.now().isBefore(deal.getExpiryDate())) {
            throw new IllegalStateException("Deal cannot be deleted before expiry date.");
        }
        
        repository.deleteById(id);
    }



	
}
