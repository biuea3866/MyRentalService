import { combineReducers } from "redux";
import { all } from 'redux-saga/effects';
import auth, { authSaga } from './auth';
import user, { userSaga } from "./user";
import write, { writeSaga } from "./write";
import post, { postSaga } from "./post";
import postList, { postListSaga } from "./postList";
import loading from './loading';

const rootReducer = combineReducers(
    {
        loading,
        auth,
        user,
        write,
        post,
        postList,
    },
);

export function* rootSaga() {
    yield all([authSaga(), userSaga(), writeSaga(), postSaga(), postListSaga()]);
}

export default rootReducer;