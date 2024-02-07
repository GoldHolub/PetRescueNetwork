package pet.network.demo.model;

import lombok.Data;

@Data
public class Pet {
    private String type;
    private String name;
    private String color;
    public Pet() {
    }
    public Pet(String type, String name, String color) {
        this.type = type;
        this.name = name;
        this.color = color;
    }
}
