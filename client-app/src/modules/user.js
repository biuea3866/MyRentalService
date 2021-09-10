import { createAction, handleActions } from "redux-actions";
import { takeLatest } from "redux-saga/effects";
import * as authAPI from '../lib/api/auth';
import createRequestSaga, {
    createRequestActionTypes,
} from "../lib/createRequestSaga";

const SAVE_USER = 'user/SAVE_USER';
const [CHECK, CHECK_SUCCESS, CHECK_FAILURE] = createRequestActionTypes('user/CHECK');

export const saveUser = createAction(SAVE_USER, user => user);
export const check = createAction(CHECK, userId => userId);

const checkSaga = createRequestSaga(CHECK, authAPI.check);

function checkFailureSaga() {
    try {
        localStorage.removeItem('user');
    } catch(e) {
        console.log('localStorage is not working');
    }
}

export function* userSaga() {
    yield takeLatest(CHECK, checkSaga);
    yield takeLatest(CHECK_FAILURE, checkFailureSaga);
}

const initialState = {
    user: null,
    checkError: null,
};

export default handleActions(
    {
        [SAVE_USER]: (state, { payload: user }) => ({
            ...state,
            user,
        }),
        [CHECK_SUCCESS]: (state, { payload: user }) => ({
            ...state,
            user,
            checkError: null,
        }),
        [CHECK_FAILURE]: (state, { payload: error }) => ({
            ...state,
            user: null,
            checkError: error,
        }),
    },
    initialState,
);