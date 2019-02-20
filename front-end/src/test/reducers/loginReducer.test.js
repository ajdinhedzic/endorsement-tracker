import React from 'react'
import loginReducer from "../../main/reducers/loginReducer";
import {SIGNUP_FAILURE, SIGNUP_LOADING, SIGNUP_SUCCESS} from "../../main/consts/actionsTypes";

describe('Sign up reducer', function () {
    const initialState = {
        signUpSuccess: false,
        isLoading: false,
        hasError: false
    };

    it('returns the initial state', function () {
        expect(loginReducer(initialState,{type: 'any'})).toBe(initialState);
    });

    it('sets isLoading true', function () {
        const reducer = loginReducer(initialState, {type: SIGNUP_LOADING});
        expect(reducer.isLoading).toBeTruthy();
    });

    it('sets success on SINGUP_SUCCESS', function () {
        const reducer = loginReducer(initialState, {type: SIGNUP_SUCCESS});
        expect(reducer.signUpSuccess).toBeTruthy();
        expect(reducer.isLoading).toBeFalsy()
    });

    it('sets failure on SIGNUP_FAILURE', function () {
        const reducer = loginReducer(initialState, {type: SIGNUP_FAILURE});
        expect(reducer.hasError).toBeTruthy();
        expect(reducer.isLoading).toBeFalsy()
    });
});