package com.flightinventorysolution.models;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@Table(name = "flights")
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String carrierCode;
    private Integer flightNumber;
    private Date flightDate;
    private String origin;
    private String destination;

}
