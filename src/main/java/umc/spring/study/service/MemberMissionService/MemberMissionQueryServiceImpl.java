package umc.spring.study.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.repository.MemberMissionRepository.MemberMissionRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<MemberMission> findMissionsForMemberByStatus(Long memberId, Long cursor, MissionStatus status){
        return memberMissionRepository.findByMemberIdAndMissionIdAndStatus(memberId, cursor, status);
    }
}
