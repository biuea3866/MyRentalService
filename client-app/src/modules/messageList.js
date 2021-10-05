import { createAction, handleActions } from 'redux-actions';
import createRequestSaga, {
    createRequestActionTypes,
} from '../lib/createRequestSaga';
import * as chatAPI from '../lib/api/chat';
import { takeLatest } from 'redux-saga/effects';

const CHANGE_FIELD = 'messages/CHANGE_FIELD';
const [
    READ_CHATLIST,
    READ_CHATLIST_SUCCESS,
    READ_CHATLIST_FAILURE,
] = createRequestActionTypes('messages/READ_CHATLIST');
const [
    READ_USERLIST,
    READ_USERLIST_SUCCESS,
    READ_USERLIST_FAILURE,
] = createRequestActionTypes('messages/READ_USERLIST');

export const changeField = createAction(CHANGE_FIELD, ({ key, value }) => ({
    key,
    value
}));
export const readChatList = createAction(READ_CHATLIST, ({
    receiver,
    sender
}) => ({
    receiver,
    sender,
}));
export const readUserList = createAction(READ_USERLIST, sender => sender);

const readChatListSaga = createRequestSaga(READ_CHATLIST, chatAPI.getMessageList);
const readUserListSaga = createRequestSaga(READ_USERLIST, chatAPI.getUserList);

export function* messageListSaga() {
    yield takeLatest(READ_CHATLIST, readChatListSaga);
    yield takeLatest(READ_USERLIST, readUserListSaga);
}

const initialState = {
    userList: null,
    chatList: null,
    error: null,
};

const messageList = handleActions(
    {
        [CHANGE_FIELD]: (state, { payload: { key, value }}) => ({
            ...state,
            [key]: value,
        }),
        [READ_CHATLIST_SUCCESS]: (state, { payload: chatList }) => ({
            ...state,
            chatList,
        }),
        [READ_CHATLIST_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error
        }),
        [READ_USERLIST_SUCCESS]: (state, { payload: userList }) => ({
            ...state,
            userList,
        }),
        [READ_USERLIST_FAILURE]: (state, { payload: error }) => ({
            ...state,
            error,
        }),
    },
    initialState,
);

export default messageList;