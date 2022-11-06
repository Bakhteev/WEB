import { CENTER_OF_GRAPH } from './const.js'

export const convertXHumanReadable = (x, rValue) =>
  +(((x - CENTER_OF_GRAPH) / 100) * rValue).toFixed()
export const convertYHumanReadable = (y, rValue) =>
  +(-((y - CENTER_OF_GRAPH) / 100) * rValue).toFixed(2)

export const getYCoordinate = (e) => e.offsetY - 20 * (e.offsetY / 320)
export const getXCoordinate = (e) => e.offsetX - 20 * (e.offsetX / 320)

export const convertYToGraphCoord = (yValue, rValue) =>
  (-(yValue > 3 ? 3 : yValue) * 100) / rValue + CENTER_OF_GRAPH

export const convertXToGraphCoord = (xValue, rValue) =>
  (xValue * 100) / rValue + CENTER_OF_GRAPH

export const tableCreator = (data) => {
  let htmlTable = ''
  data.reverse().forEach((obj) => {
    htmlTable += createRow(obj)
  })

  return htmlTable
}

export const createRow = ({ x, y, r, currentTime, executionTime, hitted }) =>
  `
      <tr style='text-align: center;'>
        <td>${x}</td>
        <td>${y}</td>
        <td>${r}</td>
        <td>${hitted}</td>
        <td>${currentTime}</td>
        <td>${executionTime} ms</td>
      </tr>
    `
