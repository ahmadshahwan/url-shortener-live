package fr.sncf.d2d.web.shortener.web;

import fr.sncf.d2d.web.shortener.domain.LinkAlias;
import fr.sncf.d2d.web.shortener.domain.LinkAliasService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URL;

@RestController
@RequestMapping(("/links"))
public class LinkAliasController {

    private final LinkAliasService linkAliasService;

    public LinkAliasController(
            LinkAliasService linkAliasService
    ) {
        this.linkAliasService = linkAliasService;
    }

    @PostMapping
    public LinkAliasResponse create(
            @RequestBody
            URL url
    ) {
        LinkAlias linkAlias = this.linkAliasService.create(url);
        return new LinkAliasResponse(
                linkAlias.id(),
                linkAlias.alias(),
                linkAlias.originalUrl()
        );
    }
}
