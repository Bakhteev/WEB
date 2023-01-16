import { Action } from 'redux'
import { CreateHitDto } from '@/models'
import { SET_HIT, SET_X, SET_Y, SET_R } from '@/redux/actions'
const initStore: CreateHitDto = {
  x: 0,
  y: 0,
  r: 1,
}

export const graphReducer = (
  store = initStore,
  { type, payload }: Action<number | CreateHitDto>
): CreateHitDto => {
  switch (type) {
    case SET_HIT:
      return { ...store, ...payload }
    case SET_X:
      return { ...store, x: payload }
    case SET_Y:
      return { ...store, y: payload }
    case SET_R:
      return { ...store, r: payload }
    default:
      return store
  }
}
