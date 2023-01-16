export interface HitsPageResponseDto {
  currentPage: number
  totalPages: number
  totalElements: number
  data: HitResponseDto[]
}
