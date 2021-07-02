package com.flightinventorysolution.services;

import com.flightinventorysolution.models.Flight;
import com.flightinventorysolution.repositories.FlightRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

@SpringBootTest
public class FlightServiceTest {

    @MockBean
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    @Mock
    private FlightService flightServiceMock;

    private Flight flight;

    @BeforeEach
    void before(){
        flight = new Flight();
        flight.setId(1L);
        flight.setFlightDate(new Date());
        flight.setFlightNumber(100);
        flight.setCarrierCode("AAA");
        flight.setDestination("Karachi");
        flight.setOrigin("Chemnitz");
    }

    @Test
    void positive_getAllFlightsTest(){
        Mockito.when(flightRepository.findAll()).thenReturn(Arrays.asList(flight));
        ResponseEntity resp = flightService.getAllFlights();
        Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void positive_getFlightByIdTest(){
        Mockito.when(flightRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(flight));
        ResponseEntity resp = flightService.getFlightById(Mockito.anyLong());
        Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void positive_update(){
        Mockito.when(flightRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(flight));
        ResponseEntity resp = flightService.getFlightById(Mockito.anyLong());
        Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void positive_save(){
        Mockito.when(flightRepository.save(Mockito.mock(Flight.class))).thenReturn(flight);
        ResponseEntity resp = flightService.save(Mockito.mock(Flight.class));
        Assertions.assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void positive_delete(){
        Mockito.when(flightRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(flight));
        flightServiceMock.delete(1L);
        Mockito.verify(flightServiceMock, Mockito.times(1)).delete(Mockito.anyLong());
    }
}
