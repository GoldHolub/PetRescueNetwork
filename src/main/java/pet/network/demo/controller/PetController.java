package pet.network.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.network.demo.model.Pet;

import java.util.List;

@Tag(name = "Book management", description = "Book management endpoints")
@RequestMapping(value = "/api/pets")
@RestController
public class PetController {
    @Operation(summary = "Find all pets",
            description = "find all pets by selected parameters - type, color, weight, ets.")
    @GetMapping
    public List<Pet> getPets() {
        return List.of(new Pet("dog", "Chappy", "black"), new Pet("cat", "Vasa", "white"));
    }
}
