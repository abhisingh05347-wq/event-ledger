package com.example.Gateway.Service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import com.example.Gateway.Client.AccountClient;
import com.example.Gateway.Dto.EventRequest;
import com.example.Gateway.Entity.EventEntity;
import com.example.Gateway.Exception.ServiceUnavailableException;
import com.example.Gateway.Repository.EventRepository;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;

@Service
public class EventService {

	private static final Logger log =LoggerFactory.getLogger(EventService.class);
	
	private final EventRepository eventRepository;
    private final AccountClient accountClient;
    private final Counter eventCounter;

    public EventService(
            EventRepository eventRepository,
            AccountClient accountClient,
            MeterRegistry meterRegistry) {

        this.eventRepository = eventRepository;
        this.accountClient = accountClient;

        this.eventCounter = Counter.builder("event.submitted") 
        		.description("Total submitted events") 
        		.register(meterRegistry);
    }
    
    

    @CircuitBreaker(
            name = "accountService",
            fallbackMethod = "fallback")
    public EventEntity createEvent(
            EventRequest request) {

        Optional<EventEntity> existingEvent =
                eventRepository.findById(
                        request.getEventId());

        if (existingEvent.isPresent()) {
            return existingEvent.get();
        }

        EventEntity entity = new EventEntity();

        entity.setEventId(request.getEventId());
        entity.setAccountId(request.getAccountId());
        entity.setType(request.getType());
        entity.setAmount(request.getAmount());
        entity.setCurrency(request.getCurrency());
        entity.setEventTimestamp(request.getEventTimestamp());

        EventEntity savedEvent = eventRepository.save(entity);
     // Custom Metric 
        eventCounter.increment();

        // Call Account Service
        accountClient.applyTransaction(
                request.getAccountId(),
                request);
        log.info("Event saved successfully. eventId={}",request.getEventId());
        return savedEvent;
    }

    public EventEntity fallback( EventRequest request, Exception ex) { 
    	String traceId = MDC.get("traceId"); 
    	log.error( "Account Service unavailable. traceId={}, error={}", traceId, ex.getMessage()); 
    	throw new ServiceUnavailableException( "Account Service Down"); }
    
    
    public EventEntity getEvent(String eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new RuntimeException("Event not found"));
    }
    
    public List<EventEntity>
    getEventsByAccount(String accountId) {
        return eventRepository.findByAccountIdOrderByEventTimestampAsc(accountId);
    }
}
