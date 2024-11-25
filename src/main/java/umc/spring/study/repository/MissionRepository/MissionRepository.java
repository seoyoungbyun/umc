package umc.spring.study.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Store;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
    Page<Mission> findAllByStore(Store store, PageRequest pageRequest);
}
