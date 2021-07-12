package com.neueda.urlshortner.service;

import org.springframework.stereotype.Service;

import com.neueda.urlshortner.model.*;


@Service
public interface UrlService {
	
	public Url generateShortLink(UrlData url);
	public Url persistShortLink(Url url);
	public Url getEncodedUrl(String url);
	public void deleteShortLink(Url url);

}
