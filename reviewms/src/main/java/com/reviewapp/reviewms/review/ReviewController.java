package com.reviewapp.reviewms.review;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews(@RequestParam Long companyId) {
        return new ResponseEntity<>(this.reviewService.getAllReviews(companyId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createReview(@RequestParam Long companyId, @RequestBody Review review) {
        if (this.reviewService.createReview(companyId, review)) {
            return new ResponseEntity<>("Review saved successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review not saved successfully", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReview(@PathVariable Long reviewId) {
        Review review = this.reviewService.getReview(reviewId);
        if (review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long reviewId, @RequestBody Review updatedReview) {
        Boolean reviewIsUpdated = this.reviewService.updateReview(reviewId, updatedReview);
        if (reviewIsUpdated) {
            return new ResponseEntity<>("Review Updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review Not Updated", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long reviewId) {
        Boolean reviewIsDeleted = this.reviewService.deletedReview(reviewId);
        if (reviewIsDeleted) {
            return new ResponseEntity<>("Review Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Review Not Deleted", HttpStatus.NOT_FOUND);
        }
    }
}
