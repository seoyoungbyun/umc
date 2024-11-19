package umc.spring.study.service.MemberService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.Member;
import umc.spring.study.repository.MemberRepository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

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
}
