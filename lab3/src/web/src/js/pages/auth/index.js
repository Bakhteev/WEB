import {animatedInputs} from './inputAnimation.js'
import {showPopUp} from '../../common/popup.js'

const popUpCloseBtn = document.querySelector('.popup__close')

const baseURL = 'http://localhost:8000'
export const init = () => {


    animatedInputs()
    const form = document.querySelector('#auth-form')
    form.addEventListener('submit', (e) => {
        e.preventDefault()
        const email = form.email.value
        const password = form.password.value
        const to = form.getAttribute('action')
        ;(async () => {
            const res = await fetch(baseURL + to, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json;charset=utf-8',
                },
                body: JSON.stringify({email, password}),
            })
            const data = await res.text()
            if (!res.ok) {
                showPopUp(true, data)
            } else {
                location.href = baseURL
            }
        })()
    })

    popUpCloseBtn.addEventListener('click', () => {
        showPopUp(false)
    })
}
