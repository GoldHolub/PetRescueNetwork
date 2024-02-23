package pet.network.demo.service.impl;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pet.network.demo.dto.pet.ImageUpdateRequestDto;
import pet.network.demo.dto.pet.PetCreateRequestDto;
import pet.network.demo.dto.pet.PetCreateResponseDto;
import pet.network.demo.dto.pet.PetSearchResponseDto;
import pet.network.demo.exception.EntityNotFoundException;
import pet.network.demo.mapper.PetMapper;
import pet.network.demo.model.Pet;
import pet.network.demo.model.PetImage;
import pet.network.demo.repository.pet.PetImageRepository;
import pet.network.demo.repository.pet.PetRepository;
import pet.network.demo.service.PetService;

@RequiredArgsConstructor
@Service
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final PetMapper petMapper;
    private final PetImageRepository petImageRepository;

    @Override
    public List<PetSearchResponseDto> getPets(Pageable pageable) {
        int startIndex = pageable.getPageNumber() == 0 ? 1
                : pageable.getPageNumber() * pageable.getPageSize() - pageable.getPageSize() + 1;
        int endIndex = pageable.getPageNumber() == 0 ? pageable.getPageSize()
                : pageable.getPageNumber() * pageable.getPageSize();

        List<PetSearchResponseDto> petList = new ArrayList<>();
        for (int i = startIndex; i <= endIndex; i++) {
            PetSearchResponseDto petDto = new PetSearchResponseDto();
            petDto.setId((long) i);
            petDto.setImageUrl("/api/pets/image/" + i);
            petDto.setAge(LocalDate.of(2, 2, 2));
            petDto.setFond(false);
            petDto.setSex("Girl");
            petDto.setPriceForDonate(30000L);
            petDto.setAccumulatedPrice(25000L);
            petDto.setTitle("Bublic needs your help!! Please " + i);
            petList.add(petDto);
        }
        return petList;
    }

    @Override
    @Transactional
    public PetCreateResponseDto createPet(PetCreateRequestDto requestDto) {
        PetImage image = new PetImage();
        image.setImage(requestDto.getImage());

        Pet pet = petMapper.toModel(requestDto);
        pet.setPetImage(image);
        petImageRepository.save(image);
        Pet savedPet = petRepository.save(pet);
        return petMapper.toDto(savedPet);
    }

    @Override
    public PetCreateResponseDto findPetById(Long petId) {
        Pet pet = petRepository.findById(petId).orElseThrow(
                () -> new EntityNotFoundException("Can't find pet by id: " + petId));
        return petMapper.toDto(pet);
    }

    @Override
    public ResponseEntity<byte[]> findImageById(Long imageId) {
        PetImage image = petImageRepository.findById(imageId).orElseThrow(
                () -> new EntityNotFoundException("Can't find image by id: " + imageId));
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(image.getImage());
    }

    @Override
    public void deletePetById(Long petId) {
        petRepository.findById(petId).orElseThrow(
                () -> new EntityNotFoundException("Can't find pet by id: " + petId));
        petRepository.deleteById(petId);
    }

    @Override
    public void updatePetImageById(Long petImageId, ImageUpdateRequestDto requestDto) {
        PetImage image = petImageRepository.findById(petImageId).orElseThrow(
                () -> new EntityNotFoundException("Can't find pet image by id: " + petImageId));
        image.setImage(requestDto.getImage());
        petImageRepository.save(image);
    }

    @Override
    public PetCreateResponseDto updatePetById(PetCreateRequestDto requestDto, Long petId) {
        Pet oldPet = petRepository.findById(petId).orElseThrow(
                () -> new EntityNotFoundException("Can't find pet by id: " + petId));
        PetImage image = new PetImage();
        image.setId(oldPet.getPetImage().getId());
        image.setImage(requestDto.getImage());
        Pet pet = petMapper.toModel(requestDto);
        pet.setId(petId);
        pet.setPetImage(image);
        Pet savedPet = petRepository.save(pet);
        return petMapper.toDto(savedPet);
    }

    @Override
    public ResponseEntity<byte[]> getImage(Long petId) {
        String image = "/9j/4AAQSkZJRgABAQAAAQABAAD/2wCEAAkGBwgHBgkIBwgKCgkLDRYPDQ"
                + "wMDRsUFRAWIB0iIiAdHx8kKDQsJCYxJx8fLT0tMTU3Ojo6Iys/RD84QzQ5OjcBCgoKDQw"
                + "NGg8PGjclHyU3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nzc3Nz"
                + "c3N//AABEIAJQApgMBIgACEQEDEQH/xAAbAAEAAgMBAQAAAAAAAAAAAAAABQYDBAcCAf/EADMQA"
                + "AEDAwIEAwYGAwEAAAAAAAEAAgMEBRESIQYxQVETIqEUMkJhgZEHFSNxscFS0fHh/8QAGQEBAAMB"
                + "AQAAAAAAAAAAAAAAAAIDBAEF/8QAIxEBAQEAAgIBAwUAAAAAAAAAAAECAxESITEiMkIEExRhcf/"
                + "aAAwDAQACEQMRAD8A7iiIgIiICIiAiIgIiICIiAiIgIiICIiAiIgIiICIiAiIgIiIMFZUClgdK"
                + "RnGwHcqLhvb2vaKqENaTjLDnCzcRP0U0B+EztDv2wVA3OR8cRczbG+4Wbk1rz6laOPGbn2uEUj"
                + "ZGB7HBzXbghe1UeHLy17QdQ8MuxI3/A91bWnIyNwrePflFXJx3F9vqIisQEREBERAREQEREBER"
                + "AREQEREBERBFcSwma0Tac5jxJt8jv6ZVWnkM9MxuxcRnbqr3K0OY4EZBBGFSKagbFoZLK/w2nE"
                + "Z5lnyO24WTmnW5pr/AE97lio2mpNHfK2liPl0g4PPny9V0azXcxUbY6sO1D3epwqbUUVNHxBNW"
                + "neTSGhrRtnupfW5rItYIz3WXXJccnlGnWJvPVW6O6QOaHOywHkSFuNka5uprgW4zlVqoPh00XQ"
                + "OGAtSqfIKUiN7mgjkDsVp1+puPmMmeDy+Ks1Rc6WnGXShx6NbuSo2fiINOmNjGuPIPOT6Ln1x"
                + "vroJBBGCHdXA4+ndY7fPLPKA52c9AeR+q5nm3ud30s/j5y6RS3qZ0rG1McYa84Bbn/anFTLE5"
                + "1bWRQubljN3n9v/AHCuY5K7gurLao5szNnQiIr1IiIgIiICIiAiIgIiIPhCp/E+u3OdI0+R+S"
                + "3lnPYBXAqu8aQRuoY53jeJ+22eYVPNjyyt4d+OnP4eJbRb55ZK+XRKBmMyHGsjmB3Kt9I9l6p"
                + "I3QYcHgaSP9qCoLWypM0FRBFK2Y58MjOkY5lXKx2+mtjGwQaQ1gw1jeTVjzwzV66at8njGG5z"
                + "UNLFG2qmZG2PZznOwAtOWSCWBr4JY5YJBs5rgQVE8c8K1F3qi9lSxkJY4BjmkgOONx8xg/dan"
                + "DFhqbDR+x09SKljnl7xjS1hIHL7LvNOp/bnFbf8Y5uHYp6vU5wweTW+Un5dseqw0dM+GsdCY"
                + "42SvI8NgOpzumwH9qw6T7XHC5jnOfs3HLKsVttLY6htTURxiVgwzSPUrnFd7+npZy7mY9cO2"
                + "n8spT4rtdRKdUh6D5BS6+YX1ejnMzOo862290REXXBERAREQEREBERAREQFEcTU/tFqlGQMb"
                + "5Kl1H3sNdb5GvONWwPzXL8Oz5UO531nDlqlrzAJJnDQGnbnnH0GMrQ4a/ER01fSwV8VN4dVI"
                + "Iw6AkuY4nABH7nmsHG8lO2xSR3CCSVmrQdDNfPkcf2qhwZNa4rzHVTU1TJLG79MNidpae5xn"
                + "1UZZIn4avvp3+RjJhh7ctbyUS4QsLjT6SQ/fSoyK+i9QOjpzIKeQYMg8peOob1+v2W7Q2+QR"
                + "hpxHGPdbzwFXyZzr4iWLc/LdoYmyVsT5ABpOrJ7qwdVXnnzCFh5dlMUEzpIy125b17rvF69I"
                + "8nd9tpERXKxERAREQEREBERAREQEREBQfFznNtRIJHmHJTig+LyfySQjo9pUd/bUsfdFSdJD"
                + "WRMEoa57DqP8fXZeYPZSx8TW7EkaWjSSOyiYJCyQuJOTuFK0cYnfljmh/7KiXtrnJrEuYlrW"
                + "yKPLhG1u2G6Rs0dgpYVTWM0ack8ioenZNTvb4g1Df3Vs0zJzP8AqM8mrLSRyCn2z9e2zSgku"
                + "lf8W6krU/VPJj3dIWsIGv8AKCA1bNtGiqcByLVzM6ruvcSiIivUiIiAiIgIiICIiAiIgIiIP"
                + "ii+JIvGtE7c42ypNxA3PRUy78RR1ty/LoCWxNzreT757BQ5NSRPGbagaa3e07NcdjjkrHbqK"
                + "CPS0twRzOFVG1M9NUSNbJvqwrBa7wxzmxzNbnlhqzY3n4X7xr5WRsMTTkYWR+nRl2AFqxyM"
                + "1buzttuo+trZD8JIbnAHVXW9TtVM93pIPq4oz7waO+Fktb2yVjiDnDOgUHTxSSu8SV5dq6Z"
                + "wG9sKasrQyqcARuz7qvHJ5a6Wa4+s9ptERamYREQEREBERAREQEREBEXiSRkbNcj2tb3ccB"
                + "Bo3+Uw2ipeDghnNchpqhrb2x8j9IJAyV0m+3y01NFUUbK+N0jxj9M6sFckugMdQd8PYeYPNZ"
                + "ea/VGnhnqrNU0z5a0vgPxZz3C15hPTVTHBpw125+SkuE5W3Oia5o88btLh/CtrbNGQJHx6j2"
                + "ICp/a1b3Ft5cydVHWuXxmscSdhutqqDHAx8s9VH0c8cbp9IwA8gfQ/8XqorIxNGc/Fyyr/AM"
                + "faj8u2/E0R4YORAyVv2pjhVZ7DclYqelbUAO17fJSdNGIZgGjmNyocXHry7qW+SePUboREW"
                + "xmEREBERAREQEREBERB8Oeirt64YbdmvFRVVDiRgYk0gehVjRcs7dl6chl/Cm7xVrZqO8QiF"
                + "rs+HIwkn5Egj+FrcZWCpt8LJql0WchrtAPLuuzLFUUsFTGWVETJGEbhzcqF44nOTTkf4RtfK"
                + "LjWPa4UviNZFkjzOGc4H1C6hFUslAI93oe6i5OF3QuItdRDTQOdqMXg9cgk5B+WOSzU1lrYo"
                + "PBNZFtu14YSR6rnVnqQ7zfdUbieuitN1uMPiAaS2RrM/wCe/oq0eJWadXmfvsACV2eOwUkjQ"
                + "64xQVlQRh8roGgu7d14l4VskgI/L4GE9WN0n0XP2kpyRFcIV8lZYaOpqG+HK9u7TtyJH9KeF"
                + "Q0EHBJC06bh0ULh7FUlsQOfDkbq+xyML2bbXOle51RAWEeQYcPupdWIWypeKRsjA5pyvajrV"
                + "RVFKHmonbI53RoOPVSKnEaIiLrgiIgIiICIiAiIgIiICIiAiIgIiICYREBERAREQEREBERB/9k=";
        byte[] bytes;
        try {
            bytes = Base64.getDecoder().decode(image.getBytes("UTF-8"));
            //            bytes = Files.readAllBytes(Path.of("src/main/resources/images/dog.jpg"));
        } catch (IOException e) {
            throw new RuntimeException("Can't read bytes!", e);
        }
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(bytes);
    }
}
