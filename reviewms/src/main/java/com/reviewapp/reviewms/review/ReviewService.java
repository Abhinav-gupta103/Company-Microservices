package com.reviewapp.reviewms.review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);

    Boolean createReview(Long companyId, Review review);

    Review getReview(Long reviewId);

    Boolean updateReview(Long reviewId, Review updatedReview);

    Boolean deletedReview(Long reviewId);
}
