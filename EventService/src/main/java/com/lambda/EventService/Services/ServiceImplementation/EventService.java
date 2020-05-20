package com.lambda.EventService.Services.ServiceImplementation;

import com.lambda.EventService.ExceptionHandling.CustomEventException;
import com.lambda.EventService.Models.Api.EventFilterDTO;
import com.lambda.EventService.Models.Entity.EventType;
import com.lambda.EventService.Services.IEventService;
import com.lambda.EventService.Models.Entity.Event;
import com.lambda.EventService.Repositories.IEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class EventService implements IEventService {

    @Autowired
    IEventRepository eventRepository;
    @Override
    public Event createEvent(Event info) throws CustomEventException {
        if(info.getLocation() == null || info.getEventType()==null || info.getCanBuyTicket() == null || info == null)
            throw new CustomEventException("400: Event is null or one or more attributes of class Event are null!");
        var eventRepositoryTemp = eventRepository.save(info);
        return info;
    }

    @Override
    public Event findById(Long id) throws CustomEventException {
        long idd= id;
        var result = eventRepository.findById(idd);
        if(result != null) return result;
        throw new CustomEventException("404: Event with ID="+id.toString()+" not found in the database!");
    }

    @Override
    public Event updateEventStatus(Event info)throws CustomEventException{
        if(info.getEventId() == null || info.getLocation() == null || info.getEventType()==null || info.getCanBuyTicket() == null || info == null)
        throw new CustomEventException("400: Event is null or one or more attributes of class Event are null!");
        return eventRepository.save(info);
    }
    @Override
    public List<Event> findAll(EventFilterDTO eventFilterDTO) throws CustomEventException{
        return eventRepository.findAll(new Specification<Event>() {
            @Override
            public Predicate toPredicate(Root<Event> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (eventFilterDTO != null) {
                    if (eventFilterDTO.eventNameField != null && eventFilterDTO.eventNameField.trim().length() != 0 )
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("eventName")),"%"+eventFilterDTO.eventNameField.toUpperCase()+"%"));
                    if (eventFilterDTO.eventTypeDropDownMenuItem != null && eventFilterDTO.eventTypeDropDownMenuItem.trim().length() != 0)
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("eventType").get("eventTypeDescription")),"%"+eventFilterDTO.eventTypeDropDownMenuItem.toUpperCase()+"%"));
                    if (eventFilterDTO.locationNameField != null && eventFilterDTO.locationNameField.trim().length() != 0)
                    predicates.add(criteriaBuilder.like(criteriaBuilder.upper(root.get("location").get("description")),"%"+eventFilterDTO.locationNameField.toUpperCase()+"%"));
                    if (eventFilterDTO.eventDate != null)
                    predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("eventTime"),eventFilterDTO.eventDate));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        });
    }


}
