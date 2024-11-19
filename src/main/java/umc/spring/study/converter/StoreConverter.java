package umc.spring.study.converter;

import umc.spring.study.domain.Region;
import umc.spring.study.domain.Store;
import umc.spring.study.web.dto.StoreRequestDTO;
import umc.spring.study.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static StoreResponseDTO.StoreJoinResultDTO toJoinResultDTO(Store store){
        return StoreResponseDTO.StoreJoinResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.StoreJoinDto request, Region region){

        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }
}
