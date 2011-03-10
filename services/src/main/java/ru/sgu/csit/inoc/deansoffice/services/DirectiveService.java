package ru.sgu.csit.inoc.deansoffice.services;

import ru.sgu.csit.inoc.deansoffice.domain.Directive;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 12:32
 * To change this template use File | Settings | File Templates.
 */
public interface DirectiveService {
    Directive createDirective(String type);
    void executeDirective(Directive directive);
}
