package com.cariq.trainticketing.repository;

import com.cariq.trainticketing.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, String> {
    Optional<Wallet> findById(String id);
}
