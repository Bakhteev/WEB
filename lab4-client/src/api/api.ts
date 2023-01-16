import { AuthenticatedUserResponseDto, RefreshResponseDto } from '@/models'
import {
  RefreshTokenRequestDto,
  HitsPageResponseDto,
  HitResponseDto,
} from '@/models/RefreshTokenRequestDto'
import axios from 'axios'

export const api = axios.create({
  baseURL: import.meta.env.VITE_API_URL,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json',
  },
})

export const login = async (dto: AuthDto) => {
  return await api.post<AuthenticatedUserResponseDto>('/auth/login', dto)
}

export const registration = async (dto: AuthDto) => {
  return await api.post<AuthenticatedUserResponseDto>('/auth/registration', dto)
}

export const logout = async (token: string) => {
  return await api.post<AuthenticatedUserResponseDto>('/auth/logout', null, {
    headers: { Authorization: 'Bearer ' + token },
  })
}

export const refreshAuth = async (dto: RefreshTokenRequestDto) => {
  return await api.post<RefreshResponseDto>('/auth/refresh', dto)
}

export const getHits = async (page: number, limit: number, token: string) => {
  return await api.get<HitsPageResponseDto>('/hits', {
    params: { page, limit },
    headers: { Authorization: 'Bearer ' + token },
  })
}

export const createHit = async (dto: CreateHitDto) => {
  return await api.post<HitResponseDto>('/hits', {
    headers: { Authorization: 'Bearer ' + token },
  })
}
