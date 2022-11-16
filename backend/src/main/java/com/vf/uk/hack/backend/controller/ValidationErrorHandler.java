package com.vf.uk.hack.backend.controller;

import com.vf.uk.hack.backend.model.BackendException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class ValidationErrorHandler {

  @ExceptionHandler(BackendException.class)
  public ResponseEntity<ExceptionResponse> handleMockException(final BackendException err) {
    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ExceptionResponse(err.getCode(), err));
  }

  @ExceptionHandler(RuntimeException.class)
  public ResponseEntity<ExceptionResponse> handleRuntimeException(final RuntimeException err) {
    return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ExceptionResponse("INTERNAL_SERVER_ERROR", err));
  }

  public static class ExceptionResponse {
    private final String code;
    private final String message;

    public ExceptionResponse(final String code, final Exception err) {
      this.code = code;
      this.message = err.getMessage();
    }
  }
}