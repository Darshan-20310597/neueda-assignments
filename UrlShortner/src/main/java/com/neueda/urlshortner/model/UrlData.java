package com.neueda.urlshortner.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UrlData {
	
	private String url;
	private String expirationDate; //Optional
	
	
	public UrlData(String url, String expirationDate) {
		this.url = url;
		this.expirationDate = expirationDate;
	}

	public UrlData() {
		//No Arg Constructor
	}

	@Override
	public String toString() {
		return "UrlData [url=" + url + ", expirationDate=" + expirationDate + "]";
	}
}
