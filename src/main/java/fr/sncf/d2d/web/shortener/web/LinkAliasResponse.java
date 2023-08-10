package fr.sncf.d2d.web.shortener.web;

import java.net.URL;
import java.util.UUID;

public record LinkAliasResponse(
        UUID id,
        String shortId,
        URL realUrl
) {
}
