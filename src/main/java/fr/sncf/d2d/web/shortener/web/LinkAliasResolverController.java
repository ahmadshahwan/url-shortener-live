package fr.sncf.d2d.web.shortener.web;

import fr.sncf.d2d.web.shortener.domain.LinkAliasService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URL;

@Controller
public class LinkAliasResolverController {

    private final LinkAliasService linkAliasService;

    public LinkAliasResolverController(
            LinkAliasService linkAliasService
    ) {
        this.linkAliasService = linkAliasService;
    }

    @GetMapping("{alias}")
    public RedirectView resolve(
            @PathVariable String alias
    ) {
        URL url = this.linkAliasService.resolve(alias);
        return new RedirectView(url.toString());
    }
}
