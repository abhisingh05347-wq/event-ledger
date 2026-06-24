package com.example.Gateway.ServiceTest;


import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.Gateway.Client.AccountClient;
import com.example.Gateway.Dto.EventRequest;
import com.example.Gateway.Entity.EventEntity;
import com.example.Gateway.Repository.EventRepository;
import com.example.Gateway.Service.EventService;


@ExtendWith(MockitoExtension.class)
class EventServiceTest {

@Mock
private EventRepository eventRepository;

@Mock
private AccountClient accountClient;

@InjectMocks
private EventService eventService;

private EventRequest request;

@BeforeEach
void setup() {

    request = new EventRequest();

    request.setEventId("evt-001");
    request.setAccountId("acc-001");
    request.setType("CREDIT");
    request.setAmount(
            BigDecimal.valueOf(100));

    request.setCurrency("USD");

    request.setEventTimestamp(
            Instant.now());
}

@Test
void createEventSuccess() {

    when(eventRepository
            .findById("evt-001"))
            .thenReturn(Optional.empty());

    EventEntity entity =
            new EventEntity();

    entity.setEventId("evt-001");

    when(eventRepository
            .save(any(EventEntity.class)))
            .thenReturn(entity);

    assertDoesNotThrow(() ->
            eventService.createEvent(
                    request));

    verify(eventRepository)
            .save(any(EventEntity.class));
}

@Test
void duplicateEventShouldReturnExistingEvent() {

    EventEntity existing =
            new EventEntity();

    existing.setEventId("evt-001");

    when(eventRepository
            .findById("evt-001"))
            .thenReturn(
                    Optional.of(existing));

    EventEntity result =
            eventService.createEvent(
                    request);

    assertEquals(
            "evt-001",
            result.getEventId());
}

@Test
void getEventSuccess() {

    EventEntity entity =
            new EventEntity();

    entity.setEventId("evt-001");

    when(eventRepository
            .findById("evt-001"))
            .thenReturn(
                    Optional.of(entity));

    EventEntity result =
            eventService.getEvent(
                    "evt-001");

    assertEquals(
            "evt-001",
            result.getEventId());
}

}
