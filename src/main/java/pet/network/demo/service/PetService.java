package pet.network.demo.service;

import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import pet.network.demo.dto.pet.ImageUpdateRequestDto;
import pet.network.demo.dto.pet.PetCreateRequestDto;
import pet.network.demo.dto.pet.PetCreateResponseDto;
import pet.network.demo.dto.pet.PetSearchResponseDto;

public interface PetService {
    List<PetSearchResponseDto> getPets(Pageable pageable);

    ResponseEntity<byte[]> getImage(Long petId);

    PetCreateResponseDto createPet(PetCreateRequestDto requestDto);

    PetCreateResponseDto findPetById(Long petId);

    ResponseEntity<byte[]> findImageById(Long imageId);

    void deletePetById(Long imageId);

    void updatePetImageById(Long petImageId, ImageUpdateRequestDto requestDto);

    PetCreateResponseDto updatePetById(PetCreateRequestDto requestDto, Long petId);
}
