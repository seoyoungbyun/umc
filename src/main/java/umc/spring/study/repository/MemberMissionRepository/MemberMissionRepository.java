package umc.spring.study.repository.MemberMissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {

    Optional<MemberMission> findByMemberIdAndMissionId(Long memberId, Long missionId);

    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus missionStatus, PageRequest pageRequest);

    Optional<MemberMission> findByMemberAndMission(Member member, Mission mission);

    Optional<MemberMission> findByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus missionStatus);
}
