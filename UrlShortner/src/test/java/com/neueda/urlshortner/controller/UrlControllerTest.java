package com.neueda.urlshortner.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.neueda.urlshortner.exception.URLNotFoundException;
import com.neueda.urlshortner.exception.URLValidationException;
import com.neueda.urlshortner.model.Url;
import com.neueda.urlshortner.model.UrlData;
import com.neueda.urlshortner.service.UrlServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlControllerTest {

    private String originalUrl = "https://www.amazon.co.uk/dp/B08C1RR8JM/ref=s9_acsd_al_bw_c2_x_1_i?pf_rd_m=A3P5ROKL5A1OLE&pf_rd_s=merchandised-search-3&pf_rd_r=PSMP02K9RBPHV5G7RXKH&pf_rd_t=101&pf_rd_p=6628636d-eb1b-4d62-9ea4-c234d4abeeb3&pf_rd_i=5157838031";

    private MockMvc mockMvc;

    @Mock
    private UrlServiceImpl mockUrlService;

    @InjectMocks
    private UrlController mockController;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(mockController)
                .setControllerAdvice()
                .build();
    }

    @Test
    public void testGenerateShortLinkSuccess() throws Exception {

        UrlData urlData = new UrlData(originalUrl, null);
        ObjectMapper objMapper = new ObjectMapper();
        String jsonString = objMapper.writeValueAsString(urlData);
        Url response = new Url();
        response.setOriginalUrl(originalUrl);

        when(mockUrlService.generateShortLink(any(UrlData.class))).thenReturn(response);
        this.mockMvc
                .perform(
                        MockMvcRequestBuilders.post("/api/generate")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(jsonString)
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.originalUrl").value(originalUrl));
    }

    @Test(expected = URLValidationException.class)
    public void testGenerateShortLinkException() throws Throwable {
        UrlData urlData = new UrlData("", null);
        ObjectMapper objMapper = new ObjectMapper();
        String jsonString = objMapper.writeValueAsString(urlData);

        when(mockUrlService.generateShortLink(any(UrlData.class))).thenThrow(URLValidationException.class);
        try {
            this.mockMvc
                    .perform(
                            MockMvcRequestBuilders.post("/api/generate")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(jsonString)
                    );
        } catch (Exception ex) {
            throw ex.getCause();
        }
    }

    @Test
    public void testRedirectToOriginalUrlSuccess() throws Exception {
        String shortLink = "3c1acff0";
        Url url = new Url(originalUrl, shortLink, LocalDateTime.now(), LocalDateTime.now().plusMinutes(10));
        when(mockUrlService.getEncodedUrl(anyString())).thenReturn(url);
        this.mockMvc
                .perform(MockMvcRequestBuilders.get("/api/3c1acff0"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection());
    }

    @Test(expected = URLValidationException.class)
    public void testRedirectToOriginalUrlValidationException() throws Throwable {
        try {
            this.mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/ "));
        } catch (Exception ex) {
            throw ex.getCause();
        }
    }

    @Test(expected = URLNotFoundException.class)
    public void testRedirectToOriginalUrlNotFoundException() throws Throwable {
        String shortLink = "3c1acff0";
        when(mockUrlService.getEncodedUrl(anyString())).thenReturn(null);
        try {
            this.mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/3c1acff0"));
        } catch (Exception ex) {
            throw ex.getCause();
        }
    }

    @Test(expected = URLValidationException.class)
    public void testRedirectToOriginalUrlExpired() throws Throwable {
        String shortLink = "3c1acff0";
        Url url = new Url(originalUrl, shortLink, LocalDateTime.now(), LocalDateTime.now());
        when(mockUrlService.getEncodedUrl(anyString())).thenReturn(url);
        try {
            this.mockMvc
                    .perform(MockMvcRequestBuilders.get("/api/3c1acff0"));
        } catch (Exception ex) {
            throw ex.getCause();
        }
    }


}
