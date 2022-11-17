package com.adidas.backend.prioritysaleservice.manager.dao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubscribedAdiClubMembersDao {
    private String email;
    private Integer points;
    private Instant registrationDate;
    private boolean deleted;
}
