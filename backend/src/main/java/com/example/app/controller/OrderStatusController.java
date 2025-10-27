package com.example.app.controller;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.app.dto.OrderStatusDTO;
import com.example.app.model.OrderStatus;
import com.example.app.service.OrderStatusService;


@RestController
@RequestMapping(value = "/api/orderStatuss", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderStatusController {

    @Autowired
    private OrderStatusService orderStatusService;

    public OrderStatusController() {

    }

    /**
     * Trả về danh sách các trạng thái của 1 order
     *
     * @return A list of all the order statuses
     */
    @GetMapping
    public ResponseEntity<List<OrderStatus>> getAllOrderStatuss() {
        return ResponseEntity.ok(orderStatusService.findAll());
    }

    /**
     * Trả về trạng thái tường ứng id
     *
     * @param id The id of the order status to be retrieved.
     * @return A ResponseEntity with the OrderStatus object.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderStatus> getOrderStatus(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(orderStatusService.get(id));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createOrderStatus(
            @RequestBody @Valid final OrderStatusDTO orderStatusDTO) {
        return new ResponseEntity<>(orderStatusService.create(orderStatusDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateOrderStatus(@PathVariable("id") final Long id,
            @RequestBody @Valid final OrderStatusDTO orderStatusDTO) {
        orderStatusService.update(id, orderStatusDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteOrderStatus(@PathVariable("id") final Long id) {
        orderStatusService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
