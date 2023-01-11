package com.example.lab4server.controller;

import com.example.lab4server.dto.CreatePointDto;
import com.example.lab4server.dto.PointDto;
import com.example.lab4server.entities.PointEntity;
import com.example.lab4server.mappers.PointMapper;
import com.example.lab4server.repository.PointRepository;
import com.example.lab4server.repository.UserRepository;
import com.example.lab4server.utils.DtoCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/hits")
public class HitController {

    private final PointRepository pointRepository;

    @Autowired
    public HitController(PointRepository pointRepository) {
        this.pointRepository = pointRepository;
    }

    @GetMapping
    public ResponseEntity<Map<String, Object>> getAll(@RequestParam(name="page", defaultValue = "1") int page,
                                                    @RequestParam(name="limit", defaultValue = "10") int limit){
        Pageable paging = PageRequest.of(page-1, limit);
        Page<PointEntity> data  = pointRepository.findAll(paging);
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("currentPage", data.getNumber()+1);
        result.put("totalPages",data.getTotalPages());
        result.put("totalElements", data.getTotalElements());
        result.put("data", data.getContent());
        return ResponseEntity.ok(result);
    }


    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CreatePointDto createPointDto){
        PointDto pointDto = DtoCreator.createDto(createPointDto);
        pointDto.setHit("hit");
        pointDto.setDate(new SimpleDateFormat("dd.MM.yyyy hh:mm").format(new Date()));
        pointDto.setLeadTime(System.nanoTime());
        pointRepository.save(PointMapper.INSTANCE.toPointEntity(pointDto));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
