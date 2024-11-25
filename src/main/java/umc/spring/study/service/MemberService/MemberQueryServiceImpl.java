package umc.spring.study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.MemberMission;
import umc.spring.study.repository.MemberMissionRepository.MemberMissionRepository;
import umc.spring.study.repository.MemberRepository.MemberRepository;
import umc.spring.study.repository.ReviewRepository.ReviewRepository;
import umc.spring.study.repository.StoreRepository.StoreRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    @Override
    public Optional<Member> findMember(Long id){
        return memberRepository.findById(id);
    }

    @Override
    public Member getMemberById(Long memberId){
        Member member = memberRepository.findMemberById(memberId);

        System.out.println("MyPage: " + member);

        return member;
    }

    @Override
    public Page<Review> getReviewList(Long memberId, Long storeId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        Store store = storeRepository.findById(storeId).get();
        Page<Review> StorePage = reviewRepository.findAllByMemberAndStore(member, store, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<Mission> getMissionList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        Page<MemberMission> memberMissions = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.CHALLENGING, PageRequest.of(page, 10));
        Page<Mission> StorePage = memberMissions.map(MemberMission::getMission);
        return StorePage;
    }

    @Override
    public Page<Mission> completeMission(Long memberId, Long missionId, Integer page) {
        Member member = memberRepository.findById(memberId).get();
        memberMissionRepository.findByMemberIdAndMissionId(memberId, missionId)
                .ifPresent(memberMission -> {
                    // 상태 변경
                    memberMission.setStatus(MissionStatus.COMPLETE);
                    memberMissionRepository.save(memberMission);
                });

        Page<MemberMission> memberMissions = memberMissionRepository.findAllByMemberAndStatus(member, MissionStatus.COMPLETE, PageRequest.of(page, 10));
        Page<Mission> StorePage = memberMissions.map(MemberMission::getMission);
        return StorePage;
    }
}
