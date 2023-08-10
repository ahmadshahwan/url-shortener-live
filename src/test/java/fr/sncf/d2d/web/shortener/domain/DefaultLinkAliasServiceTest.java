package fr.sncf.d2d.web.shortener.domain;

import fr.sncf.d2d.web.shortener.persistence.InMemoryLinkAliasRepository;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

class DefaultLinkAliasServiceTest {

    private LinkAliasRepository repository = new InMemoryLinkAliasRepository();
    private DefaultLinkAliasService sut = new DefaultLinkAliasService(this.repository);

    @Test
    void create() throws MalformedURLException {
        // Given
        URL url = new URL("https://www.google.com");
        // When
        LinkAlias linkAlias = this.sut.create(url);
        // Then
        assertNotNull(linkAlias);
        assertEquals(linkAlias.originalUrl(), url);
        assertEquals(linkAlias.alias().length(), 8);

    }

    @Test
    void resolve() throws MalformedURLException {
        // Given
        URL url = new URL("https://www.google.com");
        LinkAlias linkAlias = this.sut.create(url);
        // When
        URL resolvedUrl = this.sut.resolve(linkAlias.alias());
        // Then
        assertEquals(resolvedUrl, url);
    }

    @Test
    void remove() throws MalformedURLException {
        // Given
        URL url = new URL("https://www.google.com");
        LinkAlias linkAlias = this.sut.create(url);
        // When
        this.sut.remove(linkAlias.id(), linkAlias.removalToken());
        // Then
        assertThrows(Exception.class, () -> this.sut.resolve(linkAlias.alias()));
    }
}