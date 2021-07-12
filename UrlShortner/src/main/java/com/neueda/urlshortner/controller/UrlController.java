package com.neueda.urlshortner.controller;

import com.neueda.urlshortner.exception.URLProcessingException;
import com.neueda.urlshortner.model.Url;
import com.neueda.urlshortner.model.UrlData;
import com.neueda.urlshortner.model.UrlErrorDto;
import com.neueda.urlshortner.model.UrlResponseData;
import com.neueda.urlshortner.service.UrlService;
import com.neueda.urlshortner.validator.UrlValidator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@RestController
public class UrlController {

    private final static Logger LOGGER = LoggerFactory.getLogger(UrlController.class);

    @Autowired
    private UrlService urlService;


    @PostMapping("/api/generate")
    public ResponseEntity<UrlResponseData> generateShortLink(@RequestBody UrlData urldata) {

        Url urltoReturn = urlService.generateShortLink(urldata);
        if (urltoReturn != null) {
            UrlResponseData urlReponse = new UrlResponseData();
            urlReponse.setShortLink("localhost:8080/api/" + urltoReturn.getShortLink());
            LOGGER.info(urlReponse.getShortLink());
            urlReponse.setOriginalUrl(urltoReturn.getOriginalUrl());
            urlReponse.setExpirationDate(urltoReturn.getExpirationDate());
            LOGGER.info("Successfully reduced the size of the link using generateShortLink()");
            return new ResponseEntity<>(urlReponse, HttpStatus.OK);
        }
        throw new URLProcessingException("Error while generating shortlink");
    }

    @GetMapping("/api/{shortLink}")
    public void redirectToOriginal(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
    	String originalUrl =  UrlValidator.validate(urlService, shortLink);
        response.sendRedirect(originalUrl);
    }

}
