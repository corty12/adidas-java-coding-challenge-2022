package com.adidas.backend.publicservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class QueueSubscriptionBean {

    @JsonProperty("email")
    private @NonNull String email;

}
