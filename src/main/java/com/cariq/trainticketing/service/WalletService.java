package com.cariq.trainticketing.service;

import com.cariq.trainticketing.entity.Wallet;
import com.cariq.trainticketing.exception.UserWalletNotFoundException;
import com.cariq.trainticketing.repository.TicketRepository;
import com.cariq.trainticketing.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WalletService {

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public String createWallet() {
        Wallet wallet = new Wallet(UUID.randomUUID().toString(), 0.00);
        Wallet _wall = saveWallet(wallet);
        return _wall.getId();
    }

    public Wallet addBalance(String id, double amount) throws UserWalletNotFoundException {
        Wallet wallet = walletRepository.findById(id).orElse(null);
        if (wallet != null) {
            wallet.setBalance(wallet.getBalance() + amount);
            saveWallet(wallet);
            return wallet;
        }
        else throw new UserWalletNotFoundException("Wallet not found with id: "+id);
    }

    public Wallet saveWallet(Wallet wallet) {
        return walletRepository.save(wallet);
    }

    public Wallet getWalletById(String id) throws UserWalletNotFoundException{
        Wallet wallet = walletRepository.findById(id).orElse(null);
        if (wallet != null) return wallet;
        else throw new UserWalletNotFoundException("Wallet not found with id: "+id);
    }
}