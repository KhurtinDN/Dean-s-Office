package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.exceptions;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/16/11
 * Time: 9:28 AM
 */
public class BadRequestException extends RuntimeException {
    public BadRequestException() {
        super();
    }

    public BadRequestException(String message) {
        super(message);
    }
}
