package umc.spring.study.repository.MemberRepository;

import umc.spring.study.domain.Member;

public interface MemberRepositoryCustom {
    Member findMemberById(Long memberId);
}
