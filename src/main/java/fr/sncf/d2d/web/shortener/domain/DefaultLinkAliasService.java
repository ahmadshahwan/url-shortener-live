package fr.sncf.d2d.web.shortener.domain;

import org.springframework.stereotype.Service;

import java.net.URL;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class DefaultLinkAliasService implements LinkAliasService {

    private final LinkAliasRepository repository;

    private final Random random = new SecureRandom();
    private final Base64.Encoder encoder = Base64.getUrlEncoder();

    public DefaultLinkAliasService(
            LinkAliasRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public LinkAlias create(URL originalUlr) {
        return this.repository.save(new LinkAlias(
                UUID.randomUUID(),
                originalUlr,
                generateAlias(),
                generateRemovalToken()
        ));
    }

    @Override
    public URL resolve(String alias) {
        Optional<LinkAlias> linkAliasOpt = this.repository.findByAlias(alias);
        if (linkAliasOpt.isPresent()) {
            return linkAliasOpt.get().originalUrl();
        }
        throw new RuntimeException();
    }

    @Override
    public void remove(UUID id, String removalToken) {
        LinkAlias linkAlias = this.repository.retrieve(id)
                .orElseThrow();
        if (!linkAlias.removalToken().equals(removalToken)) {
            throw new IllegalStateException();
        }
        this.repository.remove(linkAlias);
    }

    private String generateAlias() {
        return this.generateRandomString(6);
    }

    private String generateRemovalToken() {
        return this.generateRandomString(30);
    }

    private String generateRandomString(int byteSize) {
        byte[] bytes = new byte[byteSize];
        this.random.nextBytes(bytes);
        return this.encoder.encodeToString(bytes);
    }
}
