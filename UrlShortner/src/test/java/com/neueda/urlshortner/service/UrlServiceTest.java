package com.neueda.urlshortner.service;

import com.neueda.urlshortner.model.Url;
import com.neueda.urlshortner.model.UrlData;
import com.neueda.urlshortner.repository.UrlRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Captor;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UrlServiceTest {

    private UrlData urlData;

    @Mock
    private UrlRepository urlRepository;

    @Captor
    ArgumentCaptor<Url> urlCaptor;

    @InjectMocks
    private UrlServiceImpl urlServiceImpl;

    @Before
    public void init() {
        urlData = new UrlData("Longurl", "expirationDate");
    }

    @Test
    public void testGenerateShortUrlIfUrlisBlank() throws Exception {
        urlData.setUrl("");
        try {
            urlServiceImpl.generateShortLink(urlData);
        } catch (EntityNotFoundException ex) {
            assertEquals(
                    "There is an error while processing the request, this is because the entered url is blank or Null. Please check and retry",
                    ex.getMessage());
        }

    }

    @Test
    public void testGenerateShortSuccessfully() throws Exception {
        urlData.setUrl(
                "https://www.amazon.co.uk/all-new-blink-outdoor-wireless-weather-resistant-hd-security-camera-with-2-year-battery-life-motion-detection-1-camera-system/dp/B088CZW8XC/ref=gbps_img_m-2_b6bc_fd384b6b?smid=A3P5ROKL5A1OLE&pf_rd_p=c7416edb-2cce-4fca-8ec2-3a43dafab6bc&pf_rd_s=merchandised-search-2&pf_rd_t=101&pf_rd_i=341686031&pf_rd_m=A3P5ROKL5A1OLE&pf_rd_r=QKEZDE26ZZ3Y0WT9NT1Z");
        urlData.setExpirationDate(LocalDateTime.now().toString());
        urlServiceImpl.generateShortLink(urlData);
        verify(urlRepository).save(urlCaptor.capture());
        Url url = urlCaptor.getValue();
        assertEquals(8, url.getShortLink().length());
        verify(urlRepository, times(1)).save(url);
    }

}
