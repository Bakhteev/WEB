import { takeEvery } from 'redux-saga/effects'
import { FETCH_DATA, SET_X } from '@/redux/actions'
import { graphWorker } from '@/redux/saga/graph/graph.workers'
import {
  AUTH_LOGIN,
  AUTH_LOGOUT,
  AUTH_REFRESH,
  AUTH_REGISTRATION,
  FETCH_DATA,
} from '@/redux/actions'
import {
  loginWorker,
  registrationWorker,
  logoutWorker,
  refreshWorker,
} from '@/redux/saga/auth'
import { getAllHitsWorker } from './hitHistory'
export const saga = function* () {
  yield takeEvery(AUTH_LOGIN, loginWorker)
  yield takeEvery(AUTH_REGISTRATION, registrationWorker)
  yield takeEvery(AUTH_LOGOUT, logoutWorker)
  yield takeEvery(FETCH_DATA, getAllHitsWorker)
}
