package umc.spring.study.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import umc.spring.study.validation.annotation.ExistStore;

public class ReviewRequestDTO {

    @Getter
    public static class ReviewJoinDto{
        @NotBlank
        @Size(min = 1, max = 100)
        String body;
        @NotNull
        Float score;
    }
}
