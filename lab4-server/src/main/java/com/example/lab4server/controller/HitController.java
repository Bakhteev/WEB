package com.example.lab4server.controller;

import com.example.lab4server.dto.request.CreateHitDto;
import com.example.lab4server.dto.response.HitResponseDto;
import com.example.lab4server.dto.response.HitsPageResponseDto;
import com.example.lab4server.entities.HitEntity;
import com.example.lab4server.mappers.HitMapper;
import com.example.lab4server.security.userDetails.UserDetailsImpl;
import com.example.lab4server.services.HitService;
import com.example.lab4server.utils.DtoCreator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hits")
public class HitController {

    private final HitService hitService;

    @Autowired
    public HitController(HitService hitService) {
        this.hitService = hitService;
    }

    @GetMapping
    public ResponseEntity<HitsPageResponseDto> getAll(@RequestParam(name = "page", defaultValue = "1") int page,
                                                      @RequestParam(name = "limit", defaultValue = "10") int limit) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(DtoCreator.createDto(this.hitService.getAll(user.getId(), page, limit)));
    }


    @PostMapping("/create")
    public ResponseEntity<HitResponseDto> create(@Valid @RequestBody CreateHitDto createHitDto) {
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        HitEntity hit = this.hitService.createHit(user.getId(), createHitDto);
        return new ResponseEntity<>(HitMapper.INSTANCE.toDTO(hit), HttpStatus.CREATED);
    }


}
