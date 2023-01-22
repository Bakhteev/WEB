import { createHit, refreshAuth } from '@/api'
import { CreateHitDto } from '@/models'
import { addHit, authSetAuthificated, setError } from '@/redux/actions'
import { authSelector } from '@/redux/selectors'
import { Action } from 'redux'
import { select, put, call } from 'redux-saga/effects'
export function* createHitWorker(action: Action<CreateHitDto>) {
  const { accessToken } = yield select(authSelector)
  console.log(action.payload)

  try {
    const { data } = yield call(createHit, action.payload, accessToken)
    console.log(data)

    yield put(addHit(data))
  } catch (e) {
    console.log(e)

    const { refreshToken } = yield select(authSelector)
    try {
      const { data } = yield call(refreshAuth, { refreshToken })
      console.log('here')
      yield put(authSetAuthificated(data))
      const { accessToken } = yield select(authSelector)
      const { data: hit } = yield call(createHit, action.payload, accessToken)
      yield put(addHit(hit))
    } catch (err) {
      yield put(setError(err.response.data.message))
    }
  }
}
