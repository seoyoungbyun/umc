package umc.spring.study.service.MissionService;

import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.domain.Store;
import umc.spring.study.domain.enums.MissionStatus;
import umc.spring.study.repository.MissionRepository.MissionRepository;
import umc.spring.study.web.dto.HomeDTO;
import umc.spring.study.web.dto.MissionByStatusDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {

    private final MissionRepository missionRepository;

    @Override
    public List<HomeDTO> findMissionsByRegionAndMember(String regionName, Long cursor, Long memberId){
        List<HomeDTO> filteredMissions = missionRepository.dynamicQueryWithBooleanBuilder(regionName, cursor, memberId);

        filteredMissions.forEach(homeDTO -> System.out.println("Home: " + homeDTO));

        return filteredMissions;
    }

    @Override
    public List<MissionByStatusDTO> findMissionsForMemberByStatus(Long memberId, Long cursor, MissionStatus status){
        List<MissionByStatusDTO> filteredMissions = missionRepository.findMissionsForMemberByStatus(memberId, cursor, status);

        filteredMissions.forEach(MissionByStatusDTO -> System.out.println("Mission: " + MissionByStatusDTO));

        return filteredMissions;
    }
}
