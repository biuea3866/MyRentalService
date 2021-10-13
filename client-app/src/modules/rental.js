import { createAction, handleActions } from "redux-actions";
import createRequestSaga, { createRequestActionTypes } from "../lib/createRequestSaga";
import * as rentalAPI from '../lib/api/rental';
import * as postAPI from '../lib/api/posts';
import { takeLatest } from "@redux-saga/core/effects";

const INITIALIZE = 'rental/INITIALIZE';

const CHANGE_FIELD = 'rental/CHANGE_FIELD';

const [
    CREATE_RENTAL,
    CREATE_RENTAL_SUCCESS,
    CREATE_RENTAL_FAILURE,
] = createRequestActionTypes('rental/CREATE_RENTAL');

const [
    COMPLETE,
    COMPLETE_SUCCESS,
    COMPLETE_FAILURE
] = createRequestActionTypes('rental/COMPLETE');

const [
    REQUEST_RENTALS,
    REQUEST_RENTALS_SUCCESS,
    REQUEST_RENTALS_FAILURE,
] = createRequestActionTypes('rental/REQUEST_RENTALS');

export const createRental = createAction(CREATE_RENTAL, ({
    postId,
    owner,
    borrower,
    price,
    startDate,
    endDate
}) => ({
    postId,
    owner,
    borrower,
    price,
    startDate,
    endDate
}));

export const completeRental = createAction(COMPLETE, ({
    acceptance,
    rentalId
}) => ({
    acceptance,
    rentalId
}));

export const requestRentals = createAction(REQUEST_RENTALS, owner => owner);

export const initialize = createAction(INITIALIZE);

export const changeField = createAction(CHANGE_FIELD, ({
    key,
    value
}) => ({
    key,
    value
}));

const createRentalSaga = createRequestSaga(CREATE_RENTAL, postAPI.createRental);
const completeRentalSaga = createRequestSaga(COMPLETE, rentalAPI.completeRental);
const requestRentalsSaga = createRequestSaga(REQUEST_RENTALS, rentalAPI.requestRentals);

export function* rentalSaga() {
    yield takeLatest(CREATE_RENTAL, createRentalSaga);
    yield takeLatest(COMPLETE, completeRentalSaga);
    yield takeLatest(REQUEST_RENTALS, requestRentalsSaga);
};

const initialState = {
    acceptance: null,
    rentalId: null,
    owner: null,
    message: '',
    rental: null,
    rentals: null,
    rentalError: null,
};

const rental = handleActions(
    {
        [INITIALIZE]: state => initialState,
        [CHANGE_FIELD]: (state, { payload: { key, value }}) => ({
            ...state,
            [key]: value
        }),
        [CREATE_RENTAL_SUCCESS]: (state, { payload: message }) => ({
            ...state,
            message,
        }),
        [CREATE_RENTAL_FAILURE]: (state, { payload: rentalError }) => ({
            ...state,
            rentalError
        }),
        [COMPLETE_SUCCESS]: (state, { payload: rental }) => ({
            ...state,
            rental,
        }),
        [COMPLETE_FAILURE]: (state, { payload: rentalError }) => ({
            ...state,
            rentalError
        }),
        [REQUEST_RENTALS_SUCCESS]: (state, { payload: rentals }) => ({
            ...state,
            rentals
        }),
        [REQUEST_RENTALS_FAILURE]: (state, { payload: rentalError }) => ({
            ...state,
            rentalError
        }),
    },
    initialState,
);

export default rental;