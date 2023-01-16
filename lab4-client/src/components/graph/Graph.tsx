import React, { useRef, useState, MouseEvent } from 'react'
import { useSelector, useDispatch } from 'react-redux'
import { graphSelector } from '@/redux/selectors'
import './styles.scss'
import { RootState } from '@/redux/store'
import { CreateHitDto } from '@/models'
import { coordConverter } from '@/utils'
import { setX, setY } from '@/redux/actions'
const limits = {
  xMax: 5,
  xMin: -5,
  yMax: 5,
  yMin: -5,
  rMax: 5,
  rMin: 1,
}

const Graph = () => {
  const { x, y, r }: CreateHitDto = useSelector(graphSelector)
  const dispatch = useDispatch()
  const _yLine = useRef()
  const _xLine = useRef()
  const _dot = useRef()

  const setDot = (x: number, y: number) => {
    _dot.current.setAttribute('cx', x)
    _dot.current.setAttribute('cy', y)
    _dot.current.classList.remove('inactive')
  }

  const handleMouseMove = (e: MouseEvent) => {
    const { current: yline } = _yLine
    yline.setAttribute('stroke', 'red')
    const coordY = e.nativeEvent.offsetY - 20 * (e.nativeEvent.offsetY / 320)
    if (r) {
      const highLimit = (limits.yMax * 100) / r
      const lowLimit = -((limits.yMin * 100) / r)

      if (coordY > 150) {
        yline.setAttribute(
          'y1',
          coordY <= 150 + lowLimit ? coordY : 150 + lowLimit
        )
        yline.setAttribute(
          'y2',
          coordY <= 150 + lowLimit ? coordY : 150 + lowLimit
        )
      } else {
        yline.setAttribute(
          'y1',
          coordY >= 150 - highLimit ? coordY : 150 - highLimit
        )
        yline.setAttribute(
          'y2',
          coordY >= 150 - highLimit ? coordY : 150 - highLimit
        )
      }
    } else {
      yline.setAttribute('y1', coordY)
      yline.setAttribute('y2', coordY)
    }
    const { current: xLine } = _xLine
    const coordX: number =
      e.nativeEvent.offsetX - 20 * (e.nativeEvent.offsetX / 320)
    xLine.setAttribute('stroke', 'red')
    if (r) {
      const highLimit = (limits.xMax * 100) / r
      const lowLimit = -((limits.xMin * 100) / r)
      if (coordX > 150) {
        xLine.setAttribute(
          'x1',
          coordX <= 150 + highLimit ? coordX : 150 + highLimit
        )
        xLine.setAttribute(
          'x2',
          coordX <= 150 + highLimit ? coordX : 150 + highLimit
        )
      } else {
        xLine.setAttribute(
          'x1',
          coordX >= 150 - lowLimit ? coordX : 150 - lowLimit
        )
        xLine.setAttribute(
          'x2',
          coordX >= 150 - lowLimit ? coordX : 150 - lowLimit
        )
      }
    } else {
      xLine.setAttribute('x1', coordX)
      xLine.setAttribute('x2', coordX)
    }
  }

  const handleClick = (e: MouseEvent) => {
    const { current: yLine } = _yLine
    const { current: xLine } = _xLine
    setDot(xLine.getAttribute('x1'), yLine.getAttribute('y1'))
    const xConverted = coordConverter(+xLine.getAttribute('x1'), r)
    const yConverted = coordConverter(+yLine.getAttribute('y1'), r, false)
    dispatch(setX(xConverted))
    dispatch(setY(yConverted))
  }

  const handleMouseLeave = (e: MouseEvent) => {
    const { current: yLine } = _yLine
    const { current: xLine } = _xLine
    yLine.setAttribute('stroke', 'transparent')
    xLine.setAttribute('stroke', 'transparent')
  }

  return (
    <div>
      <svg
        onMouseLeave={handleMouseLeave}
        onClick={handleClick}
        onMouseMove={handleMouseMove}
        style={{ border: '1px solid #000', boxSizing: 'border-box' }}
        id="graph"
        viewBox="0 0 300 300"
        xmlns="http://www.w3.org/2000/svg"
      >
        {/* <!-- circle --> */}
        <path
          className="graph-shape"
          d="M 50 150 A 100 100 0 0 0 150 250 L 150 150 Z"
        />

        {/* <!-- triangle --> */}
        <polygon className="graph-shape" points="150,100 150,150 100,150" />

        {/* <!-- rectangle --> */}
        <polygon
          className="graph-shape"
          points="250,200 250,150 150,150 150,200"
        />

        {/* <!-- axles --> */}
        <text className="graph-axle-text" x="290" y="140">
          x
        </text>
        <line className="graph-axle-line" x1="0" x2="295" y1="150" y2="150" />
        <polygon
          className="graph-axle-arrow"
          points="299,150 290,155 290,145"
        />

        <text className="graph-axle-text" x="160" y="10">
          y
        </text>
        <line className="graph-axle-line" x1="150" x2="150" y1="5" y2="300" />
        <polygon className="graph-axle-arrow" points="150,1 145,10 155,10" />

        {/* <!-- points --> */}
        <line className="graph-point" x1="50" x2="50" y1="145" y2="155" />
        <line className="graph-point" x1="100" x2="100" y1="145" y2="155" />
        <line className="graph-point" x1="200" x2="200" y1="145" y2="155" />
        <line className="graph-point" x1="250" x2="250" y1="145" y2="155" />

        <line className="graph-point" x1="145" x2="155" y1="250" y2="250" />
        <line className="graph-point" x1="145" x2="155" y1="200" y2="200" />
        <line className="graph-point" x1="145" x2="155" y1="100" y2="100" />
        <line className="graph-point" x1="145" x2="155" y1="50" y2="50" />

        {/* <!-- labels --> */}
        <text
          className="graph-label r-whole-neg"
          textAnchor="middle"
          x="50"
          y="140"
        >
          {-r}
        </text>
        <text
          className="graph-label r-half-neg"
          textAnchor="middle"
          x="100"
          y="140"
        >
          {-r / 2}
        </text>
        <text
          className="graph-label r-half-pos"
          textAnchor="middle"
          x="200"
          y="140"
        >
          {r / 2}
        </text>
        <text
          className="graph-label r-whole-pos"
          textAnchor="middle"
          x="250"
          y="140"
        >
          {r}
        </text>

        <text
          className="graph-label r-whole-neg"
          textAnchor="start"
          x="160"
          y="255"
        >
          {-r}
        </text>
        <text
          className="graph-label r-half-neg"
          textAnchor="start"
          x="160"
          y="205"
        >
          {-r / 2}
        </text>
        <text
          className="graph-label r-half-pos"
          textAnchor="start"
          x="160"
          y="105"
        >
          {r / 2}
        </text>
        <text
          className="graph-label r-whole-pos"
          textAnchor="start"
          x="160"
          y="55"
        >
          {r}
        </text>
        <line
          x1="0"
          y1="150"
          x2="300"
          y2="150"
          stroke="black"
          strokeDasharray="3,3"
          id="y-line"
          ref={_yLine}
        />
        <line
          x1="150"
          y1="300"
          x2="150"
          y2="0"
          stroke="transparent"
          strokeDasharray="3,3"
          ref={_xLine}
          id="x-line"
        />
        <circle
          cx={(x / r) * 100 + 150}
          cy={(-y / r) * 100 + 150}
          r="2"
          className="dot dot-active"
          ref={_dot}
        />
        <g id="render-dots">
          {/* <ui:repeat value="#{pointBeanWorker.pointsState}" var="mark">
                <circle cx="#{mark.XValue/mark.RValue * 100 + 150}" cy="#{-mark.YValue/mark.RValue * 100 + 150}" r="2"
                        className="#{mark.hit.equals('hit')?'dot-hit':'dot-miss'}"/>
            </ui:repeat> */}
        </g>
      </svg>
    </div>
  )
}

export default Graph
