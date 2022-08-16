package com.cariq.trainticketing;

import com.cariq.trainticketing.controller.TicketController;
import com.cariq.trainticketing.controller.WalletController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class SmokeTest {

    @Autowired
    private WalletController walletController;
    @Autowired
    private TicketController ticketController;

    @Test
    public void walletControllerLoads() throws Exception{
        assertThat(walletController).isNotNull();
    }

    @Test
    public void ticketControllerLoads() throws Exception{
        assertThat(ticketController).isNotNull();
    }
}
