package fr.sncf.d2d.web.shortener.persistence;

import fr.sncf.d2d.web.shortener.domain.LinkAlias;
import fr.sncf.d2d.web.shortener.domain.LinkAliasRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Repository
public class InMemoryLinkAliasRepository implements LinkAliasRepository {

    Map<UUID, LinkAlias> data = new HashMap<>();
    Map<String, LinkAlias> aliasIndex = new HashMap<>();


    @Override
    public LinkAlias save(LinkAlias linkAlias) {
        this.data.put(linkAlias.id(), linkAlias);
        this.aliasIndex.put(linkAlias.alias(), linkAlias);
        return linkAlias;
    }

    @Override
    public Optional<LinkAlias> retrieve(UUID id) {
        return Optional.ofNullable(this.data.get(id));
    }

    @Override
    public Optional<LinkAlias> findByAlias(String alias) {
        return Optional.ofNullable(this.aliasIndex.get(alias));
    }

    @Override
    public void remove(LinkAlias linkAlias) {
        this.data.remove(linkAlias.id());
        this.aliasIndex.remove(linkAlias.alias());
    }
}
