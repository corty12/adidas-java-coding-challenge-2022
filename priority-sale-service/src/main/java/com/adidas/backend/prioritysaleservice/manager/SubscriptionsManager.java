package com.adidas.backend.prioritysaleservice.manager;

import com.adidas.backend.prioritysaleservice.manager.dto.AdiClubMemberInfoDto;
import com.adidas.backend.prioritysaleservice.mysql.SubscriptionsDataBaseConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionsManager {

    private final SubscriptionsDataBaseConfig subscriptionsDataBaseConfig;

    @Value(value = "${adi-club.serviceUrl}")
    private String adiClubServiceUrl;

    @Value(value = "${adi-club.path}")
    private String adiClubPath;

    @Value(value = "${adi-club.emailAddressParameter}")
    private String adiClubEmailAddressParameter;

    public void processNewSubscription(String pEmail) {

        RestTemplate restTemplate = new RestTemplate();

        AdiClubMemberInfoDto adiClubMember = restTemplate
                .getForObject(adiClubServiceUrl + adiClubPath + adiClubEmailAddressParameter + pEmail, AdiClubMemberInfoDto.class);


        if (adiClubMember != null) {
            // insert into members
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(subscriptionsDataBaseConfig.subscriptionsDataSource()).withTableName("MEMBER_SUBSCRIPTIONS");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("EMAIL", adiClubMember.getEmail());
            parameters.put("POINTS", adiClubMember.getPoints());
            parameters.put("REGISTRATION_DATE", new Timestamp(new Date().getTime()));

            simpleJdbcInsert.execute(parameters);
        } else {
            //Insert into nonmembers
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(subscriptionsDataBaseConfig.subscriptionsDataSource()).withTableName("NON_MEMBER_SUBSCRIPTIONS");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("EMAIL", pEmail);
            parameters.put("REGISTRATION_DATE", new Timestamp(new Date().getTime()));

            simpleJdbcInsert.execute(parameters);
        }
    }
}
