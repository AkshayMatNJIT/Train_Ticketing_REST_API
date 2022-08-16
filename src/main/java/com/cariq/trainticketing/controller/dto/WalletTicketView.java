package com.cariq.trainticketing.controller.dto;

import com.cariq.trainticketing.entity.Ticket;
import com.cariq.trainticketing.entity.Wallet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletTicketView {
    private Wallet wallet;
    private List<Ticket> tickets;
}
