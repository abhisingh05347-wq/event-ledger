package com.example.Gateway.Entity;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="events")
public class EventEntity {

    @Id
    private String eventId;

    private String accountId;

    private String type;

    private BigDecimal amount;

    private String currency;

    private Instant eventTimestamp;

	public EventEntity(String eventId, String accountId, String type, BigDecimal amount, String currency,
			Instant eventTimestamp) {
		super();
		this.eventId = eventId;
		this.accountId = accountId;
		this.type = type;
		this.amount = amount;
		this.currency = currency;
		this.eventTimestamp = eventTimestamp;
	}

	public EventEntity() {
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
		return "EventEntity [eventId=" + eventId + ", accountId=" + accountId + ", type=" + type + ", amount=" + amount
				+ ", currency=" + currency + ", eventTimestamp=" + eventTimestamp + "]";
	}
    
	
    

}
