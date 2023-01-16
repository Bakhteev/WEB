import { login } from '@/api'
import { AuthDto, AuthenticatedUserResponseDto } from '@/models'
import { call, put } from 'redux-saga/effects'
import { authSetAuthificated, setError } from '@/redux/actions'

export function* loginWorker(action: Action<AuthDto>) {
  try {
    const { data } = yield call(login, action.payload)
    sessionStorage.setItem('access', data.accessToken)
    sessionStorage.setItem('refresh', data.refreshToken)
    yield put(authSetAuthificated(data))
  } catch (err) {
    yield put(setError(e.response.data.message))
  }
}
