package com.cariq.trainticketing.repository;

import com.cariq.trainticketing.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    List<Ticket> findByWalletId(String walletId);
}
