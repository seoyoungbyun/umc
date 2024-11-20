package umc.spring.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.converter.MissionConverter;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Store;
import umc.spring.study.repository.MissionRepository.MissionRepository;
import umc.spring.study.repository.StoreRepository.StoreRepository;
import umc.spring.study.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    @Override
    @Transactional
    public Mission joinMission(MissionRequestDTO.MissionJoinDto request, Long storeId) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new RuntimeException());;
        Mission mission = MissionConverter.toMission(request, store);

        return missionRepository.save(mission);
    }
}
