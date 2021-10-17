package com.andile.flight.service.controller;

import com.andile.flight.service.dto.FlightBookingAcknowledgement;
import com.andile.flight.service.dto.FlightBookingRequest;
import com.andile.flight.service.service.FlightService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class FlightController {

    @Autowired
    private FlightService flightService;

    /**
     * This end-point is used to request a booking flight
     * @param request represents a request object that will be sent
     * @return FlightBookingAcknowledgement object
     **/
    @PostMapping("/bookFlightTicket")
    public FlightBookingAcknowledgement bookFlightTicket(@RequestBody FlightBookingRequest request){
       log.info("request :",request);
        return flightService.bookFlightTicket(request);
    }
}
