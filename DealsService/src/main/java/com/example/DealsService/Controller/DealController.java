package com.example.DealsService.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import com.example.DealsService.Exception.DealNotFound;
import com.example.DealsService.Model.Deal;
import com.example.DealsService.Service.DealService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deals")
public class DealController {

    @Autowired
    private DealService service;

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/createDeal")
    public ResponseEntity<Deal> createDeal(@RequestBody Deal deal) {
        Deal createdDeal = service.createDeal(deal);
        return new ResponseEntity<>(createdDeal, HttpStatus.CREATED);
    }

//    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/createDeals")
    public ResponseEntity<List<Deal>> createDeals(@RequestBody List<Deal> deals) {
        List<Deal> createdDeals = service.createDeals(deals);
        return new ResponseEntity<>(createdDeals, HttpStatus.CREATED);
    }

    
    @GetMapping("/all")
    public ResponseEntity<List<Deal>> getAllDeals() {
        List<Deal> deals = service.getAllDeals();
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }

   
    @GetMapping("/id/{id}")
    public ResponseEntity<Deal> getDealById(@PathVariable Long id) {
       Deal deal = service.getDealById(id).orElseThrow(()->new DealNotFound("Deal with ID " + id + " not found"));
        return new ResponseEntity<>(deal, HttpStatus.OK);
    }

   
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Deal>> getActiveDealsByCategory(@PathVariable String category) {
        List<Deal> deals = service.getActiveDealsByCategory(category);
        return new ResponseEntity<>(deals, HttpStatus.OK);
    }

    
    @PutMapping("/update/{id}")
    public ResponseEntity<Deal> updateDeal(@PathVariable Long id, @RequestBody Deal deal) {
        Deal updatedDeal = service.updateDeal(id, deal);
        return new ResponseEntity<>(updatedDeal, HttpStatus.OK);
    }

    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDeal(@PathVariable Long id) {
        service.deleteDeal(id);
        return new ResponseEntity<>("Deal deleted successfully.", HttpStatus.OK);
    }
}
