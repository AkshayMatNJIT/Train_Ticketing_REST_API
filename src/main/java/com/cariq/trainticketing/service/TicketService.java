package com.cariq.trainticketing.service;

import com.cariq.trainticketing.client.BartAPI;
import com.cariq.trainticketing.client.BartAPI_new;
import com.cariq.trainticketing.entity.Ticket;
import com.cariq.trainticketing.entity.Wallet;
import com.cariq.trainticketing.exception.InsufficientBalanceException;
import com.cariq.trainticketing.exception.InvalidSourceOrDestinationException;
import com.cariq.trainticketing.repository.TicketRepository;
import com.cariq.trainticketing.repository.WalletRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private BartAPI_new bartAPI;

    public List<Ticket> getTicketsByWalletId(String walletID) {
        return ticketRepository.findByWalletId(walletID);
    }

    public Ticket buyTicket(String source, String destination, String walletId) throws InvalidSourceOrDestinationException, InsufficientBalanceException {
        double ticketFare = bartAPI.getFare(source, destination);
        Ticket ticket = new Ticket();
        ticket.setOrigin(source);
        ticket.setDestination(destination);
        ticket.setFare(ticketFare);

        // get wallet and update balance and ticket purchased
        Wallet wallet = walletRepository.findById(walletId).orElse(null);
        double newBalance = wallet.getBalance() - ticketFare;
        if (newBalance < 0.00) {
            throw new InsufficientBalanceException("Insufficient balance, please load money to your wallet");
        }
        else {
            wallet.setBalance(newBalance);
            walletRepository.save(wallet);

            // get new wallet and update tickets
            wallet = walletRepository.findById(walletId).orElse(null);
            ticket.setWallet(wallet);
            return ticketRepository.save(ticket);
        }
    }
}