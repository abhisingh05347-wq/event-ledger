package com.example.Gateway.Controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Gateway.Dto.EventRequest;
import com.example.Gateway.Entity.EventEntity;
import com.example.Gateway.Service.EventService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/events")
@AllArgsConstructor
public class EventController {

	private final EventService eventService;

	 @PostMapping
	    public ResponseEntity<EventEntity>
	    createEvent( @Valid @RequestBody EventRequest request) {

	        EventEntity response = eventService.createEvent(request);
	        return ResponseEntity
	                .status(HttpStatus.CREATED)
	                .body(response);
	    }
	
	 
	 @GetMapping("/{eventId}")
	    public ResponseEntity<EventEntity>
	    getEvent( @PathVariable String eventId) {
	        return ResponseEntity.ok(
	                eventService.getEvent(
	                        eventId));
	    }

	    @GetMapping
	    public ResponseEntity<List<EventEntity>>
	    getEventsByAccount( @RequestParam("account") String accountId) {
	        return ResponseEntity.ok(
	                eventService.getEventsByAccount(
	                        accountId));
	    }
	 
}
