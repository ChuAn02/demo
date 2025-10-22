package com.example.app.controller;


import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.app.dto.CartItemDTO;
import com.example.app.model.CartItem;
import com.example.app.service.CartItemService;

import java.util.List;


@RestController
@RequestMapping(value = "/api/cartItems", produces = MediaType.APPLICATION_JSON_VALUE)
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(final CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    @GetMapping
    public ResponseEntity<List<CartItem>> getAllCartItems() {
        return ResponseEntity.ok(cartItemService.findAllByUserId());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItem> getCartItem(@PathVariable("id") final Long id) {
        return ResponseEntity.ok(cartItemService.get(id));
    }

    // Them 1 san pham vao gio hang
    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<CartItem> createCartItem(@RequestBody @Valid final CartItemDTO cartItemDTO) {
        return new ResponseEntity<>(cartItemService.create(cartItemDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItem> updateCartItem(@PathVariable("id") final Long id,
            @RequestBody @Valid final CartItemDTO cartItemDTO) {
        return ResponseEntity.ok(cartItemService.update(id, cartItemDTO));
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCartItem(@PathVariable("id") final Long id) {
        cartItemService.delete(id);
        return ResponseEntity.noContent().build();
    }



}
