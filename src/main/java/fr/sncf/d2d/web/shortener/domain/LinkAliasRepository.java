package fr.sncf.d2d.web.shortener.domain;

import java.util.Optional;
import java.util.UUID;

public interface LinkAliasRepository {

    LinkAlias save(LinkAlias linkAlias);

    Optional<LinkAlias> retrieve(UUID id);

    Optional<LinkAlias> findByAlias(String alias);

    void remove(LinkAlias linkAlias);
}
