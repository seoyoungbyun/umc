package umc.spring.study.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import umc.spring.study.domain.enums.Role;
import umc.spring.study.validation.annotation.ExistCategories;

import java.util.List;

public class MemberRequestDTO {
    
    @Getter
    @Setter
    public static class MemberJoinDto{
        @NotBlank
        String name;
        @NotBlank
        @Email
        String email;
        @NotNull
        String password;
        @NotNull
        Integer gender;
        @NotNull
        Integer birthYear;
        @NotNull
        Integer birthMonth;
        @NotNull
        Integer birthDay;
        @Size(min = 5, max = 12)
        String address;
        @Size(min = 5, max = 12)
        String specAddress;
        @ExistCategories
        List<Long> preferCategory;
        @NotNull
        Role role;
    }

    @Getter
    public static class MemberMissionJoinDto{
        @NotNull
        Long memberId;
        @NotNull
        Long missionId;
    }
}
