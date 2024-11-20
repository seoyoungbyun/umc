package umc.spring.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.study.apiPayload.ApiResponse;
import umc.spring.study.converter.MissionConverter;
import umc.spring.study.converter.ReviewConverter;
import umc.spring.study.converter.StoreConverter;
import umc.spring.study.domain.Mission;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.service.MissionService.MissionCommandService;
import umc.spring.study.service.ReviewService.ReviewCommandService;
import umc.spring.study.service.StoreService.StoreCommandService;
import umc.spring.study.validation.annotation.ExistStore;
import umc.spring.study.web.dto.*;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final ReviewCommandService reviewCommandService;
    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.StoreJoinResultDTO> join(@RequestBody @Valid StoreRequestDTO.StoreJoinDto request){
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(store));
    }

    @PostMapping("/{store_id}/reviews")
    public ApiResponse<ReviewResponseDTO.ReviewJoinResultDTO> join(
            @ExistStore @PathVariable Long store_id,
            @RequestBody @Valid ReviewRequestDTO.ReviewJoinDto request){
        Review review = reviewCommandService.joinReview(request, store_id);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }

    @PostMapping("/{store_id}/missions")
    public ApiResponse<MissionResponseDTO.MissionJoinResultDTO> join(
            @ExistStore @PathVariable Long store_id,
            @RequestBody @Valid MissionRequestDTO.MissionJoinDto request){
        Mission mission = missionCommandService.joinMission(request, store_id);
        return ApiResponse.onSuccess(MissionConverter.toJoinResultDTO(mission));
    }
}
