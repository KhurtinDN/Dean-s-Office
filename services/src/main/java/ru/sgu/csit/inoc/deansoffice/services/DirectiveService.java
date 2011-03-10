package ru.sgu.csit.inoc.deansoffice.services;

import ru.sgu.csit.inoc.deansoffice.domain.Directive;

import java.io.UnsupportedEncodingException;

/**
 * Created by IntelliJ IDEA.
 * User: MesheryakovAV
 * Date: 28.02.11
 * Time: 12:32
 */
public interface DirectiveService {
    Directive createDirective(String type);
    void executeDirective(Directive directive);
    String generatePrintTemplateBody(Directive directive) throws UnsupportedEncodingException;
}
