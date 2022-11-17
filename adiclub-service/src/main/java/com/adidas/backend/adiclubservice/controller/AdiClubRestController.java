package com.adidas.backend.adiclubservice.controller;

import com.adidas.backend.adiclubservice.dto.AdiClubMemberInfoDto;
import org.springframework.http.ResponseEntity;

public interface AdiClubRestController {
    ResponseEntity<AdiClubMemberInfoDto> getAdiClubMemberInfo(final String emailAddress);

}
