package com.cariq.trainticketing.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteFare {
    public String origin;
    public String destination;
    public double fare;
}
