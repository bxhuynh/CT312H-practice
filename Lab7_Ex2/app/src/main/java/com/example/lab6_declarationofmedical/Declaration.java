package com.example.lab6_declarationofmedical;

import java.io.Serializable;

public class Declaration implements Serializable {
    String title;
    String value;
    long id;
    public Declaration (long id, String title, String value) {
        this.title = title;
        this.value = value;
        this.id = id;
    }
}
