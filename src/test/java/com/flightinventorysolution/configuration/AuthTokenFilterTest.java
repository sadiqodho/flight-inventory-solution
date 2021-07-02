package com.flightinventorysolution.configuration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;

@SpringBootTest
public class AuthTokenFilterTest {
    @Mock
    private AuthTokenFilter authTokenFilter;

    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    private FilterChain filterChain;
    private final String VALID_TOKEN = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImlhdCI6MTYyNTA4MzQ3MSwiZXhwIjoxNjI1MTY5ODcxfQ.pqsIAoJB1KsGufP9yjNSbUI7SMmfPrVkb5fG5zspJchn3hY2w_S78c8hbK1cG2SAxx2XxWpf8IG6Xx3LPGPHHA";

    @BeforeEach
    void beforeEach(){
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        filterChain = Mockito.mock(FilterChain.class);
    }

    @Test
    void positive_doFilterInternal() throws ServletException, IOException {
        request.addHeader("Authorization", VALID_TOKEN);
        authTokenFilter.doFilterInternal(request, response, filterChain);
        Mockito.verify(authTokenFilter, Mockito.times(1)).doFilterInternal(request, response, filterChain);
    }
}
