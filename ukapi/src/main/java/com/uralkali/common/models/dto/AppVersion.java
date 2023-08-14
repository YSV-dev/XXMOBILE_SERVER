package com.uralkali.common.models.dto;

import java.io.Serializable;

/**
 * Версия приложения
 * @author brzsmg
 */
public class AppVersion implements Serializable {
    private int number;
    private String name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AppVersion(int number, String name) {
        this.number = number;
        this.name = name;
    }
}
