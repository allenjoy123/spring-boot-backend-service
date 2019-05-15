package io.qdivision.springbootbackendservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableScheduling
@EnableOAuth2Client
public class SpringBootBackendServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBackendServiceApplication.class, args);
	}
	@Bean
	public FilterRegistrationBean oauth2ClientFilterRegistration(
			OAuth2ClientContextFilter filter) {
		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(filter);
		registration.setOrder(-100);
		return registration;
	}
	@Bean
	public OAuth2RestTemplate createRestTemplate() {

		DefaultOAuth2ClientContext clientContext = new DefaultOAuth2ClientContext();

		ResourceOwnerPasswordResourceDetails resourceDetails = new ResourceOwnerPasswordResourceDetails();
		resourceDetails.setClientId("center-agent-moves");
		resourceDetails.setAccessTokenUri("https://auth.cloud1.unigroup.com/auth/realms/devunigroup/protocol/openid-connect/token");
		resourceDetails.setGrantType("password");
		resourceDetails.setUsername("Portal username");
		resourceDetails.setPassword("Portal password");

		return new OAuth2RestTemplate(resourceDetails, clientContext);
	}




}

