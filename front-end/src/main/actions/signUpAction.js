import {SIGNUP_FAILURE, SIGNUP_LOADING, SIGNUP_SUCCESS} from "../consts/actionsTypes";
import * as api from '../api/api'

export function signUpAction(email, password) {
    return (dispatch) => {
        dispatch({type: SIGNUP_LOADING});
        return api.post("/api/signup", {email, password})
            .then(() => dispatch({type: SIGNUP_SUCCESS}))
            .catch(() => dispatch({type: SIGNUP_FAILURE}))
    }
}