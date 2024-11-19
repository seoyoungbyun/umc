package umc.spring.study.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.Region;
import umc.spring.study.domain.Store;
import umc.spring.study.repository.RegionRepository.RegionRepository;
import umc.spring.study.repository.StoreRepository.StoreRepository;
import umc.spring.study.web.dto.StoreRequestDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;

    @Override
    @Transactional
    public Store joinStore(StoreRequestDTO.StoreJoinDto request){

        Region region = regionRepository.findRegionById(request.getRegion());
        Store newStore = StoreConverter.toStore(request, region);

        return storeRepository.save(newStore);
    }
}
