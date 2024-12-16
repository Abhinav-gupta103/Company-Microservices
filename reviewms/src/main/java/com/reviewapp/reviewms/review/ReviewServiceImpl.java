package com.reviewapp.reviewms.review;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = this.reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public Boolean createReview(Long companyId, Review review) {
        if (review != null) {
            review.setCompanyId(companyId);
            this.reviewRepository.save(review);
            return true;
        } else
            return false;
    }

    @Override
    public Review getReview(Long reviewId) {
        return this.reviewRepository.findById(reviewId).orElse(null);
    }

    @Override
    public Boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = this.reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setTitle(updatedReview.getTitle());
            review.setCompanyId(updatedReview.getCompanyId());
            this.reviewRepository.save(review);
            return true;
        } else
            return false;
    }

    @Override
    public Boolean deletedReview(Long reviewId) {
        Review review = this.reviewRepository.findById(reviewId).orElse(null);
        if (review != null) {
            this.reviewRepository.deleteById(reviewId);
            return true;
        } else
            return false;
    }

}
