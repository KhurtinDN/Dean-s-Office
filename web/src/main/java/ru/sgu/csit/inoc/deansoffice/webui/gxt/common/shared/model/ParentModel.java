package ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.model;

import ru.sgu.csit.inoc.deansoffice.webui.gxt.common.shared.utils.ObjectUtil;

/**
 * User: Khurtin Denis (KhurtinDN@gmail.com)
 * Date: 2/8/11
 * Time: 10:55 AM
 */
public class ParentModel extends PersonModel {

    public String getWorkInfo() {
        return get("workInfo");
    }

    public void setWorkInfo(String workInfo) {
        set("workInfo", workInfo);
    }

    public String getPhoneNumbers() {
        return get("phoneNumbers");
    }

    public void setPhoneNumbers(String phoneNumbers) {
        set("phoneNumbers", phoneNumbers);
    }

    @Override
    public boolean equals(final Object model) {
        if (this == model) {
            return true;
        }
        if (model == null || this.getClass() != model.getClass()) {
            return false;
        }

        final ParentModel that = (ParentModel) model;

        return super.equals(that) &&
                ObjectUtil.equal(this.getWorkInfo(),that.getWorkInfo()) &&
                ObjectUtil.equal(this.getPhoneNumbers(),that.getPhoneNumbers());
    }

    @Override
    public int hashCode() {
        return ObjectUtil.hashCode(
                super.hashCode(),
                getWorkInfo(),
                getPhoneNumbers());
    }
}
