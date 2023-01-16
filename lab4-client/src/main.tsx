import React from 'react'
import ReactDOM from 'react-dom'
import App from './App'
import { configureRootTheme } from '@yandex/ui/Theme'
import { theme } from '@yandex/ui/Theme/presets/default'
import { Provider } from 'react-redux'
import { store } from '@/redux/store'
import '@/styles/index.scss'
configureRootTheme({ theme })

ReactDOM.render(
  <Provider store={store}>
    <App />
  </Provider>,

  document.getElementById('root')
)
