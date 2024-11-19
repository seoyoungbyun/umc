package umc.spring.study.web.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import umc.spring.study.domain.enums.MissionStatus;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MissionByStatusDTO {
    private Long id;
    private String storeName;
    private Integer reward;
    private String missionSpec;
    private MissionStatus status;
}
