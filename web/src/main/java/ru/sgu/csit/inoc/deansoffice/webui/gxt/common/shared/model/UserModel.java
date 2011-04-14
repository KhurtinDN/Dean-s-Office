package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

/**
 * User: Denis Khurtin ( KhurtinDN (a) gmail.com )
 * Date: 4/12/11
 * Time: 3:31 PM
 */
public class UserModel extends PersonModel {

    public String getLogin() {
        return get("login");
    }

    public void setLogin(String login) {
        set("login", login);
    }

    public String getPassword() {
        return get("password");
    }

    public void setPassword(String password) {
        set("password", password);
    }
}
