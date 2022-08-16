package com.cariq.trainticketing.controller.dto;

import javax.validation.constraints.NotBlank;

public class Route {
    @NotBlank
    public String origin;
    @NotBlank
    public String destination;
}