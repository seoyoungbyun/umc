package umc.spring.study.repository.ReviewRepository;

public interface ReviewRepositoryCustom {
    void insertReview(String body, Float score, Long memberId, Long storeId);
}
