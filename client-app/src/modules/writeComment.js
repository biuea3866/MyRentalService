import { createAction, handleActions } from "redux-actions";
import createRequestSaga, {
    createRequestActionTypes
} from "../lib/createRequestSaga";
import * as postsAPI from '../lib/api/posts';
import { takeLatest } from "redux-saga/effects";

const INITIALIZE = 'comment/INITIALIZE';
const CHANGE_FIELD = 'comment/CHANGE_FIELD';
const [
    WRITE_COMMENT,
    WRITE_COMMENT_SUCCESS,
    WRITE_COMMENT_FAILURE,
] = createRequestActionTypes('comment/WRITE_COMMENT');

export const initialize = createAction(INITIALIZE);
export const changeField = createAction(CHANGE_FIELD, ({ key, value }) => ({
    key,
    value
}));
export const write = createAction(WRITE_COMMENT, ({
    postId,
    comment,
    writer,
}) => ({
    postId,
    comment,
    writer,
}));

const writeSaga = createRequestSaga(WRITE_COMMENT, postsAPI.writeComment);
export function* writeCommentSaga() {
    yield takeLatest(WRITE_COMMENT, writeSaga);
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
        [WRITE_COMMENT]: state => ({
            ...state,
            success: null,
            failure: null,
        }),
        [WRITE_COMMENT_SUCCESS]: (state, { payload: success }) => ({
            ...state,
            success,
        }),
        [WRITE_COMMENT_FAILURE]: (state, { payload: failure }) => ({
            ...state,
            failure,
        }),
    },
    initialState,
);

export default writeComment;