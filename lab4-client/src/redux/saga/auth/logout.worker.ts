import { logout, refreshAuth } from '@/api'
import { Action } from 'redux-actions'
import { call, put, select } from 'redux-saga/effects'
import {
  authSetAuthificated,
  authLogout,
  authRefresh,
} from '@/redux/actions/auth'
import { authSelector } from '@/redux/selectors'
import { setError } from '@/redux/actions'
export function* logoutWorker(action: Action) {
  try {
    const { accessToken } = yield select(authSelector)
    yield call(logout, accessToken)
    yield put(
      authSetAuthificated({
        accessToken: '',
        refreshToken: '',
        type: '',
        user: null,
      })
    )
    sessionStorage.removeItem('access')
    sessionStorage.removeItem('refresh')
  } catch (e) {
    const { refreshToken } = yield select(authSelector)
    try {
      const { data } = yield call(refreshAuth, { refreshToken })
      yield put(authSetAuthificated(data))
      const { accessToken } = yield select(authSelector)
      yield call(logout, accessToken)
      yield put(
        authSetAuthificated({
          accessToken: '',
          refreshToken: '',
          type: '',
          user: null,
        })
      )
      sessionStorage.removeItem('access')
      sessionStorage.removeItem('refresh')
    } catch (err) {
      yield put(setError(err.response.data.message))
    }
  }
}
