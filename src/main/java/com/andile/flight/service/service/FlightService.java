package com.andile.flight.service.service;

import com.andile.flight.service.dto.FlightBookingAcknowledgement;
import com.andile.flight.service.dto.FlightBookingRequest;
import com.andile.flight.service.model.PassengerInfo;
import com.andile.flight.service.model.PaymentInfo;
import com.andile.flight.service.repository.PassengerInfoRepository;
import com.andile.flight.service.repository.PaymentInfoRepository;
import com.andile.flight.service.utils.PaymentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.UUID;
/**
 *
 **/
@Service
public class FlightService {
    @Autowired
    private PaymentInfoRepository paymentInfoRepository;
    @Autowired
    private PassengerInfoRepository passengerInfoRepository;

    /**
     * This method is used to send a book flight request
     * @param request represents the request that will be sent to the flight booking application
     * @return FlightBookingAcknowledgement object
     **/
    public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request){

        FlightBookingAcknowledgement flightBookingAcknowledgement= null;
        PassengerInfo passengerInfo = request.getPassengerInfo();
        passengerInfo = passengerInfoRepository.save(passengerInfo);

        PaymentInfo paymentInfo = request.getPaymentInfo();
        PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(),passengerInfo.getFare());

        paymentInfo.setPassengerId(passengerInfo.getPId());
        paymentInfo.setAmount(passengerInfo.getFare());
        paymentInfoRepository.save(paymentInfo);
        
        return new FlightBookingAcknowledgement("SUCCESS",passengerInfo.getFare(), UUID.randomUUID().toString().split("-")[0],passengerInfo);
    }
}
