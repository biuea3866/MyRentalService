import { createAction, handleActions } from "redux-actions";
import createRequestSaga, {
    createRequestActionTypes
} from "../lib/createRequestSaga";
import * as postsAPI from '../lib/api/posts';
import { takeLatest } from "redux-saga/effects";

const INITIALIZE = 'comment/INITIALIZE';
const CHANGE_FIELD = 'comment/CHANGE_FIELD';
const [
    COMMENT,
    COMMENT_SUCCESS,
    COMMENT_FAILURE,
] = createRequestActionTypes('comment/WRITE_COMMENT');

export const initialize = createAction(INITIALIZE);
export const changeField = createAction(CHANGE_FIELD, ({ key, value }) => ({
    key,
    value
}));
export const write = createAction(COMMENT, ({
    comment,
    writer,
    postId
}) => ({
    comment,
    writer,
    postId
}));

const writeSaga = createRequestSaga(COMMENT, postsAPI.writeComment);
export function* writeCommentSaga() {
    yield takeLatest(COMMENT, writeSaga);
}

const initialState = {
    comment: '',
    writer: '',
    postId: '',
    success: '',
    failure: '',
};

const writeComment = handleActions(
    {
        [INITIALIZE]: state => initialState,
        [CHANGE_FIELD]: (state, { payload: { key, value }}) => ({
            ...state,
            [key]: value,
        }),
        [COMMENT]: state => ({
            ...state,
            success: null,
            failure: null,
        }),
        [COMMENT_SUCCESS]: (state, { payload: success }) => ({
            ...state,
            success,
        }),
        [COMMENT_FAILURE]: (state, { payload: failure }) => ({
            ...state,
            failure,
        }),
    },
    initialState,
);

export default writeComment;