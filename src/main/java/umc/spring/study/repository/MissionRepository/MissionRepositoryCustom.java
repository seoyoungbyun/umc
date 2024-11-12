package umc.spring.study.repository.MissionRepository;

import com.querydsl.core.Tuple;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.web.dto.HomeDTO;
import umc.spring.study.web.dto.MissionByStatusDTO;

import java.util.List;

public interface MissionRepositoryCustom {
    List<HomeDTO> dynamicQueryWithBooleanBuilder(String regionName, Long cursor, Long memberId);

    List<MissionByStatusDTO> findMissionsForMemberByStatus(Long memberId, Long cursor, MissionStatus status);
}
