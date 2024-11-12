package umc.spring.study.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.spring.study.domain.*;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.domain.mapping.QMemberMission;
import umc.spring.study.web.dto.HomeDTO;
import umc.spring.study.web.dto.MissionByStatusDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;
    QMission mission = QMission.mission;
    QStore store = QStore.store;
    QRegion region = QRegion.region;

    @Override
    public List<HomeDTO> dynamicQueryWithBooleanBuilder(String regionName, Long cursor, Long memberId) {
        BooleanBuilder predicate = new BooleanBuilder();

        if (regionName != null) {
            predicate.and(region.name.eq(regionName));
        }

        predicate.and(mission.deadline.gt(LocalDate.now()));

        if (cursor != null) {
            predicate.and(mission.id.lt(cursor));
        }

        if (memberId != null) {
            predicate.and(mission.id.notIn(JPAExpressions
                    .select(QMemberMission.memberMission.mission.id)
                    .from(QMemberMission.memberMission)
                    .where(QMemberMission.memberMission.member.id.eq(memberId))));
        }

        List<Tuple> result = jpaQueryFactory
                .select(mission.id, store.name, mission.reward, mission.missionSpec,
                        Expressions.numberTemplate(Long.class, "DATEDIFF({0}, {1})", mission.deadline, LocalDate.now()))
                .from(mission)
                .join(store).on(mission.store.id.eq(store.id))
                .join(region).on(store.region.id.eq(region.id))
                .where(predicate)
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();

        return result.stream()
                .map(tuple -> new HomeDTO(
                        tuple.get(mission.id),
                        tuple.get(store.name),
                        tuple.get(mission.reward),
                        tuple.get(mission.missionSpec),
                        tuple.get(Expressions.numberTemplate(Long.class, "DATEDIFF({0}, {1})", mission.deadline, LocalDate.now())))
                )
                .collect(Collectors.toList());
    }

    @Override
    public List<MissionByStatusDTO> findMissionsForMemberByStatus(Long memberId, Long cursor, MissionStatus status){

        QMission mission = QMission.mission;
        QMember member = QMember.member;
        QStore store = QStore.store;
        QMemberMission memberMission = QMemberMission.memberMission;

        BooleanBuilder predicate = new BooleanBuilder();

        if (memberId != null) {
            predicate.and(member.id.eq(memberId));
        }

        if (cursor != null) {
            predicate.and(mission.id.lt(cursor));
        }

        if (status != null) {
            predicate.and(memberMission.status.eq(status));
        }

        List<Tuple> result = jpaQueryFactory
                .select(mission.id, store.name, mission.reward, mission.missionSpec, memberMission.status)
                .from(member)
                .join(memberMission).on(member.id.eq(memberMission.id))
                .join(mission).on(memberMission.mission.id.eq(mission.id))
                .join(store).on(mission.store.id.eq(store.id))
                .where(predicate)
                .orderBy(mission.id.desc())
                .limit(10)
                .fetch();

        return result.stream()
                .map(tuple -> new MissionByStatusDTO(
                        tuple.get(mission.id),
                        tuple.get(store.name),
                        tuple.get(mission.reward),
                        tuple.get(mission.missionSpec),
                        tuple.get(memberMission.status)
                ))
                .collect(Collectors.toList());
    }
}
