import React, { useEffect, useState } from 'react'
import { Container, Row, Col } from 'react-bootstrap'
import { Graph, Header, HitForm, HistoryTable } from '@/components'
import { useSelector, useDispatch } from 'react-redux'
import { hitHistorySelector } from '@/redux/selectors'
import { setPage, fetchData } from '@/redux/actions'

const Main = () => {
  const { data } = useSelector(hitHistorySelector)
  const [curData, setCurData] = useState([])
  const dispatch = useDispatch()
  useEffect(() => {
    dispatch(fetchData())
    setCurData(data)
  }, [])
  return (
    <>
      <Header />
      <main>
        <Container>
          <Row>
            <Col lg={6}>
              <Graph />
              <HitForm />
            </Col>
            <Col lg={6}>
              <HistoryTable data={curData} />
            </Col>
          </Row>
        </Container>
      </main>
    </>
  )
}

export default Main
