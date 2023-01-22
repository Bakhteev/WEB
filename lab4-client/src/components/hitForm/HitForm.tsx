import React, { FormEvent } from 'react'
import { Textinput } from '@yandex/ui/Textinput/desktop/bundle'
import { Slider, useSliderState } from '@yandex/ui/Slider/desktop/bundle'
import { Radiobox, Radio } from '@yandex/ui/Radiobox/desktop/bundle'
import { Button } from '@yandex/ui/Button/desktop/bundle'
import { useSelector, useDispatch } from 'react-redux'
import { graphSelector } from '@/redux/selectors'
import { setR, setX, setY, addHit, createHit } from '@/redux/actions'
import './styles.scss'

const rValues = [1, 2, 3, 4, 5]

const HitForm = () => {
  const { y, x, r } = useSelector(graphSelector)
  const xState = useSliderState({ value: [x] })
  const dispatch = useDispatch()

  const handleSubmit = (e: FormEvent) => {
    e.preventDefault()
    dispatch(createHit({ x, y, r }))
  }
  return (
    <form onSubmit={handleSubmit}>
      <div className="">
        <p>X Value: {x}</p>
        <Slider
          view="default"
          // onInput={(e) => {
          //   console.log(e)

          //   // dispatch(setX(e))
          // }}
          // onInput={(e) => (e, x) => }
          {...xState}
          value={[x]}
          // value={[x]}
          showTickValues={true}
          min={-5}
          max={5}
          step={0.1}
          onChange={(e) => {
            // console.log(e)
            // xState.value[0] = x
            dispatch(setX(xState.value[0]))
          }}
        />
      </div>
      <Textinput
        size="m"
        view="material"
        label="Y value"
        type="number"
        state=""
        placeholder="Y value must be from -5 to 5"
        className="form__input"
        hasClear
        value={!y ? '' : y}
        onInput={(event) => dispatch(setY(+event.target.value))}
        onClearClick={() => dispatch(setY(0))}
      />
      <div className="">
        <p>R value</p>
        <Radiobox
          size="m"
          view="default"
          value={r}
          onChange={(e) => dispatch(setR(+e.target.value))}
        >
          {rValues.map((value, index) => (
            <Radio
              key={[value, index]}
              value={value}
              onClick={() => dispatch(setR(value))}
              className="me-3"
            >
              {value}
            </Radio>
          ))}
        </Radiobox>
        <Button
          pin="round-breack"
          view="action"
          id="logout"
          size="l"
          type="submit"
          className="d-flex justify-content-center text mt-3"
        >
          Submit
        </Button>
      </div>
    </form>
  )
}

export default HitForm
