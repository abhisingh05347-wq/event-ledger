package com.example.Gateway.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Gateway.Entity.EventEntity;

public interface EventRepository extends JpaRepository<EventEntity,String>{

List<EventEntity>findByAccountIdOrderByEventTimestampAsc(String accountId);
	
	
	

}
