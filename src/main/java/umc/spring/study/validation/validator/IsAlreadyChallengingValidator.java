package umc.spring.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.service.MemberMissionService.MemberMissionQueryService;
import umc.spring.study.validation.annotation.IsAlreadyChallenging;
import umc.spring.study.web.dto.MemberRequestDTO;

@Component
@RequiredArgsConstructor
public class IsAlreadyChallengingValidator implements ConstraintValidator<IsAlreadyChallenging, MemberRequestDTO.MemberMissionJoinDto> {

    private final MemberMissionQueryService memberMissionQueryService;

    @Override
    public void initialize(IsAlreadyChallenging constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MemberRequestDTO.MemberMissionJoinDto request, ConstraintValidatorContext context) {
        boolean isValid = !memberMissionQueryService.findMissionsForMemberByStatus(request.getMemberId(), request.getMissionId(), MissionStatus.CHALLENGING).isPresent();

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MISSION_ALREADY_CHALLENGING.toString()).addConstraintViolation();
        }

        return isValid;

    }
}
