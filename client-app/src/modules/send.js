import { createAction, handleActions } from "redux-actions";
import createRequestSaga, {
    createRequestActionTypes
} from "../lib/createRequestSaga";
import * as chatAPI from '../lib/api/chat';
import { takeLatest } from "redux-saga/effects";

const INITIALIZE = 'send/INITIALIZE';
const CHANGE_FIELD = 'send/CHANGE_FIELD';
const [
    SEND_CHAT,
    SEND_CHAT_SUCCESS,
    SEND_CHAT_FAILURE,
] = createRequestActionTypes('send/SEND_CHAT');

export const initialize = createAction(INITIALIZE);
export const changeField = createAction(CHANGE_FIELD, ({ key, value }) => ({
    key,
    value
}));
export const sendChat = createAction(SEND_CHAT, ({
    sender,
    receiver,
    content
}) => ({
    sender,
    receiver,
    content
}));

const sendChatSaga = createRequestSaga(SEND_CHAT, chatAPI.send);
export function* sendSaga() {
    yield takeLatest(SEND_CHAT, sendChatSaga);
}

const initialState = {
    sender: '',
    receiver: '',
    content: '',
    chat: null,
    chatError: null
};

const send = handleActions(
    {
        [INITIALIZE]: state => initialState,
        [CHANGE_FIELD]: (state, { payload: { key, value }}) => ({
            ...state,
            [key]: value,
        }),
        [SEND_CHAT]: state => ({
            ...state,
            chat: null,
            chatError: null,
        }),
        [SEND_CHAT_SUCCESS]: (state, { payload: chat }) => ({
            ...state,
            chat,
        }),
        [SEND_CHAT_FAILURE]: (state, { payload: chatError }) => ({
            ...state,
            chatError,
        }),
    },
    initialState,
);

export default send;