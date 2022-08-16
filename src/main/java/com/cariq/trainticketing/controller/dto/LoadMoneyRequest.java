package com.cariq.trainticketing.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoadMoneyRequest {

@DecimalMin("0.01")
@Pattern(regexp = "\\d{0,4}\\.\\d{0,2}", message = "must be a decimal value")
public String amount;
}
