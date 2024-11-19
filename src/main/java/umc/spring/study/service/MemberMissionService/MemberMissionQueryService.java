package umc.spring.study.service.MemberMissionService;

import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionQueryService {
    Optional<MemberMission> findMissionsForMemberByStatus(Long memberId, Long cursor, MissionStatus status);
}
