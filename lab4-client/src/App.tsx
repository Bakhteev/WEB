import { useState } from 'react'
import { compose } from '@bem-react/core'
import { Button } from '@yandex/ui/Button/desktop/bundle'
import { Header, Graph } from './components'
import { Main, Auth } from '@/views'
import { useSelector } from 'react-redux'
import { authSelector } from '@/redux/selectors'
import {
  BrowserRouter as Router,
  Link,
  Routes,
  Route,
  Navigate,
} from 'react-router-dom'

const App = () => {
  const { user } = useSelector(authSelector)
  return (
    <Router>
      {/* <div className="d-flex p-3 justify-content-center">
        <Link to={'/'} className="me-2">
          Main
        </Link>
        <Link to={'/auth/login'} className="me-2">
          login
        </Link>
        <Link to={'/auth/registration'}>registration</Link>
      </div> */}
      <Routes>
        {!user ? (
          <>
            <Route path="/" element={<Main />} />
            <Route path="*" element={<Navigate to="/" replace />} />
          </>
        ) : (
          <>
            <Route
              path="/auth/login"
              element={<Auth text={'Login'} login={true} />}
            />
            <Route
              path="/auth/registration"
              element={<Auth text={'Registration'} login={false} />}
            />
            <Route path="*" element={<Navigate to="/auth/login" replace />} />
          </>
        )}
      </Routes>
    </Router>
  )
}

export default App
