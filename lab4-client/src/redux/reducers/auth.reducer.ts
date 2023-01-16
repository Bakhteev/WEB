import { AUTH_LOGOUT, AUTH_SET_AUTHIFICATED } from '@/redux/actions'

const initialState: AuthenticatedUserResponseDto = {
  accessToken: '',
  refreshToken: '',
  type: '',
  user: null,
}

export const authReducer = (
  state = initialState,
  { type, payload }: Action<AuthenticatedUserResponseDto | RefreshResponseDto>
): AuthenticatedUserResponseDto => {
  switch (type) {
    case AUTH_SET_AUTHIFICATED:
      return { ...state, ...payload }
    default:
      return state
  }
}
