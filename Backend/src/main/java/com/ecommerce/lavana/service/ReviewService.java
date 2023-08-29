package com.ecommerce.lavana.service;

import com.ecommerce.lavana.DTO.ReviewDTO;
import com.ecommerce.lavana.Entity.Review;

public interface ReviewService {
    public void postReview(String user , ReviewDTO reviewDTO)throws Exception;

    public Review putReview(String user, ReviewDTO reviewDTO)throws Exception;

    public void deleteReview(String user, ReviewDTO reviewDTO)throws Exception;
}
