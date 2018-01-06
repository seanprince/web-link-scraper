package com.sp.wls.services.config;

import com.sp.wls.common.LocatedHyperlinkModel;
import com.sp.wls.common.exceptions.InvalidDataException;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class LinksServiceImplTest {

    @Mock
    private HttpConnection mockConnection;

    private LinksService objectUnderTest;

    @Before
    public void setup() throws InvalidDataException {
        MockitoAnnotations.initMocks(this);

        objectUnderTest = new LinksServiceImpl();
    }

    @Test(expected = InvalidDataException.class)
    public void getLinks_throws_for_missing_url() {

        objectUnderTest.getLinks("");
    }

    @Test(expected = InvalidDataException.class)
    public void getLinks_throws_for_badly_formatted_url() {

        objectUnderTest.getLinks("NOT A URL");
    }

    @Test
    public void getLinks_calls_Jsoup_connect() {
        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect("http://testurl.com")).thenReturn(mockConnection);

        try {
            when(mockConnection.get()).thenReturn(new Document(""));
        } catch (IOException ignored) {
        }

        objectUnderTest.getLinks("http://testurl.com");

        PowerMockito.verifyStatic(Jsoup.class);
        Jsoup.connect("http://testurl.com");
    }

    @Test
    public void getLinks_returns_no_hyperlinks() {

        String htmlString = "<html><head><title>My title</title></head>" +
                "<body>Body content</body></html>";

        Document doc = Jsoup.parse(htmlString);

        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect("http://testurl.com")).thenReturn(mockConnection);

        try {
            when(mockConnection.get()).thenReturn(doc);
        } catch (IOException ignored) {
        }

        HashSet<LocatedHyperlinkModel> links = objectUnderTest.getLinks("http://testurl.com");

        assertTrue(links.size() == 0);
    }

    @Test
    public void getLinks_returns_correct_number_of_hyperlinks() {
        final String url = "http://testurl.com";
        String htmlString = "<html><head><title>My title</title></head>" +
                "<p><a href=\"http://link1.com/html/\">Link 1</a></p>" +
                "<p><a href=\"http://link2.com/html/\">Link 2</a></p>" +
                "<p><a href=\"http://link3.com/html/\">Link 3</a></p>"
                + "<body>Body content</body></html>";

        Document doc = Jsoup.parse(htmlString);

        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect(url)).thenReturn(mockConnection);

        try {
            when(mockConnection.get()).thenReturn(doc);
        } catch (IOException ignored) {
        }

        HashSet<LocatedHyperlinkModel> links = objectUnderTest.getLinks(url);

        assertTrue(links.size() == 3);
    }

    @Test
    public void getLinks_returns_located_hyperlinks() {
        final String url = "http://testurl.com";
        final String link1 = "http://link1.com/html/";
        final String link2 = "http://link2.com/html/";
        final String link3 = "http://link3.com/html/";

        String htmlString = "<html><head><title>My title</title></head>" +
                "<p><a href=\"" + link1 + "\">Link 1</a></p>" +
                "<p><a href=\"" + link2 + "\">Link 2</a></p>" +
                "<p><a href=\"" + link3 + "\">Link 3</a></p>" +
                "<body>Body content</body></html>";

        Document doc = Jsoup.parse(htmlString);

        PowerMockito.mockStatic(Jsoup.class);
        when(Jsoup.connect(url)).thenReturn(mockConnection);

        try {
            when(mockConnection.get()).thenReturn(doc);
        } catch (IOException ignored) {
        }

        HashSet<LocatedHyperlinkModel> links = objectUnderTest.getLinks(url);

        assertTrue(links.contains(new LocatedHyperlinkModel(link1, "html > body > p:nth-child(1) > a")));
        assertTrue(links.contains(new LocatedHyperlinkModel(link2, "html > body > p:nth-child(2) > a")));
        assertTrue(links.contains(new LocatedHyperlinkModel(link3, "html > body > p:nth-child(3) > a")));
    }
}