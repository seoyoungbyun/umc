package umc.spring.study.service.StoreService;

import umc.spring.study.domain.Store;
import umc.spring.study.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Store joinStore(StoreRequestDTO.StoreJoinDto request);
}
