package com.swlibs.common.specifications.gatt;

/**
 * Gatt identifier info.
 */
public class GatIdentifier {

    private String mName;
    private String mId;
    private Integer mNumber;
    private String mSpecification;

    public GatIdentifier(String name, String id, Integer number, String specification) {
        this.mName = name;
        this.mId = id;
        this.mNumber = number;
        this.mSpecification = specification;
    }

    public String getName() {
        return mName;
    }

    public String getId() {
        return mId;
    }

    public Integer getNumber() {
        return mNumber;
    }

    public String getSpecification() {
        return mSpecification;
    }
}
