import { HitsPageResponseDto } from '@/models/HitsPageResponseDto'
import { SET_PAGE, SET_DATA, FETCH_DATA, ADD_HIT } from './actionTypes'
import { createAction } from 'redux-actions'

export const setPage = createAction<number>(SET_PAGE)
export const setData = createAction<HitsPageResponseDto>(SET_DATA)
export const fetchData = createAction(FETCH_DATA)
export const addHit = createAction(ADD_HIT)
