import { combineReducers } from "redux";
import { all } from 'redux-saga/effects';
import auth, { authSaga } from './auth';
import user, { userSaga } from "./user";
import write, { writeSaga } from "./write";
import post, { postSaga } from "./post";
import postList, { postListSaga } from "./postList";
import writeComment, { writeCommentSaga } from "./writeComment";
import send, { sendSaga } from './send';
import messageList, { messageListSaga } from "./messageList";
import loading from './loading';
import rental, { rentalSaga } from "./rental";

const rootReducer = combineReducers(
    {
        loading,
        auth,
        user,
        write,
        post,
        postList,
        writeComment,
        send,
        messageList,
        rental,
    },
);

export function* rootSaga() {
    yield all([
        authSaga(), 
        userSaga(), 
        writeSaga(), 
        postSaga(), 
        postListSaga(),
        writeCommentSaga(),
        sendSaga(),
        messageListSaga(),
        rentalSaga(),
    ]);
}

export default rootReducer;