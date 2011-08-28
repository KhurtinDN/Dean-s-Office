package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

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

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final UserModel that = (UserModel) model;

        return super.equals(that) &&
                ObjectUtil.equal(this.getLogin(), that.getLogin()) &&
                ObjectUtil.equal(this.getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getLogin(),
                getPassword());
    }
}
