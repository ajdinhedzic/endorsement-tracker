import React from 'react';
import {SignUpContainer} from "../../../main/components/register/SignUpContainer";
import {configure, shallow} from "enzyme";
import SignUp from "../../../main/components/register/Signup";
import Adapter from "enzyme-adapter-react-16/build";

describe('Sign Up Container', function () {
    beforeEach(() => {
        configure({adapter: new Adapter()});
    });

    it('renders a sign up component', function () {
        const component = shallow(<SignUpContainer/>);
        expect(component.find(SignUp).length).toBe(1)
    });

    describe('signup actions', function () {
        const props = {
            email: 'test@test.com',
            password: 'admin'
        };

        it('should calls signUpSubmit', function () {
            const signUp = jest.fn();
            const component = shallow(<SignUpContainer {...props} signUp={signUp}/>);
            expect(component.find(SignUp).props().formSubmit).toBe(signUp);
        });
    });
});