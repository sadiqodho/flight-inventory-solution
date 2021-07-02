package com.flightinventorysolution.services;

import com.flightinventorysolution.models.Flight;
import com.flightinventorysolution.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public ResponseEntity<?> getAllFlights(){
        return new ResponseEntity<>(flightRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> getFlightById(Long id){
        return new ResponseEntity<>(flightRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> update(Long id, Flight flight){
        Flight flightResp = flightRepository.findById(id).orElse(null);
        if(flightResp == null){
            return new ResponseEntity<>(Map.of("message", "Record Not found!"), HttpStatus.NOT_FOUND);
        }
        flightResp.setFlightDate(flight.getFlightDate());
        flightResp.setFlightNumber(flight.getFlightNumber());
        flightResp.setCarrierCode(flight.getCarrierCode());
        flightResp.setDestination(flight.getDestination());
        flightResp.setOrigin(flight.getOrigin());
        return new ResponseEntity<>(flightRepository.save(flightResp), HttpStatus.OK);
    }

    public ResponseEntity<?> save(Flight flight){
        return new ResponseEntity<>(flightRepository.save(flight), HttpStatus.OK);
    }

    public ResponseEntity<?> delete(Long id){
        Flight flight = flightRepository.findById(id).orElse(null);
        if(flight == null){
            return new ResponseEntity<>(Map.of("message", "Record Not found!"), HttpStatus.NOT_FOUND);
        }
        flightRepository.delete(flight);
        return new ResponseEntity<>(Map.of("message", "Record deleted successfully!"), HttpStatus.OK);
    }
}
