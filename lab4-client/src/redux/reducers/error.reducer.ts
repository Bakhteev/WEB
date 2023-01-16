import { SET_ERROR } from '@/redux/actions'

interface IErrorState {
  error: boolean
  errorMessage: string
}

const initialState: IErrorState = {
  error: false,
  errorMessage: '',
}

export const errorReducer = (
  state = initialState,
  { type, payload }: Action<string>
): IErrorState => {
  switch (type) {
    case SET_ERROR:
      return { ...state, error: true, errorMessage: payload }
    default:
      return state
  }
}
