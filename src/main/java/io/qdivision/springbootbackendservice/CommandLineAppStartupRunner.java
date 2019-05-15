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
        String url = "https://devcenter-agent-moves.cloud1.unigroup.com/"+"api/vendor-search" +
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

        String jsonBody = "{\n" +
                "  \"centerMove\" : {\n" +
                "    \"moveDetail\" : {\n" +
                "      \"mode\" : \"add\",\n" +
                "      \"firstName\" : \"John\",\n" +
                "      \"lastName\" : \"Doe\",\n" +
                "      \"middleName\" : \"K\",\n" +
                "      \"centerMoveAgntCmpy\" : \"U\",\n" +
                "      \"centerMoveAgntNbr\" : \"164\",\n" +
                "      \"bookerCmpy\" : \"U\",\n" +
                "      \"orderBookerAgntNbr\" : \"123\",\n" +
                "      \"orderNbr\" : \"\",\n" +
                "      \"orderYearNbr\" : \"2\",\n" +
                "      \"directionType\" : \"IMPORT\",\n" +
                "      \"serviceType\" : \"DOOR_TO_DOOR\",\n" +
                "      \"departmentType\" : \"PHE\",\n" +
                "      \"transportationMode\" : \"AIR\",\n" +
                "      \"poNbr\" : \"123\",\n" +
                "      \"gppApplies\" : \"10\",\n" +
                "      \"gppDeclinedRsn\" : \"test\",\n" +
                "      \"amountDeduction\" : \"10\",\n" +
                "      \"gppValueType\" : \"test\",\n" +
                "      \"salesId\" : \"11223\",\n" +
                "      \"ileadnbr\" : \"123\",\n" +
                "      \"shipmentType\" : \"111\",\n" +
                "      \"regionNbr\" : \"1234\",\n" +
                "      \"nationalAcctNbr\" : \"123\",\n" +
                "      \"nationalAcctCmpy\" : \"U\",\n" +
                "      \"grossWeight\" : \"10\",\n" +
                "      \"billedWeight\" : \"1111\",\n" +
                "      \"cube\" : \"10\",\n" +
                "      \"originAddress\" : \"123 Main St\",\n" +
                "      \"originCity\" : \"New York\",\n" +
                "      \"originStateCode\" : \"NY\",\n" +
                "      \"originCountryCode\" : \"USA\",\n" +
                "      \"originPostalCode\" : \"12561\",\n" +
                "      \"destAddress\" : \"123 Main St\",\n" +
                "      \"destCity\" : \"St. Louis\",\n" +
                "      \"destStateCode\" : \"MO\",\n" +
                "      \"destCountryCode\" : \"USA\",\n" +
                "      \"destPostalCode\" : \"63141\",\n" +
                "      \"billToAgentCmpy\" : \"U\",\n" +
                "      \"billToAcctIndt\" : \"I\",\n" +
                "      \"gppBillRate\" : \"10\",\n" +
                "      \"gppTotalValuationAmount\" : \"10.00\",\n" +
                "      \"gppCertificateNumber\" : \"111\",\n" +
                "      \"serviceList\" : [ {\n" +
                "        \"distributionCode\" : \"064\",\n" +
                "        \"billAmount\" : \"10\",\n" +
                "        \"billCurrencyCode\" : \"USD\",\n" +
                "        \"payAmount\" : \"1111\",\n" +
                "        \"payCurrencyCode\" : \"USD\",\n" +
                "        \"agentpayCmpy\" : \"U\",\n" +
                "        \"agentpayNbr\" : \"123\",\n" +
                "        \"vendorpayNbr\" : \"123\",\n" +
                "        \"invoiceNbr\" : \"1233\",\n" +
                "        \"invoiceDate\" : \"01/01/2019\",\n" +
                "        \"guid\" : \"cc9049e3-91da-46c5-bca4-255795b76ce7\"\n" +
                "      } ],\n" +
                "      \"packDate\" : \"2020/01/31\",\n" +
                "      \"actualDeliveryDate\" : \"2020/02/27\",\n" +
                "      \"moveManagerReleaseToBillInd\" : false\n" +
                "    }\n" +
                "  }\n" +
                "}";

        HttpEntity<String> request = new HttpEntity<String>(jsonBody, headers);
        String url = "http://localhost:8080/"+"api/createorder";
        final ResponseEntity<Object> response2 = oAuth2RestTemplate
                .exchange(url, HttpMethod.PUT, request,
                        Object.class);



        System.out.println("Status Code: " + response2.getStatusCode());
    }
}
