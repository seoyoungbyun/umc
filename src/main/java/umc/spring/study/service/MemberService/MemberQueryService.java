package umc.spring.study.service.MemberService;

import org.springframework.data.domain.Page;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);
    Member getMemberById(Long memberId);
    Page<Review> getReviewList(Long userId, Long storeId, Integer page);
    Page<Mission> getMissionList(Long userId, Integer page);
    Page<Mission> completeMission(Long userId, Long missionId, Integer page);
}
