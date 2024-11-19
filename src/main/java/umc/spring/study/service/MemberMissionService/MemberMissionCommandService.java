package umc.spring.study.service.MemberMissionService;

import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.web.dto.MemberRequestDTO;

public interface MemberMissionCommandService {
    MemberMission joinMemberMission(MemberRequestDTO.MemberMissionJoinDto request);
}
