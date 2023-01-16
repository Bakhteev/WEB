import { UserDto } from './UserDto'

export interface AuthenticatedUserResponseDto {
  accessToken: string
  refreshToken: string
  type: string
  user: UserDto
}
