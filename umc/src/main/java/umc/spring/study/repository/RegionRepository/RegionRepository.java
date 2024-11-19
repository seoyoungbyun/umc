package umc.spring.study.repository.RegionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long>, RegionRepositoryCustom {
}
