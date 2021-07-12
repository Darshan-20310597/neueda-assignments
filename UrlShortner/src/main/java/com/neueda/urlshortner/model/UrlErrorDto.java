package com.neueda.urlshortner.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UrlErrorDto {

	
	private String status;
	private String error;
	public UrlErrorDto(String status, String error) {
		this.status = status;
		this.error = error;
	}
	public UrlErrorDto() {

	}
	@Override
	public String toString() {
		return "UrlErrorDto [status=" + status + ", error=" + error + "]";
	}
	
	
	
}
