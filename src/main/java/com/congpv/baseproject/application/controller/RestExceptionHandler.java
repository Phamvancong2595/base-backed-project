package com.congpv.baseproject.application.controller;

import com.congpv.baseproject.application.response.BaseResponse;
import com.congpv.baseproject.application.response.FindProductResponse;
import com.congpv.baseproject.application.response.PostResponse;
import com.congpv.baseproject.application.response.PriceCheckResponse;
import com.congpv.baseproject.infrastructure.exception.EmptyRequestException;
import com.congpv.baseproject.infrastructure.exception.PostNotFoundException;
import com.congpv.baseproject.infrastructure.exception.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

  private static final String MSG_PRODUCT_NOT_FOUND = "Your product is not found";
  private static final String MSG_EMPTY_REQUEST = "Your request body is empty";
  private static final String MSG_POST_NOT_FOUND = "No posts found";

  @ExceptionHandler(value = ProductNotFoundException.class)
  public ResponseEntity<FindProductResponse> handleProductNotFoundException() {
    return new ResponseEntity<>(
        FindProductResponse.builder().msg(MSG_PRODUCT_NOT_FOUND).status(false).build(),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(value = EmptyRequestException.class)
  public ResponseEntity<PriceCheckResponse> handleEmptyRequestException() {
    return new ResponseEntity<>(
        PriceCheckResponse.builder().msg(MSG_EMPTY_REQUEST).status(false).build(),
        HttpStatus.BAD_REQUEST
    );
  }

  @ExceptionHandler(value = Exception.class)
  public ResponseEntity<BaseResponse> handleUnknownException() {
    return new ResponseEntity<>(
        BaseResponse.builder().msg("Internal Server Error").status(false).build(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
  @ExceptionHandler(value = PostNotFoundException.class)
  public ResponseEntity<PostResponse> handlePostNotFoundException() {
    return new ResponseEntity<>(
        PostResponse.builder().msg(MSG_PRODUCT_NOT_FOUND).status(false).build(),
        HttpStatus.NOT_FOUND
    );
  }
}
