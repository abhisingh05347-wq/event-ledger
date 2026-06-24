package com.example.Gateway.Dto;

import java.math.BigDecimal;
import java.time.Instant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;


public class EventRequest {
	
	@NotBlank
    private String eventId;

    @NotBlank
    private String accountId;

    @NotBlank
    private String type;

    @Positive
    private BigDecimal amount;

    @NotBlank
    private String currency;

    private Instant eventTimestamp;

	public EventRequest(@NotBlank String eventId, @NotBlank String accountId, @NotBlank String type,
			@Positive BigDecimal amount, @NotBlank String currency, Instant eventTimestamp) {
		super();
		this.eventId = eventId;
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
		this.currency = currency;
		this.eventTimestamp = eventTimestamp;
	}

	public EventRequest() {
		super();
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public Instant getEventTimestamp() {
		return eventTimestamp;
	}

	public void setEventTimestamp(Instant eventTimestamp) {
		this.eventTimestamp = eventTimestamp;
	}

	@Override
	public String toString() {
		return "EventRequest [eventId=" + eventId + ", accountId=" + accountId + ", type=" + type + ", amount=" + amount
				+ ", currency=" + currency + ", eventTimestamp=" + eventTimestamp + "]";
	}
    
    

}
