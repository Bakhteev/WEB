import { HitResponseDto } from '@/models'
import { SET_PAGE, SET_DATA, ADD_HIT } from '@/redux/actions'
import { Action } from 'redux'
export interface IHitHistoryStore {
  currentPage: number
  totalPages: number
  totalElements: number
  data: HitResponseDto[]
  limit: number
}

const initialState: IHitHistoryStore = {
  currentPage: 1,
  totalPages: 20,
  totalElements: 10,
  limit: 10,
  data: [
    // {
    //   id: 1,
    //   xValue: 1,
    //   yValue: 1,
    //   rValue: 1,
    //   hit: true,
    //   date: '16.01.2023 7:46',
    //   leadTime: 10,
    // },
    // {
    //   id: 2,
    //   xValue: 1,
    //   yValue: 1,
    //   rValue: 1,
    //   hit: true,
    //   date: '16.01.2023 7:46',
    //   leadTime: 10,
    // },
  ],
}

export const hitHistoryReducer = (
  state = initialState,
  { type, payload }: Action
): IHitHistoryStore => {
  switch (type) {
    case SET_PAGE:
      return { ...state, currentPage: payload }
    case SET_DATA:
      return { ...state, data: [...state.data, ...payload.data] }
    case ADD_HIT:
      return { ...state, data: [...state.data, payload] }
    default:
      return state
  }
}
