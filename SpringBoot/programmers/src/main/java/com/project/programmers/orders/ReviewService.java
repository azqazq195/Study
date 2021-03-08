package com.project.programmers.orders;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;


@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public int review(Review review){
        checkNotNull(review.getUserSeq(), "userId must be provided");
        checkNotNull(review.getProductSeq(), "productId must be provided");
        return reviewRepository.review(review);
    }

}
