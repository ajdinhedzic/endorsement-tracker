import React from "react";

import {configure, shallow} from "enzyme";
import Adapter from 'enzyme-adapter-react-16';
import {Link} from "react-router-dom";
import {SIGNUP} from "../../main/consts/routes";
import MainPage from "../../main/components/MainPage";

describe('Main PAge', function () {
    beforeEach(() => {
        configure({adapter: new Adapter()});
    });

    it('renders a submit button', () => {
        const component = shallow(<MainPage/>);
        expect(component.find('.btn').length).toBe(1);
    });

    it('renders a link to route to the sign up page', function () {
        const component = shallow(<MainPage/>);
        expect(component.find(Link).length).toBe(1);
        expect(component.find(Link).props().to).toBe(SIGNUP);
    });
});