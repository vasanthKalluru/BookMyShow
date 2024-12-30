package org.example.bookmyshow3.models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Theatre extends BaseModel{
    private String name;
    private List<Screen> screens;
    private Region region;
}
