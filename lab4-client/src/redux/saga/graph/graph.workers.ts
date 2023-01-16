import { put } from 'redux-saga/effects'

export function* graphWorker(action) {
  console.log(action)
  yield put(setX(action.payload))
}
