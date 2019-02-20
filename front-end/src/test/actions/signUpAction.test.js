import React from 'react';
import {signUpAction} from "../../main/actions/signUpAction";
import configureStore from 'redux-mock-store';
import thunk from "redux-thunk";
import {SIGNUP_FAILURE, SIGNUP_LOADING, SIGNUP_SUCCESS} from "../../main/consts/actionsTypes";
import * as api from '../../main/api/api'

describe('Sign Up Actions', function () {
    let store;

    beforeEach(() => {
        let mockStore = configureStore([thunk]);
        store = mockStore({});
        api.post = jest.fn();
        api.post.mockReturnValue(Promise.resolve({data: ''}));
    });

    it('dispatches sign up loading', function () {
        return store.dispatch(signUpAction('any', 'any'))
            .then(() =>
                expect(store.getActions()).toContainEqual({type: SIGNUP_LOADING})
            )
    });

    it('makes a POST call to /signup', function () {
        return store.dispatch(signUpAction('test@test.com', 'pass'))
            .then(() =>
                expect(api.post).toHaveBeenCalledWith('/api/signup', {email: 'test@test.com', password: 'pass'})
            )
    });

    it('dispatches singup success on success', function () {
        api.post.mockReturnValue(Promise.resolve());
        return store.dispatch(signUpAction('', ''))
            .then(() =>
                expect(store.getActions()).toContainEqual({type: SIGNUP_SUCCESS}))
    });

    it('dispatches singup failure on failure', function () {
        api.post.mockReturnValue(Promise.reject());
        return store.dispatch(signUpAction('', ''))
            .then(() =>
                expect(store.getActions()).toContainEqual({type: SIGNUP_FAILURE}))
    });
});