import { HitsPageResponseDto } from '@/models/HitsPageResponseDto'
import {
  SET_PAGE,
  SET_DATA,
  FETCH_DATA,
} from '@/redux/actions/hitHistory/actionTypes'
import { createAction } from 'redux-actions'

export const setPage = createAction<number>(SET_PAGE)
export const setData = createAction<HitsPageResponseDto>(SET_DATA)
export const fetchData = createAction(FETCH_DATA)