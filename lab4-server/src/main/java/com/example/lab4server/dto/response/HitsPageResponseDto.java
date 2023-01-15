package com.example.lab4server.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class HitsPageResponseDto {
    private long currentPage;
    private long totalPages;
    private long totalElements;
    private List<HitResponseDto> data;
}
