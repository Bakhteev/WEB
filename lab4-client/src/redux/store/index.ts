import { configureStore } from 'react-redux'
import { createStore } from 'redux'
import { composeWithDevTools } from '@redux-devtools/extension'
import { createRootReducer } from '../reducers'
import { applyMiddleware } from 'redux'
import createSagaMiddleware from 'redux-saga'
import { saga } from '../saga'

const configureStore = () => {
  const sagaMiddleware = createSagaMiddleware()
  const store = createStore(
    createRootReducer(),
    composeWithDevTools(applyMiddleware(sagaMiddleware))
  )
  sagaMiddleware.run(saga)
  return store
}
export const store = configureStore()
export type RootState = ReturnType<typeof store.getState>
