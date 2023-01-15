package com.example.lab4server.services;

import com.example.lab4server.dto.request.CreateHitDto;
import com.example.lab4server.dto.response.HitResponseDto;
import com.example.lab4server.entities.HitEntity;
import com.example.lab4server.mappers.HitMapper;
import com.example.lab4server.repository.HitRepository;
import com.example.lab4server.utils.DtoCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class HitService {

    private final HitRepository hitRepository;

    @Autowired
    public HitService(HitRepository hitRepository) {
        this.hitRepository = hitRepository;
    }

    public Page<HitEntity> getAll(String userId, int page, int limit) {
        Pageable paging = PageRequest.of(page - 1, limit);
        return hitRepository.findAllPointsByUserId(userId, paging);
    }

    public HitEntity createHit(String userId, CreateHitDto dto) {
        long startTime = System.nanoTime();
        HitResponseDto pointResponseDto = DtoCreator.createDto(dto);
        pointResponseDto.setHit(this.checkHit(dto.getX(), dto.getY(), dto.getR()));
        pointResponseDto.setDate(new SimpleDateFormat("dd.MM.yyyy hh:mm").format(new Date()));
        pointResponseDto.setLeadTime((System.nanoTime() - startTime) / 1000);
        HitEntity hit = HitMapper.INSTANCE.toHitEntity(pointResponseDto);
        hit.setUserId(userId);
        return hitRepository.save(hit);
    }

    private boolean checkHit(float x, float y, float r) {
        if (x <= 0 && y >= 0)
            return (y <= x + r / 2);
        else if (x <= 0 && y <= 0)
            return (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r, 2));
        else if (x >= 0 && y <= 0)
            return (x <= r && y >= -r / 2);
        else return false;
    }

}
