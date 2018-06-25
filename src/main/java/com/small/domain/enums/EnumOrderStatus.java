package com.small.domain.enums;

public enum EnumOrderStatus {
    PURCHASED("PURCHASED"),
    PENDING("PENDING"),
    CONFIRMED("CONFIRMED"),
    CANCELD("CANCELD"),
    FAILED("FAILED"),
    SHIPPED("SHIPPED"),
    DELIVERED("DELIVERED");

    private String dbCode;

    EnumOrderStatus(String dbCode){
        this.dbCode = dbCode;
    }

    public String getDbCode() {return dbCode;}
}

