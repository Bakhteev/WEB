import React, { useEffect, useState } from 'react'
import { Table } from 'react-bootstrap'
import { useSelector, useDispatch } from 'react-redux'
import { hitHistorySelector } from '@/redux/selectors'
import { setPage, fetchData } from '@/redux/actions'
import { Paginator } from '@/components'

const HistoryTable = ({ data = [] }) => {
  let { totalPages, currentPage } = useSelector(hitHistorySelector)

  const handleClick = (page) => {
    dispatch(setPage(page < 1 ? currentPage : page))
  }

  return (
    <>
      <Table striped bordered hover>
        <thead>
          <tr>
            <th>X value</th>
            <th>Y value</th>
            <th>R value</th>
            <th>Area hit</th>
            <th>Date</th>
            <th>Lead time</th>
          </tr>
        </thead>
        <tbody>
          {data.length ? (
            data.map((item) => (
              <tr key={item.id}>
                <td>{item.xvalue}</td>
                <td>{item.yvalue}</td>
                <td>{item.rvalue}</td>
                <td>{item.hit ? 'hit' : 'miss'}</td>
                <td>{item.date}</td>
                <td>{item.leadTime} ms</td>
              </tr>
            ))
          ) : (
            <tr>
              <td className="table-disabled stext-center" colSpan="6">
                No hits
              </td>
            </tr>
          )}
        </tbody>
      </Table>
      <Paginator
        currentPage={currentPage}
        totalPages={totalPages}
        clickHandler={handleClick}
      />
    </>
  )
}

export default HistoryTable
