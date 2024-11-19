package umc.spring.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.study.apiPayload.code.status.ErrorStatus;
import umc.spring.study.apiPayload.exception.handler.FoodCategoryHandler;
import umc.spring.study.apiPayload.exception.handler.StoreHandler;
import umc.spring.study.converter.MemberConverter;
import umc.spring.study.converter.MemberPreferConverter;
import umc.spring.study.converter.ReviewConverter;
import umc.spring.study.domain.FoodCategory;
import umc.spring.study.domain.Member;
import umc.spring.study.domain.Review;
import umc.spring.study.domain.Store;
import umc.spring.study.repository.MemberRepository.MemberRepository;
import umc.spring.study.repository.ReviewRepository.ReviewRepository;
import umc.spring.study.repository.StoreRepository.StoreRepository;
import umc.spring.study.web.dto.ReviewRequestDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public Review joinReview(ReviewRequestDTO.ReviewJoinDto request, Long storeId) {

        Store store = storeRepository.findById(storeId).orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findRandomMember();
        Review newReview = ReviewConverter.toReview(request, store, member);

        return reviewRepository.save(newReview);
    }
}
