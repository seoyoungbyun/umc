package umc.spring.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.MemberConverter;
import umc.spring.study.converter.MemberMissionConverter;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.service.MemberMissionService.MemberMissionCommandService;
import umc.spring.study.service.MemberService.MemberCommandService;
import umc.spring.study.validation.annotation.IsAlreadyChallenging;
import umc.spring.study.web.dto.MemberRequestDTO;
import umc.spring.study.web.dto.MemberResponseDTO;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/signup")
    public ApiResponse<MemberResponseDTO.MemberJoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.MemberJoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @PostMapping("/missions/challenge")
    public ApiResponse<MemberResponseDTO.MemberMissionJoinResultDTO> join(@RequestBody @IsAlreadyChallenging @Valid MemberRequestDTO.MemberMissionJoinDto request){
        MemberMission memberMission = memberMissionCommandService.joinMemberMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toJoinResultDTO(memberMission));
    }
}
