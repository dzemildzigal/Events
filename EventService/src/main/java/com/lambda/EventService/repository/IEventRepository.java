package com.lambda.EventService.repository;

import com.lambda.EventService.models.Event;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;

public interface IEventRepository extends CrudRepository<Event,Long> {
    Event findById(long id);
    Event findByEventName(String name);
    Event findByDescription(String desc);
    Event findByLocationId(long id);
    Event findByEventTypeId(long id);
    Event findByCanBuyTicket(boolean cbt);
    Event findByTicketPrice(double price);
    Event findByNumberOfTicketsAvailable(int noOfAvailableTickets);
    Event findByEventPictureURL(String URL);
    Event findByCreatedByUserId(long id);
    Event findByEventStatusId(long id);
    Event findByEventTime(Date time);

}
