import React from 'react';
import {configure, shallow} from "enzyme";
import SignUp from "../../../main/components/register/Signup";
import Adapter from "enzyme-adapter-react-16/build";

describe('Sign Up', function () {
    beforeEach(() => {
        configure({adapter: new Adapter()});
    });

    it('renders a form', function () {
        const component = shallow(<SignUp/>);
        expect(component.find('form').length).toBe(1)
    });

    it('adds email to state', function () {
        const component = shallow(<SignUp/>);
        component.find('.email').simulate('change', {target: {id: 'email', value: 'hello@hello.com'}});
        expect(component.state().email).toBe('hello@hello.com');
    });

    it('should adds password to state', function () {
        const component = shallow(<SignUp/>);
        component.find('.password').simulate('change', {target: {id: 'password', value: 'abc'}});
        expect(component.state().password).toBe('abc');
    });

    it('calls form submit with username and password on submit button click', function () {
        const mockFormSubmit = jest.fn();
        const component = shallow(<SignUp formSubmit={mockFormSubmit}/>);
        component.setState({email: 'hello@test.com', password: 'a password'});
        component.find('.btn').simulate('click');
        expect(mockFormSubmit).toHaveBeenCalledWith('hello@test.com', 'a password');
    });
});