package ru.sgu.csit.inoc.deansoffice.aos;


import ru.sgu.csit.inoc.deansoffice.domain.Directive;
import ru.sgu.csit.inoc.deansoffice.services.impl.DirectiveServiceImpl;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 14:35
 */
public class DirectiveProcessor {
    public void execute(List<Directive> directives) {
        DirectiveServiceImpl directiveService = new DirectiveServiceImpl();

        for (int i = 0; i < directives.size(); ++i) {
            Directive directive = directives.get(i);

            directiveService.executeDirective(directive);
        }
    }
}
