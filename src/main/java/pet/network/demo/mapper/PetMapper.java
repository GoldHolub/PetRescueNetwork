package pet.network.demo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import pet.network.demo.config.MapperConfig;
import pet.network.demo.dto.pet.PetCreateRequestDto;
import pet.network.demo.dto.pet.PetCreateResponseDto;
import pet.network.demo.model.Pet;

@Mapper(config = MapperConfig.class)
public interface PetMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "petImage", ignore = true)
    @Mapping(target = "sex", source = "requestDto.sex", qualifiedByName = "mapSex")
    @Mapping(target = "healthCondition", source = "requestDto.healthCondition",
            qualifiedByName = "mapHealthCondition")
    @Mapping(target = "sterilization", source = "requestDto.sterilization",
            qualifiedByName = "mapSterilization")
    @Mapping(target = "size", source = "requestDto.size", qualifiedByName = "mapSize")
    @Mapping(target = "habitat", source = "requestDto.habitat", qualifiedByName = "mapHabitat")
    Pet toModel(PetCreateRequestDto requestDto);

    @Mapping(target = "imageId", source = "petImage.id")
    PetCreateResponseDto toDto(Pet pet);

    @Named("mapSex")
    default Pet.SexType mapSex(String sex) {
        return Pet.SexType.valueOf(sex.toUpperCase());
    }

    @Named("mapHealthCondition")
    default Pet.PetConditionType mapHealthCondition(String healthCondition) {
        String joinedString = String.join("_", healthCondition.split(" "));
        return Pet.PetConditionType.valueOf(joinedString.toUpperCase());
    }

    @Named("mapSterilization")
    default Pet.PetSterilizationType mapSterilization(String sterilization) {
        return Pet.PetSterilizationType.valueOf(sterilization.toUpperCase());
    }

    @Named("mapSize")
    default Pet.PetSizeType mapSize(String size) {
        return Pet.PetSizeType.valueOf(size.toUpperCase());
    }

    @Named("mapHabitat")
    default Pet.PetHabitatType mapHabitat(String habitat) {
        String joinedString = String.join("_", habitat.split(" "));
        return Pet.PetHabitatType.valueOf(joinedString.toUpperCase());
    }
}
