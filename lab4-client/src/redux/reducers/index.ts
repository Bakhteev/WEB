import { combineReducers } from 'redux'
import { graphReducer } from './graph.reducer'
import { hitHistoryReducer } from './hitHistore.reducer'
import { errorReducer } from './error.reducer'
import { authReducer } from './auth.reducer'
export const createRootReducer = () =>
  combineReducers({
    graph: graphReducer,
    hitHistory: hitHistoryReducer,
    error: errorReducer,
    auth: authReducer,
  })
