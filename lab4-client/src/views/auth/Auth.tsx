import React, { FormEvent, useState } from 'react'
import { Textinput } from '@yandex/ui/Textinput/desktop/bundle'
import { Button } from '@yandex/ui/Button/desktop/bundle'
import { useDispatch } from 'react-redux'
import { authLogin, authRegistration } from '@/redux/actions/auth'
import { AuthDto } from '@/models'
import { useNavigate } from 'react-router-dom'

const Auth = ({ text, login }) => {
  const dispatch = useDispatch()
  const navigate = useNavigate()
  const [auth, setAuth] = useState<AuthDto>()

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault()
    login ? dispatch(authLogin(auth)) : dispatch(authRegistration(auth))
  }

  return (
    <>
      <div
        className=""
        style={{ position: 'absolute', top: '30px', left: 30, zIndex: 10 }}
      >
        test2@mail.ru <br />
        1223456789
      </div>
      <div className="d-flex justify-content-center align-items-center vh-100 flex-column">
        <h1>{text}</h1>

        <form onSubmit={handleSubmit}>
          <Textinput
            required
            size="m"
            view="material"
            label="Email"
            type="text"
            state=""
            placeholder="Email"
            name="email"
            // className="form__input"
            hasClear
            onChange={(e) =>
              setAuth((prev) => ({ ...prev, [e.target.name]: e.target.value }))
            }
            // onInput={(event) => }
            // onClearClick={() => }
          />
          <Textinput
            required
            size="m"
            view="material"
            label="Password"
            type="password"
            state=""
            name="password"
            placeholder="password"
            onChange={(e) =>
              setAuth((prev) => ({ ...prev, [e.target.name]: e.target.value }))
            }
            // className="form__input"
            hasClear
            // onInput={(event) => }
            // onClearClick={() => }
          />
          <div className="d-flex justify-content-center mt-3">
            <Button
              pin="round-breack"
              view="action"
              size="l"
              width="max"
              type="submit"
              className="d-flex justify-content-center text me-3"
            >
              Submit
            </Button>
            <Button
              pin="round-breack"
              view="link"
              size="l"
              width="max"
              type="link"
              className="d-flex justify-content-center text"
              onClick={() =>
                navigate(login ? '/auth/registration' : '/auth/login')
              }
            >
              {login ? 'Registration' : 'Login'}
            </Button>
          </div>
        </form>
      </div>
    </>
  )
}

export default Auth
