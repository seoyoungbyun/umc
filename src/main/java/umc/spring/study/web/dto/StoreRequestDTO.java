package umc.spring.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

public class StoreRequestDTO {

    @Getter
    public static class StoreJoinDto{
        @NotBlank
        String name;
        @NotBlank
        @Size(min = 5, max = 50)
        String address;

        Float score;
        @NotNull
        Long region;
    }
}
