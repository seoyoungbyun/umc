package umc.spring.study.web.dto;

import lombok.*;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HomeDTO {
    private Long id;
    private String storeName;
    private Integer reward;
    private String missionSpec;
    private Long daysLeft;

    @Override
    public String toString() {
        return "MissionDTO{" +
                "id=" + id +
                ", storeName='" + storeName + '\'' +
                ", reward=" + reward +
                ", missionSpec='" + missionSpec + '\'' +
                ", daysLeft=" + daysLeft +
                '}';
    }
}
