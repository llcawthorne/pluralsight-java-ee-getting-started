package com.pluralsight.bookstore.rest;

import com.pluralsight.bookstore.model.Book;
import com.pluralsight.bookstore.model.Language;
import com.pluralsight.bookstore.repository.BookRepository;
import com.pluralsight.bookstore.util.IsbnGenerator;
import com.pluralsight.bookstore.util.NumberGenerator;
import com.pluralsight.bookstore.util.TextUtil;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.container.test.api.RunAsClient;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import java.util.Date;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.*;
import static junit.framework.TestCase.assertEquals;

@RunWith(Arquillian.class)
@RunAsClient
public class BookEndpointTest {

    @Test
    // Test doesn't work.  Currently giving "java.lang.IllegalStateException: No baseURL found in HTTPContext
    public void createBook() throws Exception {
        // REMEMBER: the database starts out empty in my Arquillian tests

        // Test counting books
        WebTarget webTarget = ClientBuilder.newClient().target("http://localhost:8080/bookstore-back/api/books/");
        Response response = webTarget.path("count").request().get();
        assertEquals(NO_CONTENT.getStatusCode(), response.getStatus());

        // Test find all
        webTarget = ClientBuilder.newClient().target("http://localhost:8080/bookstore-back/api/books/");
        response = webTarget.request(APPLICATION_JSON).get();
        assertEquals(NO_CONTENT.getStatusCode(), response.getStatus());

        // Creates a book
        Book book = new Book("a title", "description", 12F, "isbn", new Date(), 123, "http://blahblah", Language.ENGLISH);
        webTarget = ClientBuilder.newClient().target("http://localhost:8080/bookstore-back/api/books/");
        response = webTarget.request(APPLICATION_JSON).post(Entity.entity(book, APPLICATION_JSON));
        assertEquals(CREATED.getStatusCode(), response.getStatus());

        // Test counting books
        webTarget = ClientBuilder.newClient().target("http://localhost:8080/bookstore-back/api/books/");
        response = webTarget.path("count").request().get();
        String json = response.readEntity(String.class);
        json.equals("1");

        webTarget = ClientBuilder.newClient().target("http://localhost:8080/bookstore-back/api/books/");
        response = webTarget.path("1").request().get();
        // I can't compare book to book, because date and isbn are different
        // Title was different, until I provided a sanitized title for my starting book
        // So let's compare title, description, and image url
        Book returnedBook = response.readEntity(Book.class);
        assertEquals(book.getTitle(), returnedBook.getTitle());
        assertEquals(book.getDescription(), returnedBook.getDescription());
        assertEquals(book.getImageUrl(), returnedBook.getImageUrl());
    }

    @Deployment(testable = false)
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BookEndpoint.class)
                .addClass(JAXRSConfiguration.class)
                .addClass(BookRepository.class)
                .addClass(Book.class)
                .addClass(Language.class)
                .addClass(TextUtil.class)
                .addClass(NumberGenerator.class)
                .addClass(IsbnGenerator.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/test-persistence.xml", "persistence.xml");
    }
}
