package fr.sncf.d2d.web.shortener.domain;

import java.net.URL;
import java.util.UUID;

public interface LinkAliasService {

    LinkAlias create(URL originalUlr);

    URL resolve(String alias);

    void remove(UUID id, String removalToken);
}
