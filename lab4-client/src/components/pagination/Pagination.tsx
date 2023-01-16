import React, { useState } from 'react'
import { Pagination } from 'react-bootstrap'
interface IProps {
  currentPage: number
  totalPages: number
  clickHandler: (page: number) => void
}

const Paginator = ({ currentPage, totalPages, clickHandler }: IProps) => {
  let pagesItems = []
  for (let i = 1; i <= totalPages; i++) {
    pagesItems.push(i)
  }
  pagesItems = pagesItems.splice(currentPage - 1, 5)

  return (
    <Pagination>
      <Pagination.First
        disabled={currentPage <= 1}
        onClick={() => clickHandler(1)}
      />
      <Pagination.Prev
        disabled={currentPage <= 1}
        onClick={() => clickHandler(currentPage - 1)}
      />
      {pagesItems.map((item, index) => (
        <Pagination.Item
          active={item === currentPage}
          key={[item, index]}
          onClick={() => clickHandler(item)}
        >
          {item}
        </Pagination.Item>
      ))}
      <Pagination.Next
        disabled={totalPages === currentPage}
        onClick={() => clickHandler(currentPage + 1)}
      />
      <Pagination.Last
        disabled={totalPages === currentPage}
        onClick={() => clickHandler(totalPages)}
      />
    </Pagination>
  )
}

export default Paginator
