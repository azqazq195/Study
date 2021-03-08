package com.project.programmers.orders;

import com.project.programmers.errors.NotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.stream.Collectors.toList;
import static com.project.programmers.utils.ApiUtils.ApiResult;
import static com.project.programmers.utils.ApiUtils.success;

@RestController
@RequestMapping("api/orders")
public class OrderRestController {

    private final OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }

    // findAll, findById, accept, reject, shipping, complete 메소드 구현이 필요합니다.
    // findAll
    @GetMapping
    public ApiResult<List<OrderDTO>> findAll() {
        return success(
                orderService.findAll().stream()
                        .map(OrderDTO::new)
                        .collect(toList())
        );
    }

    // findById
    @GetMapping(path = "{id}")
    public ApiResult<OrderDTO> findById(@PathVariable Long id) {
        return success(
                orderService.findById(id)
                        .map(OrderDTO::new)
                        .orElseThrow(() -> new NotFoundException("Could not found order for " + id))
        );
    }

}