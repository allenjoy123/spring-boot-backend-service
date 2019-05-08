package io.qdivision.springbootbackendservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {

    @Autowired
    OAuth2RestTemplate oAuth2RestTemplate;

    @Override
    public void run(String... args) throws Exception {
        this.getMessageFromSecuredResource();
        this.putMessageToSecuredResource();

    }


    public void getMessageFromSecuredResource() {
        Map<String, String> params = new HashMap<>();
        params.put("query", "001");


        HttpHeaders httpHeaders = new HttpHeaders();

        HttpEntity<String> request = new HttpEntity<String>(httpHeaders);
        String url = "http://center-agent-moves-ratesdev.apps.nonprod.openshift.local/"+"api/vendor-search" +
                "?query={query}";
        final ResponseEntity<Object> response2 = oAuth2RestTemplate
                .exchange(url, HttpMethod.GET, request,
                        Object.class, params);



        System.out.println("message: " + response2.getBody());
    }

    public void putMessageToSecuredResource() {
        Map<String, String> params = new HashMap<>();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");

        String jsonBody = "{  \"centerMove\" : {    \"moveDetail\" : {    \"mode\": \"update\",  \"firstName\" : \"test\",      \"lastName\" : \"tttt\",      \"middleName\" : \"test\",      \"bookerCmpy\" : \"U\",      \"orderBookerAgntNbr\" : \"123\",      \"poNbr\" : \"123\",      \"gppApplies\" : \"10\",      \"gppDeclinedRsn\" : \"test\",      \"amountDeduction\" : \"10\",      \"gppValueType\" : \"test\",      \"salesId\" : \"11223\",      \"ileadnbr\" : \"123\",      \"shipmentType\" : \"111\",      \"regionNbr\" : \"124\", \"orderNbr\" : \"1024631\",   \"orderYearNbr\" : \"2\",  \"nationalAcctNbr\" : \"128\",      \"nationalAcctCmpy\" : \"N\",      \"grossWeight\" : \"10\",      \"billedWeight\" : \"1111\",      \"cube\" : \"10\",      \"originAddress\" : \"123\",      \"originCity\" : \"test\",      \"originStateCode\" : \"IN\",      \"originCountryCodeISO\" : \"USA\",      \"destAddress\" : \"123 Street\",      \"destCity\" : \"test\",      \"destStateCode\" : \"IL\",      \"destCountryCode\" : \"C\",      \"billToAgentCmpy\" : \"U\",      \"billToAcctIndt\" : \"I\",      \"gppBillRate\" : \"10\",      \"gppInsuranceValueAmount\" : \"1000\",      \"gppOption\" : \"rest\",      \"gppTotalLossCovge\" : \"S\",      \"gppCertificateNumber\" : \"111\",      \"serviceList\" : [ {        \"distributionCode\" : \"STL\",        \"billAmount\" : \"10\",        \"billCurrencyCode\" : \"U\",        \"payAmount\" : \"2745\",        \"payCurrencyCode\" : \"US\",        \"agentpayCmpy\" : \"U\",        \"agentpayNbr\" : \"123\",        \"vendorpayNbr\" : \"122\",        \"invoiceNbr\" : \"1233\",        \"invoiceDate\" : \"12\"  , \"guid\" : \"123456789\"    } ]    }  }}";

        HttpEntity<String> request = new HttpEntity<String>(jsonBody, headers);
        String url = "http://center-agent-moves-ratesdev.apps.nonprod.openshift.local/"+"api/createorder";
        final ResponseEntity<Object> response2 = oAuth2RestTemplate
                .exchange(url, HttpMethod.PUT, request,
                        Object.class);



        System.out.println("Status Code: " + response2.getStatusCode());
    }
}
