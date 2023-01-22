import { CreateHitDto } from '@/models'
import { Action, PayloadAction } from 'redux'
import { createAction } from 'redux-actions'
import { SET_HIT, SET_X, SET_Y, SET_R, CREATE_HIT } from './actionTypes'

export const setHit = createAction<CreateHitDto>(SET_HIT)
export const setX = createAction<number>(SET_X)
export const setY = createAction<number>(SET_Y)
export const setR = createAction<number>(SET_R)
export const createHit = createAction<CreateHitDto>(CREATE_HIT)