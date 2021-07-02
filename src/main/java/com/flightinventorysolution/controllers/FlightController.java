package com.flightinventorysolution.controllers;

import com.flightinventorysolution.models.Flight;
import com.flightinventorysolution.services.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    /**
     * @return
     */
    @GetMapping
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getAllFlights(){
        return flightService.getAllFlights();
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<?> getFlightById(@PathVariable("id") Long id){
        return flightService.getFlightById(id);
    }

    /**
     * @param id
     * @param flight
     * @return
     */
    @PutMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<?> updateFlight(@PathVariable("id") Long id, @RequestBody Flight flight){
        return flightService.update(id, flight);
    }

    /**
     * @param flight
     * @return
     */
    @PostMapping
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<?> saveFlight(@RequestBody Flight flight){
        return flightService.save(flight);
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    @RolesAllowed({"ROLE_ADMIN"})
    public ResponseEntity<?> deleteFlight(@PathVariable("id") Long id){
        return flightService.delete(id);
    }
}
