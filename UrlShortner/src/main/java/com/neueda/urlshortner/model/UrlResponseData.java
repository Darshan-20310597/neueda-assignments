package com.neueda.urlshortner.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlResponseData {
	
	
	private String originalUrl;
	private String shortLink;
	private LocalDateTime expirationDate;
	
	public UrlResponseData(String originalUrl, String shortLink, LocalDateTime expirationDate) {
		this.originalUrl = originalUrl;
		this.shortLink = shortLink;
		this.expirationDate = expirationDate;
	}
	
	public UrlResponseData() {
	}
	
	
	@Override
	public String toString() {
		return "UrlResponseData [originalUrl=" + originalUrl + ", shortLink=" + shortLink + ", expirationDate="
				+ expirationDate + "]";
	}


	
	
	

}
