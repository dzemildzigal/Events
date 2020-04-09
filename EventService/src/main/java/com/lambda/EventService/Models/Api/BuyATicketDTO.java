package com.lambda.EventService.Models.Api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class BuyATicketDTO {
    private Long eventId;
    private Long userId;
    private Long numberOfTickets;
}
