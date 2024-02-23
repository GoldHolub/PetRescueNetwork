package pet.network.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pet.network.demo.dto.pet.ImageUpdateRequestDto;
import pet.network.demo.dto.pet.PetCreateRequestDto;
import pet.network.demo.dto.pet.PetCreateResponseDto;
import pet.network.demo.dto.pet.PetSearchResponseDto;
import pet.network.demo.service.PetService;

@Tag(name = "Pets management", description = "Pets management endpoints")
@RequiredArgsConstructor
@RequestMapping(value = "/api/pets")
@RestController
public class PetController {
    private final PetService petService;

    @Operation(summary = "Search fluffy",
            description = "search fluffy by selected parameters - type, color, weight, ets.")
    @GetMapping("/search")
    public List<PetSearchResponseDto> searchPets(Pageable pageable) {
        return petService.getPets(pageable);
    }

    @Operation(summary = "Load fluffy Image",
            description = "separately load image for a fluffy by id")
    @GetMapping("/image/{petImageId}")
    public ResponseEntity<byte[]> getImage(@PathVariable Long petImageId) {
        return petService.findImageById(petImageId);
    }

    @Operation(summary = "Create fluffy",
            description = "create fluffy in need")
    @PostMapping
    public PetCreateResponseDto createPet(@RequestBody PetCreateRequestDto requestDto) {
        return petService.createPet(requestDto);
    }

    @Operation(summary = "Get fluffy",
            description = "get fluffy by id")
    @GetMapping("/{petId}")
    public PetCreateResponseDto getPetById(@PathVariable Long petId) {
        return petService.findPetById(petId);
    }

    @Operation(summary = "Delete fluffy",
            description = "delete fluffy by id")
    @DeleteMapping("/{petId}")
    public void deletePetById(@PathVariable Long petId) {
        petService.deletePetById(petId);
    }

    @Operation(summary = "Update fluffy image",
            description = "update fluffy image by id")
    @PutMapping("/image/{petImageId}")
    public void updatePetImageById(@PathVariable Long petImageId,
                                   @RequestBody ImageUpdateRequestDto requestDto) {
        petService.updatePetImageById(petImageId, requestDto);
    }
}
