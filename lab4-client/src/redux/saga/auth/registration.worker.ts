import { call, put } from 'redux-saga/effects'
import { registration } from '@/api'
import { AuthDto } from '@/models'
import { authSetAuthificated, setError } from '@/redux/actions'

export function* registrationWorker(action: Action<AuthDto>) {
  try {
    const { data } = yield call(registration, action.payload)
    sessionStorage.setItem('access', data.accessToken)
    sessionStorage.setItem('refresh', data.refreshToken)
    yield put(authSetAuthificated(data))
  } catch (e) {
    console.log(e)
    yield put(setError(e.response.data.message))
  }
}
