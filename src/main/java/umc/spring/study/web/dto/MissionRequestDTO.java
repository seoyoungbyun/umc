package umc.spring.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class MissionJoinDto{
        @NotNull
        private Integer reward;
        @NotNull
        private LocalDate deadline;

        private String missionSpec;
    }
}
