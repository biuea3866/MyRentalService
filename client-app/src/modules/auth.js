import { createAction, handleActions } from "redux-actions";
import produce from 'immer';
import { takeLatest } from "@redux-saga/core/effects";
import createRequestSaga,{ 
    createRequestActionTypes 
} from "../lib/createRequestSaga";
import * as authAPI from '../lib/api/auth';

const CHANGE_FIELD = 'auth/CHANGE_FIELD';
const INITIALIZE_FORM = 'auth/INITIALIZE_FORM';

const [LOGIN, LOGIN_SUCCESS, LOGIN_FAILURE] = createRequestActionTypes('auth/LOGIN');
const [REGISTER, REGISTER_SUCCESS, REGISTER_FAILURE] = createRequestActionTypes('auth/REGISTER');
const [INFO, INFO_SUCCESS, INFO_FAILURE] = createRequestActionTypes('auth/INFO');

export const changeField = createAction(
    CHANGE_FIELD, ({
        form,
        key,
        value
    })  => ({
        form,
        key,
        value,
    }),
);

export const initializeForm = createAction(INITIALIZE_FORM, form => form);

export const login = createAction(LOGIN, ({ 
    email, 
    password 
}) => ({
    email,
    password
}));

export const register = createAction(REGISTER, ({
    email,
    password,
    nickname,
    phoneNumber
}) => ({
    email,
    password,
    nickname,
    phoneNumber
}));

export const info = createAction(INFO, userId => userId);

const loginSaga = createRequestSaga(LOGIN, authAPI.login);
const registerSaga = createRequestSaga(REGISTER, authAPI.register);
const infoSaga = createRequestSaga(INFO, authAPI.getUser);

export function* authSaga() {
    yield takeLatest(LOGIN, loginSaga);
    yield takeLatest(REGISTER, registerSaga);
    yield takeLatest(INFO, infoSaga);
}

const initialState = {
    register: {
        email: '',
        password: '',
        passwordConfirm: '',
        nickname: '',
        phoneNumber: '',
    },
    login: {
        email: '',
        password: '',
    },
    headers: null,
    auth: null,
    authError: null,
};

const auth = handleActions(
    {
        [CHANGE_FIELD]: (state, { payload: { form, key, value }}) => 
        produce(state, draft => {
            draft[form][key] = value;
        }),
        [INITIALIZE_FORM]: (state, { payload: form }) => ({
            ...state,
            [form]: initialState[form],
            authError: null,
        }),
        [LOGIN_SUCCESS]: (state, { payload: auth, headers: headers, }) => ({
            ...state,
            authError: null,
            auth,
            headers,
        }),
        [LOGIN_FAILURE]: (state, { payload: error }) => ({
            ...state,
            authError: error,
        }),
        [REGISTER_SUCCESS]: (state, { payload: auth }) => ({
            ...state,
            authError: null,
            auth,
        }),
        [REGISTER_FAILURE]: (state, { payload: error }) => ({
            ...state,
            authError: error,
        }),
        [INFO_SUCCESS]: (state, { payload: auth }) => ({
            ...state,
            authError: null,
            auth,
        }),
        [INFO_FAILURE]: (state, { payload: error }) => ({
            ...state,
            authError: error,
        })
    },
    initialState,
);

export default auth;