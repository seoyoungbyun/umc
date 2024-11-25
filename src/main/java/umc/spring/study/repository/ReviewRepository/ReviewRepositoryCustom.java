package umc.spring.study.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;

public interface ReviewRepositoryCustom {
    void insertReview(String body, Float score, Long memberId, Long storeId);
}
