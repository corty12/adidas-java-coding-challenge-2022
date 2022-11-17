package com.adidas.backend.publicservice.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class QueueSubscriptionDto {

  @JsonProperty("email")
  private @NonNull String email;

}
