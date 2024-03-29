package com.kashinCode.springboot.adminPortal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "key_value_pairs")
public class KeyValuePair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "key_name")
    @NotNull(message = "is Required")
    @Size(min = 1,message = "is Required")
    private String keyName ="";
    @Column(name = "value")
    @NotNull(message = "is Required")
    @Size(min=1,message = "is Required")
    private String value ="";

    public KeyValuePair() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
