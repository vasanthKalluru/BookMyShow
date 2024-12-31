package org.example.bookmyshow3.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Region extends BaseModel {
    private String name;
}
