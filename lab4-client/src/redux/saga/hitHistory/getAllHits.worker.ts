import { getHits, refreshAuth } from '@/api'
import { setData, setError } from '@/redux/actions'
import { authSelector, hitHistorySelector } from '@/redux/selectors'
import { Action } from 'redux'
import { call, put, select } from 'redux-saga/effects'

export function* getAllHitsWorker(action: Action) {
  const { accessToken } = yield select(authSelector)
  const { currentPage, limit } = yield select(hitHistorySelector)
  try {
    const { data } = yield call(getHits, currentPage, limit, accessToken)
    yield put(setData(data))
  } catch (e) {
    const { refreshToken } = yield select(authSelector)
    try {
      const { data } = yield call(refreshAuth, { refreshToken })
      yield put(authSetAuthificated(data))
      const { accessToken } = yield select(authSelector)
      const { data: hits } = yield call(
        getHits,
        currentPage,
        limit,
        accessToken
      )
      yield put(setData(hits))
    } catch (err) {
      yield put(setError(e.response.data.message))
    }
  }
}
