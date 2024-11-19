package umc.spring.study.service.ReviewService;

public interface ReviewQueryService {
    void insertReview(String body, Float score, Long memberId, Long storeId);
}
