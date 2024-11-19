package umc.spring.study.service.MemberMissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.study.apiPayload.exception.handler.MissionHandler;
import umc.spring.study.apiPayload.exception.handler.StoreHandler;
import umc.spring.study.converter.MemberConverter;
import umc.spring.study.converter.MemberMissionConverter;
import umc.spring.study.converter.MemberPreferConverter;
import umc.spring.study.domain.FoodCategory;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.study.repository.MemberRepository.MemberRepository;
import umc.spring.study.repository.MissionRepository.MissionRepository;
import umc.spring.study.web.dto.MemberRequestDTO;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MemberMission joinMemberMission(MemberRequestDTO.MemberMissionJoinDto request) {
        //Member member = memberRepository.findRandomMember();
        Member member = memberRepository.findMemberById(request.getMemberId());
        Mission mission = missionRepository.findById(request.getMissionId()).orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));

        boolean isChallenging = memberMissionRepository
                .findByMemberIdAndMissionId(member.getId(), request.getMissionId())
                .isPresent();

        if (isChallenging) {
            throw new MissionHandler(ErrorStatus.MISSION_MISSION_FOUND);
        }

        MemberMission memberMission = MemberMissionConverter.toMemberMission(request, member, mission);

        return memberMissionRepository.save(memberMission);
    }
}
