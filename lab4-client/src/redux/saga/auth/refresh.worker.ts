import { Action } from 'redux'
import { call, select, put } from 'redux-saga/effects'
import { refreshAuth } from '@/api'
import { authSelector } from '@/redux/selectors'
import { AuthenticatedUserResponseDto } from '@/models'
import { authSetAuthificated, setError } from '@/redux/actions'

export function* refreshWorker(action: Action) {
  const { refreshToken } = yield select(authSelector)
  try {
    const { data } = yield call(refreshAuth, { refreshToken })
    console.log(data)

    sessionStorage.setItem('access', data.accessToken)
    sessionStorage.setItem('refresh', data.refreshToken)
    yield put(authSetAuthificated(data))
  } catch (e) {
    console.log(e)
    // yield put(setError(e.response.data.message))
  }
}
