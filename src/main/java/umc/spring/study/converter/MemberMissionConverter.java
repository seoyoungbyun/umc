package umc.spring.study.converter;

import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.enums.Gender;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.web.dto.MemberRequestDTO;
import umc.spring.study.web.dto.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberMissionConverter {

    public static MemberResponseDTO.MemberMissionJoinResultDTO toJoinResultDTO(MemberMission memberMission){
        return MemberResponseDTO.MemberMissionJoinResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static MemberMission toMemberMission(MemberRequestDTO.MemberMissionJoinDto request, Member member, Mission mission){

        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();
    }
}
