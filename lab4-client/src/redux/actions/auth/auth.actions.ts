import { createAction } from 'redux-actions'
import {
  AuthDto,
  AuthenticatedUserResponseDto,
  RefreshResponseDto,
} from '@/models'
import {
  AUTH_LOGIN,
  AUTH_REGISTRATION,
  AUTH_SET_AUTHIFICATED,
  AUTH_LOGOUT,
  AUTH_REFRESH,
} from '@/redux/actions/auth/actionTypes'

export const authLogin = createAction<AuthDto>(AUTH_LOGIN)
export const authRegistration = createAction<AuthDto>(AUTH_REGISTRATION)
export const authSetAuthificated = createAction<
  AuthenticatedUserResponseDto | RefreshResponseDto
>(AUTH_SET_AUTHIFICATED)
export const authLogout = createAction(AUTH_LOGOUT)
export const authRefresh = createAction(AUTH_REFRESH)
