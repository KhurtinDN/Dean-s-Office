package ru.sgu.csit.inoc.deansoffice.shared.dto;

/**
 * User: hd (KhurtinDN(a)gmail.com)
 * Date: Sep 28, 2010
 * Time: 1:18:44 PM
 */
public class SpecialityDto implements java.io.Serializable {
    private Long id;
    private String name;

    public SpecialityDto() {
    }

    public SpecialityDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
