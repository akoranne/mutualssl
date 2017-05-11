package com.example.mutualssl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class MutualsslClientApplication {

	static {
		System.setProperty("javax.net.ssl.trustStore", "client_trust.jks");
		System.setProperty("javax.net.ssl.trustStorePassword", "s3cr3t");
		System.setProperty("javax.net.ssl.keyStore",  "client.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "s3cr3t");
		javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
				new javax.net.ssl.HostnameVerifier() {

					public boolean verify(String hostname,
										  javax.net.ssl.SSLSession sslSession) {
						if (hostname.equals("localhost")) {
							return true;
						}
						return false;
					}
				});
	}


	@Bean
	RestTemplate restTemplate(){
		return  new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(MutualsslClientApplication.class, args);
	}
}
