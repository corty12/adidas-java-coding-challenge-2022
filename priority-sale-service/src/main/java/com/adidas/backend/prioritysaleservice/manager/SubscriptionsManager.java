package com.adidas.backend.prioritysaleservice.manager;

import com.adidas.backend.avro.model.QueueSubscriptionBean;
import com.adidas.backend.prioritysaleservice.manager.dao.SubscribedAdiClubMembersDao;
import com.adidas.backend.prioritysaleservice.mysql.SubscriptionsDataBaseConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class SubscriptionsManager {

    private final SubscriptionsDataBaseConfig subscriptionsDataBaseConfig;
    private final JdbcTemplate jdbcTemplate;

    @Value(value = "${adi-club.serviceUrl}")
    private String adiClubServiceUrl;

    @Value(value = "${adi-club.path}")
    private String adiClubPath;

    @Value(value = "${adi-club.emailAddressParameter}")
    private String adiClubEmailAddressParameter;

    public void processNewSubscription(QueueSubscriptionBean queueSubscriptionBean) {
        String email = queueSubscriptionBean.getEmail();

        SubscribedAdiClubMembersDao adiClubMember = new RestTemplate()
                .getForObject(adiClubServiceUrl + adiClubPath + adiClubEmailAddressParameter + email, SubscribedAdiClubMembersDao.class);

        if (adiClubMember != null) {
            // insert into members
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(subscriptionsDataBaseConfig.subscriptionsDataSource()).withTableName("MEMBER_SUBSCRIPTIONS");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("EMAIL", adiClubMember.getEmail());
            parameters.put("POINTS", adiClubMember.getPoints());
            parameters.put("REGISTRATION_DATE", new Timestamp(new Date().getTime()));
            parameters.put("DELETED", false);

            simpleJdbcInsert.execute(parameters);
        } else {
            //Insert into nonmembers
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(subscriptionsDataBaseConfig.subscriptionsDataSource()).withTableName("NON_MEMBER_SUBSCRIPTIONS");

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("EMAIL", email);
            parameters.put("REGISTRATION_DATE", new Timestamp(new Date().getTime()));
            parameters.put("DELETED", false);

            simpleJdbcInsert.execute(parameters);
        }
    }

    public List<String> findLuckyWinners(int winnersAmount) {

        log.info("fetching {} winners, total rows in members{}", winnersAmount, jdbcTemplate.queryForObject("SELECT count(*) FROM MEMBER_SUBSCRIPTIONS", Integer.class));

        List<SubscribedAdiClubMembersDao> subscribedMembers = jdbcTemplate
                .query("SELECT * FROM MEMBER_SUBSCRIPTIONS WHERE DELETED = FALSE ORDER BY POINTS DESC, REGISTRATION_DATE ASC LIMIT ?",
                        new BeanPropertyRowMapper<>(SubscribedAdiClubMembersDao.class), winnersAmount);
        log.info("found size={}. content {} ", subscribedMembers.size(), subscribedMembers);

        return new ArrayList<>();
    }

}
