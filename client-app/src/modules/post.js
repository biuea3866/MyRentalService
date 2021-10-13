import { createAction, handleActions } from 'redux-actions';
import createRequestSaga, {
    createRequestActionTypes,
} from '../lib/createRequestSaga';
import * as postsAPI from '../lib/api/posts';
import { takeLatest } from 'redux-saga/effects';

const [
    READ_POST,
    READ_POST_SUCCESS,
    READ_POST_FAILURE,
] = createRequestActionTypes('post/READ_POST');

const UNLOAD_POST = 'post/UNLOAD_POST';

const [
    ROLLBACK_POST,
    ROLLBACK_POST_SUCCESS,
    ROLLBACK_POST_FAILURE,
] = 'post/ROLLBACK_POST';

export const readPost = createAction(READ_POST, postId => postId);
export const unloadPost = createAction(UNLOAD_POST);
export const rollbackPost = createAction(ROLLBACK_POST, postId => postId);

const readPostSaga = createRequestSaga(READ_POST, postsAPI.readPostById);
const rollbackPostSaga = createRequestSaga(ROLLBACK_POST, postsAPI.rollbackStatus);

export function* postSaga() {
    yield takeLatest(READ_POST, readPostSaga);
    yield takeLatest(ROLLBACK_POST, rollbackPostSaga);
}

const initialState = {
    post: null,
    error: null,
    message: null,
};

const post = handleActions(
    {
        [READ_POST_SUCCESS]: (state, { payload: post }) => ({
            ...state,
            post,
        }),
        [READ_POST_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error
        }),
        [UNLOAD_POST]: () => initialState,
        [ROLLBACK_POST_SUCCESS]: (state, { payload: message }) => ({
            ...state,
            message,
        }),
        [ROLLBACK_POST_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error,
        }),
    },
    initialState,
);

export default post;