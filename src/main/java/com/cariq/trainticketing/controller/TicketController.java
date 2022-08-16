package com.cariq.trainticketing.controller;

import com.cariq.trainticketing.client.BartAPI;
import com.cariq.trainticketing.controller.dto.Route;
import com.cariq.trainticketing.controller.dto.RouteFare;
import com.cariq.trainticketing.controller.dto.WalletTicketView;
import com.cariq.trainticketing.entity.Ticket;
import com.cariq.trainticketing.entity.Wallet;
import com.cariq.trainticketing.exception.InsufficientBalanceException;
import com.cariq.trainticketing.exception.InvalidSourceOrDestinationException;
import com.cariq.trainticketing.exception.UserWalletNotFoundException;
import com.cariq.trainticketing.service.TicketService;
import com.cariq.trainticketing.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@RequestMapping("ticketcontroller")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private WalletService walletService;

//    http://localhost:9191/ticketcontroller/wallets/{walletid}/ticket/buy
    @RequestMapping(value = "/wallets/{walletid}/ticket/buy", method = RequestMethod.POST)
    public WalletTicketView buyTicket(@RequestBody @Valid Route route, @PathVariable @NotBlank String walletid) throws UserWalletNotFoundException, InvalidSourceOrDestinationException, InsufficientBalanceException {
        Wallet wallet = walletService.getWalletById(walletid);
        ticketService.buyTicket(route.origin, route.destination, wallet.getId());
        List<Ticket> tickets = ticketService.getTicketsByWalletId(wallet.getId());
        return new WalletTicketView(wallet, tickets);
    }

//    http://localhost:9191/ticketcontroller/ticket/fare?origin=<origin>&destination=<destination>
    @RequestMapping(value = "/ticket/fare", method = RequestMethod.GET)
    public RouteFare getFare(@RequestParam @NotBlank String origin, @RequestParam @NotBlank String destination) throws InvalidSourceOrDestinationException {
        BartAPI bartAPI = new BartAPI();
        double fare = bartAPI.getFare(origin, destination);
        return new RouteFare(origin, destination, fare);
    }
}