package br.edu.ifrs.alvorada.check.domain;

import lombok.Getter;

@Getter
public enum Type {
    AUDIO,
    VIDEO,
    ACCESSORY,
    COMPUTING,
    PHOTOGRAPHY,
    KEY

    /*
    public String type;

    Type(String type) {
        this.type = type;
    }

    public static final Type[] ALL = { AUDIO, VIDEO };
    */
}
