package com.cariq.trainticketing.controller;

import com.cariq.trainticketing.controller.dto.LoadMoneyRequest;
import com.cariq.trainticketing.entity.Ticket;
import com.cariq.trainticketing.entity.Wallet;
import com.cariq.trainticketing.controller.dto.WalletTicketView;
import com.cariq.trainticketing.exception.UserWalletNotFoundException;
import com.cariq.trainticketing.service.TicketService;
import com.cariq.trainticketing.service.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("wallets")
public class WalletController {

    @Autowired
    private WalletService walletService;

    @Autowired
    private TicketService ticketService;

//    http://localhost:9191/walletcontroller/wallets/create
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createWallet() {
        return walletService.createWallet();
    }

//    http://localhost:9191/walletcontroller/wallets/{id}
    @RequestMapping(value = "/{walletId}", method = RequestMethod.GET)
    public WalletTicketView getWalletById(@PathVariable @NotEmpty String walletId) throws UserWalletNotFoundException {
        Wallet wallet = walletService.getWalletById(walletId);
        List<Ticket> tickets = ticketService.getTicketsByWalletId(wallet.getId());
        return new WalletTicketView(wallet, tickets);
    }

//    http://localhost:9191/walletcontroller/wallets/{walletid}/loadmoney
    @RequestMapping(value = "/{walletid}/loadmoney", method = RequestMethod.POST)
    public WalletTicketView addBalanceToWallet(@PathVariable @NotEmpty String walletid, @RequestBody @Valid LoadMoneyRequest req) throws UserWalletNotFoundException {
        double amount = Double.parseDouble(req.getAmount());
        Wallet wallet = walletService.addBalance(walletid, amount);
        List<Ticket> tickets = ticketService.getTicketsByWalletId(wallet.getId());
        return new WalletTicketView(wallet, tickets);
    }
}
