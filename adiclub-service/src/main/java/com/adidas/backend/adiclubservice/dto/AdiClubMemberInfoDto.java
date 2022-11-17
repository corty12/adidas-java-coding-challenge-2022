package com.adidas.backend.adiclubservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdiClubMemberInfoDto {
  private String email;
  private Integer points;
  private Instant registrationDate;
}
