import {SIGNUP_FAILURE, SIGNUP_LOADING, SIGNUP_SUCCESS} from "../consts/actionsTypes";

const initialState = {
    signUpSuccess: false,
    isLoading: false,
    hasError: false
};

const loginReducer = (state = initialState, action) => {
    switch (action.type) {
        case SIGNUP_LOADING:
            return Object.assign({}, state, {isLoading: true});
        case SIGNUP_SUCCESS:
            return Object.assign({}, state, {signUpSuccess: true, isLoading: false});
        case SIGNUP_FAILURE:
            return Object.assign({}, state, {hasError: true, isLoading: false});
        default:
            return state
    }
};

export default loginReducer;