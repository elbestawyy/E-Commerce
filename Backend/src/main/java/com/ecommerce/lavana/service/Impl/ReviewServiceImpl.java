package com.ecommerce.lavana.service.Impl;

import com.ecommerce.lavana.DAO.ReviewRepository;
import com.ecommerce.lavana.DTO.ReviewDTO;
import com.ecommerce.lavana.Entity.Review;
import com.ecommerce.lavana.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.sql.Date;
import java.util.Objects;

@Service
@Transactional
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public void postReview(String user , ReviewDTO reviewDTO)throws Exception{
        Review validateReview = reviewRepository.findByUserAndProductId(user, reviewDTO.getProduct().getId());
        if (validateReview != null) {
            throw new Exception("Review Already created");
        }
        Review review = new Review();
        review.setDate(Date.valueOf(LocalDate.now()));
        review.setUser(user);
        review.setRating(reviewDTO.getRating());
        review.setProduct(review.getProduct());

        if (reviewDTO.getDescription().isPresent()){
            review.setDescription(reviewDTO.getDescription().map(
                    Objects::toString
            ).orElse(null));
        }
        reviewRepository.save(review);
    }

    @Override
    public Review putReview(String user, ReviewDTO reviewDTO) throws Exception {
        Review review = reviewRepository.findByUserAndProductId(user, reviewDTO.getProduct().getId());
        if (review == null) {
            throw new Exception("Review not found");
        }
        review.setDate(Date.valueOf(LocalDate.now()));
        review.setUser(user);
        review.setRating(reviewDTO.getRating());
        review.setProduct(review.getProduct());

        if (reviewDTO.getDescription().isPresent()){
            review.setDescription(reviewDTO.getDescription().map(
                    Objects::toString
            ).orElse(null));
        }
        return reviewRepository.save(review);
    }

    @Override
    public void deleteReview(String user, ReviewDTO reviewDTO) throws Exception {
        Review review = reviewRepository.findByUserAndProductId(user, reviewDTO.getProduct().getId());
        reviewRepository.delete(review);
    }


}
