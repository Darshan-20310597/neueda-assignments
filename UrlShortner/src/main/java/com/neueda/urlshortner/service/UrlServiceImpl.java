package com.neueda.urlshortner.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

import com.neueda.urlshortner.exception.URLValidationException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.common.hash.Hashing;
import com.neueda.urlshortner.model.*;

import com.neueda.urlshortner.repository.*;



@Component
@Service
public class UrlServiceImpl implements UrlService{

	
	@Autowired
	private UrlRepository urlRepository;
	
	@Override
	public Url generateShortLink(UrlData url) {

		if (StringUtils.isNotBlank(url.getUrl())){
			String encodedUrl = encodeUrl(url.getUrl());
			Url upload = new Url();
			upload.setCreationDate(LocalDateTime.now());
			upload.setOriginalUrl(url.getUrl());
			upload.setShortLink(encodedUrl);
			upload.setExpirationDate(getExpireDate(url.getExpirationDate(),upload.getCreationDate()));
			return persistShortLink(upload);
		} else {
			throw new URLValidationException("Invalid Url to generate ShortLink");
		}

	}

	private LocalDateTime getExpireDate(String expirationDate, LocalDateTime creationDate) {
		if(StringUtils.isBlank(expirationDate)) {
			return creationDate.plusSeconds(150);
		}
		LocalDateTime expirationDatetoRet = LocalDateTime.parse(expirationDate);
		return expirationDatetoRet;
	}
	private String encodeUrl(String url) {
		// Covert long url into short link 
		String encodedUrl = "";
		LocalDateTime time = LocalDateTime.now();
		encodedUrl = Hashing.murmur3_32().hashString(url.concat(time.toString()), StandardCharsets.UTF_8).toString(); 
		return encodedUrl;
	}

	@Override
	public Url persistShortLink(Url url) {
		return urlRepository.save(url);
	}

	@Override
	public Url getEncodedUrl(String url) {
		return urlRepository.findByShortLink(url);
	}

	@Override
	public void deleteShortLink(Url url) {
		urlRepository.delete(url);
	}

}
