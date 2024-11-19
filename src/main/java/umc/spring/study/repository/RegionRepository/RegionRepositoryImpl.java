package umc.spring.study.repository.RegionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.study.domain.QRegion;
import umc.spring.study.domain.Region;

@Repository
@RequiredArgsConstructor
public class RegionRepositoryImpl implements RegionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    QRegion region = QRegion.region;

    @Override
    public Region findRegionById(Long regionId) {
        return jpaQueryFactory
                .selectFrom(region)
                .where(region.id.eq(regionId))
                .fetchOne();
    }
}
