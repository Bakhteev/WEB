import React from 'react'
import { Container, Row, Text, Col } from 'react-bootstrap'
import styles from './style.module.scss'
import { Button } from '@yandex/ui/Button/desktop/bundle'
import { useDispatch } from 'react-redux'
import { authLogout } from '@/redux/actions'
import { useNavigate } from 'react-router-dom'

const Header = () => {
  const dispatch = useDispatch()
  const navigate = useNavigate()
  return (
    <header className={styles.header}>
      <Container className="bg-green ">
        <Row className="align-items-center">
          <Col lg={11}>
            <div className="d-flex justify-content-center ">
              <span className="me-3">Bakhteev Bogdan</span>
              <span className="me-3">Group: P32302</span>
              <span>Variant: 31699310</span>
            </div>
          </Col>
          <Col lg={1} className="row">
            <Button
              pin="round-breack"
              view="action"
              id="logout"
              size="m"
              className="d-flex justify-content-center text"
              onClick={() => {
                dispatch(authLogout())
                navigate('/auth/login')
              }}
            >
              Log out
            </Button>
          </Col>
        </Row>
      </Container>
    </header>
  )
}

export default Header
