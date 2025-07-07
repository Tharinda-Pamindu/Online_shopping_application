package com.example.sara_cloth_backend.controller;

import com.example.sara_cloth_backend.payload.response.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping("/check")
    public ResponseEntity<?> check() {
        return ResponseEntity.ok().body(new Response("This is a check"));
    }

    @GetMapping("/admin/check")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> admin() {
        return ResponseEntity.ok().body(new Response("This is an admin check"));
    }

    @GetMapping("/user/check")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> user() {
        return ResponseEntity.ok().body(new Response("This is a user check"));
    }

}
