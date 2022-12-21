import './start.scss'
import { setDate } from './js/clock'
import {init} from './js/pages/auth/index.js'
setInterval(setDate, 1000)

init()
setDate()

