package com.moh.hamadpulse.constants;

import java.util.Objects;

public class Delivery {
    String DELIVERY_CODE;
    String DELIVERY_NAME_AR;
    String DELIVERY_NAME_EN;

    public Delivery(String DELIVERY_CODE, String DELIVERY_NAME_AR, String DELIVERY_NAME_EN) {
        this.DELIVERY_CODE = DELIVERY_CODE;
        this.DELIVERY_NAME_AR = DELIVERY_NAME_AR;
        this.DELIVERY_NAME_EN = DELIVERY_NAME_EN;
    }

    public Delivery(String DELIVERY_NAME_EN) {
        this.DELIVERY_NAME_EN = DELIVERY_NAME_EN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Delivery delivery = (Delivery) o;
        return Objects.equals(DELIVERY_CODE, delivery.DELIVERY_CODE);
    }

    public String getDELIVERY_CODE() {
        return DELIVERY_CODE;
    }

    public void setDELIVERY_CODE(String DELIVERY_CODE) {
        this.DELIVERY_CODE = DELIVERY_CODE;
    }

    public String getDELIVERY_NAME_AR() {
        return DELIVERY_NAME_AR;
    }

    public void setDELIVERY_NAME_AR(String DELIVERY_NAME_AR) {
        this.DELIVERY_NAME_AR = DELIVERY_NAME_AR;
    }

    public String getDELIVERY_NAME_EN() {
        return DELIVERY_NAME_EN;
    }

    public void setDELIVERY_NAME_EN(String DELIVERY_NAME_EN) {
        this.DELIVERY_NAME_EN = DELIVERY_NAME_EN;
    }

    @Override
    public String toString() {
        return DELIVERY_NAME_EN;
    }
}
