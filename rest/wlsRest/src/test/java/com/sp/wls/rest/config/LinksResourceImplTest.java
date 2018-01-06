package com.sp.wls.rest.config;

import com.sp.wls.common.LocatedHyperlinkModel;
import com.sp.wls.common.exceptions.InvalidDataException;
import com.sp.wls.rest.common.exception.BadRequestWlsServiceException;
import com.sp.wls.rest.common.transfer.LinksResponse;
import com.sp.wls.services.config.LinksService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.MessageSource;

import java.util.HashSet;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class LinksResourceImplTest {

    @Mock
    private LinksService mockLinksService;

    @Mock
    private MessageSource mockMessageSource;

    private LinksResourceImpl objectUnderTest;

    @Before
    public void setup() {

        MockitoAnnotations.initMocks(this);

        when(mockMessageSource.getMessage(any(), any())).thenReturn("Message");

        objectUnderTest = new LinksResourceImpl();
        objectUnderTest.setLinksService(mockLinksService);
        objectUnderTest.setMessageSource(mockMessageSource);
    }

    @Test
    public void links_calls_getLinks_on_service_object() {

        final String url = "http://testurl.com";
        when(mockLinksService.getLinks(url)).thenReturn(new HashSet<>());

        objectUnderTest.links(url);

        verify(mockLinksService).getLinks(url);
    }

    @Test(expected = BadRequestWlsServiceException.class)
    public void links_throws_when_service_object_throws() {

        when(mockLinksService.getLinks("BADLY FORMATTED URL")).thenThrow(new InvalidDataException("message"));
        objectUnderTest.links("BADLY FORMATTED URL");
    }

    @Test
    public void links_returns_response_matching_service_response() {

        final String url = "http://testurl.com";
        final String link1 = "http://link1.com/html/";
        final String link1Locator = "html > body > p:nth-child(1) > a";
        final String link2 = "http://link2.com/html/";
        final String link2Locator = "html > body > p:nth-child(2) > a";

        HashSet<LocatedHyperlinkModel> links = new HashSet<>();
        links.add(new LocatedHyperlinkModel(link1, link1Locator));
        links.add(new LocatedHyperlinkModel(link2, link2Locator));

        when(mockLinksService.getLinks(url)).thenReturn(links);

        LinksResponse response = objectUnderTest.links(url);

        assertTrue(response.getLinks().size() == 2);
        assertThat(response.getLinks(),
                Matchers.hasItem(Matchers.allOf(hasProperty("linkUrl", equalTo(link1)),
                        hasProperty("cssSelector", equalTo(link1Locator)))));
        assertThat(response.getLinks(),
                Matchers.hasItem(Matchers.allOf(hasProperty("linkUrl", equalTo(link2)),
                        hasProperty("cssSelector", equalTo(link2Locator)))));
    }
}