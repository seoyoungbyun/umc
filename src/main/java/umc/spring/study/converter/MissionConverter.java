package umc.spring.study.converter;

import org.springframework.data.domain.Page;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.web.dto.MissionRequestDTO;
import umc.spring.study.web.dto.MissionResponseDTO;
import umc.spring.study.web.dto.ReviewRequestDTO;
import umc.spring.study.web.dto.ReviewResponseDTO;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResponseDTO.MissionJoinResultDTO toJoinResultDTO(Mission mission){
        return MissionResponseDTO.MissionJoinResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.MissionJoinDto request, Store store){

        return Mission.builder()
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .missionSpec(request.getMissionSpec())
                .store(store)
                .build();
    }

    public static MissionResponseDTO.MissionPreViewDTO missionPreViewDTO(Mission mission){
        return MissionResponseDTO.MissionPreViewDTO.builder()
                .storeName(mission.getStore().getName())
                .reward(mission.getReward())
                .missionSpec(mission.getMissionSpec())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .build();
    }
    public static MissionResponseDTO.MissionPreViewListDTO missionPreViewListDTO(Page<Mission> missionList){

        List<MissionResponseDTO.MissionPreViewDTO> missionPreViewDTOList = missionList.stream()
                .map(MissionConverter::missionPreViewDTO).collect(Collectors.toList());

        return MissionResponseDTO.MissionPreViewListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionPreViewDTOList.size())
                .missionList(missionPreViewDTOList)
                .build();
    }
}
