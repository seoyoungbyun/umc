package umc.spring.study.repository.ReviewRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.*;
import umc.spring.study.domain.QMember;
import umc.spring.study.domain.QStore;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void insertReview(String body, Float score, Long memberId, Long storeId){
        Member member = null;
        Store store = null;

        if (memberId != null){
            member = jpaQueryFactory.selectFrom(QMember.member)
                    .where(QMember.member.id.eq(memberId))
                    .fetchOne();
        }
        if (storeId != null){
            store = jpaQueryFactory.selectFrom(QStore.store)
                    .where(QStore.store.id.eq(storeId))
                    .fetchOne();
        }

        if (body != null && score != null && member != null && store != null) {
            Review review = Review.builder()
                    .body(body)
                    .score(score)
                    .member(member)
                    .store(store)
                    .build();
            entityManager.persist(review);
        }
    }
}
