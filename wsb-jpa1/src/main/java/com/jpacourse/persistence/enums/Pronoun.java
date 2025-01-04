package com.jpacourse.persistence.enums;

public enum Pronoun {
    HE("Him"),
    SHE("Her"),
    THEY("Them");

    private final String object;

    Pronoun(String object) {
        this.object = object;
    }

    public String getObject() {
        return object;
    }

}
