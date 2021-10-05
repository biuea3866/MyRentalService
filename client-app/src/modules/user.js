import { createAction, handleActions } from "redux-actions";
import { call, takeLatest } from "redux-saga/effects";
import * as authAPI from '../lib/api/auth';
import createRequestSaga, {
    createRequestActionTypes,
} from "../lib/createRequestSaga";

const SAVE_USER = 'user/SAVE_USER';
const [CHECK, CHECK_SUCCESS, CHECK_FAILURE] = createRequestActionTypes('user/CHECK');
const LOGOUT = 'user/LOGOUT';

export const saveUser = createAction(SAVE_USER, user => user);
export const check = createAction(CHECK, userId => userId);
export const logout = createAction(LOGOUT);

const checkSaga = createRequestSaga(CHECK, authAPI.check);

function checkFailureSaga() {
    try {
        localStorage.removeItem('user');
    } catch(e) {
        console.log('localStorage is not working');
    }
}

function* logoutSaga() {
    try {
        yield call(authAPI.logout);
        
        console.log(1);
        
        localStorage.removeItem('user');
    } catch(e) {
        console.log(e);
    }
}

export function* userSaga() {
    yield takeLatest(CHECK, checkSaga);
    yield takeLatest(CHECK_FAILURE, checkFailureSaga);
    yield takeLatest(LOGOUT, logoutSaga);
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
        [LOGOUT]: state => ({
            ...state,
            user: null
        }),
    },
    initialState,
);