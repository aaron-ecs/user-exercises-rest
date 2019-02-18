package com.williams.userexercisesrest.controller;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

public @Data class ErrorResponse {
    private @Getter @Setter String error;
}
