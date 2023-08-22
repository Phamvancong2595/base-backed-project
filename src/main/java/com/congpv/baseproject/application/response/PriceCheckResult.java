package com.congpv.baseproject.application.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PriceCheckResult {

  private Long id;
  private boolean status;

}
