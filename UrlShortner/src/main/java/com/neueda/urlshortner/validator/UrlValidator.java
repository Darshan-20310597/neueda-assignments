package com.neueda.urlshortner.validator;

import com.neueda.urlshortner.exception.URLNotFoundException;
import com.neueda.urlshortner.exception.URLValidationException;
import com.neueda.urlshortner.model.Url;
import com.neueda.urlshortner.service.UrlService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

public class UrlValidator {

    private final static Logger LOGGER = LoggerFactory.getLogger(UrlValidator.class);

    public static String validate(UrlService urlService, String shortLink) {
        if (StringUtils.isEmpty(shortLink.trim())) {
            throw new URLValidationException("ShortLink is Empty");
        }

        Url urltoReturn = urlService.getEncodedUrl(shortLink);
        if (urltoReturn == null) {
            throw new URLNotFoundException("The short link doesn't exist or is Expired");
        }
        // Making an assumption if the expire time is before the current system time we conclude it by saying the
        // link is expired.
        if (urltoReturn.getExpirationDate().isBefore(LocalDateTime.now())) {
            urlService.deleteShortLink(urltoReturn);
            throw new URLValidationException("Url is expired.Please generate a new one");
        }
        return urltoReturn.getOriginalUrl();
    }
}
