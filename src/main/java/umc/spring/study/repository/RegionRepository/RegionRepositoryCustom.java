package umc.spring.study.repository.RegionRepository;

import umc.spring.study.domain.Region;

public interface RegionRepositoryCustom{
    Region findRegionById(Long regionId);
}
