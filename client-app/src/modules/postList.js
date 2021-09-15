import { createAction, handleActions } from 'redux-actions';
import createRequestSaga, {
    createRequestActionTypes,
} from '../lib/createRequestSaga';
import * as postsAPI from '../lib/api/posts';
import { takeLatest } from 'redux-saga/effects';

const [
    READ_POSTLIST,
    READ_POSTLIST_SUCCESS,
    READ_POSTLIST_FAILURE,
] = createRequestActionTypes('posts/READ_POSTLIST');

export const readPostList = createAction(READ_POSTLIST);

const readPostListSaga = createRequestSaga(READ_POSTLIST, postsAPI.readAllPosts);
export function* postListSaga() {
    yield takeLatest(READ_POSTLIST, readPostListSaga);
}

const initialState = {
    postList: null,
    error: null,
};

const postList = handleActions(
    {
        [READ_POSTLIST_SUCCESS]: (state, { payload: postList }) => ({
            ...state,
            postList,
        }),
        [READ_POSTLIST_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error
        }),
    },
    initialState,
);

export default postList;