package hcmus.nmq.simplaneservice.converter;

import hcmus.nmq.entities.Passenger;
import hcmus.nmq.model.dtos.PassengerDTO;
import hcmus.nmq.utils.Extensions;
import lombok.experimental.ExtensionMethod;
import org.springframework.stereotype.Component;

/**
 * 11:37 PM 6/20/2022
 * LeHongQuan
 */

@ExtensionMethod(Extensions.class)
@Component
public class PassengerConverter {
    public Passenger fromDTO(PassengerDTO passengerDTO) {
        return Passenger.builder()
                .email(passengerDTO.getEmail())
                .firstName(passengerDTO.getFirstName())
                .identityNumber(passengerDTO.getIdentityNumber())
                .lastName(passengerDTO.getLastName())
                .phoneNumber(passengerDTO.getPhoneNumber())
                .build();
    }

    public PassengerDTO toDTO(Passenger passenger){
        return PassengerDTO.builder()
                .id(passenger.getId())
                .email(passenger.getEmail())
                .firstName(passenger.getFirstName())
                .identityNumber(passenger.getIdentityNumber())
                .lastName(passenger.getLastName())
                .phoneNumber(passenger.getPhoneNumber())
                .build();
    }
}