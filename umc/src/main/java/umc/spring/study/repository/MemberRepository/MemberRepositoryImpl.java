package umc.spring.study.repository.MemberRepository;

import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.QMember;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    QMember member = QMember.member;

    @Override
    public Member findMemberById(Long memberId) {
        return jpaQueryFactory
                .selectFrom(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }

    @Override
    public Member findRandomMember(){
        return jpaQueryFactory
                .selectFrom(member)
                .orderBy(Expressions.numberTemplate(Double.class, "function('RAND')").asc())
                .limit(1)
                .fetchOne();
    }
}
