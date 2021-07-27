package com.larry.fc.finalproject.core;

import com.larry.fc.finalproject.core.domain.entity.BaseEntity;

import javax.persistence.Entity;

@Entity
public class SimpleEntity extends BaseEntity {

    private String name;

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "SimpleEntity{" +
                "name='" + name + '\'' +
                super.getCreatedAt() +
                '}';
    }
}
