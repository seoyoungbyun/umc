package umc.spring.study.service.MissionService;

import com.querydsl.core.Tuple;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.web.dto.HomeDTO;
import umc.spring.study.web.dto.MissionByStatusDTO;

import java.util.List;

public interface MissionQueryService {
    List<HomeDTO> findMissionsByRegionAndMember(String regionName, Long cursor, Long memberId);

    List<MissionByStatusDTO> findMissionsForMemberByStatus(Long memberId, Long cursor, MissionStatus status);
}
