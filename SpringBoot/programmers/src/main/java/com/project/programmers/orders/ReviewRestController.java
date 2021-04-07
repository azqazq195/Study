package com.project.programmers.orders;


import com.project.programmers.errors.NotFoundException;
import com.project.programmers.utils.ApiUtils.ApiResult;
import org.springframework.web.bind.annotation.*;

import static com.project.programmers.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class ReviewRestController {

    private final ReviewService reviewService;

    public ReviewRestController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    // review
//    @PostMapping(path = "{id}/review")
//    public ApiResult<ReviewDTO> review(
//            @PathVariable Long id,
//            @RequestBody String content
//            ) {
//        int row = reviewService.review(new Review(1L,id,content));
//        return success(
//                reviewService.review(new Review(1L,id,content))
//                .map(ReviewDTO::new)
//                .orElseThrow(() -> new NotFoundException("Could not found review for " + id))
//        );
//    }
}