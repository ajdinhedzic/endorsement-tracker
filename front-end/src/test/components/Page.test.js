import React from "react";
import Page from "../../main/components/Page";
import {configure, shallow} from "enzyme";
import Adapter from 'enzyme-adapter-react-16';
import {BrowserRouter, Link, Route, Router} from "react-router-dom";
import {DASHBOARD, MAINPAGE, SIGNUP} from "../../main/consts/routes";
import SignUpContainer from "../../main/components/register/SignUpContainer";
import MainPage from "../../main/components/MainPage";
import DashboardContainer from "../../main/components/dashboard/DashboardContainer";

describe('PageContent Content', function () {

    beforeEach(() => {
        configure({ adapter: new Adapter() });
    });

    it('renders a container for Bootstrap', () => {
        const component = shallow(<Page/>);
        expect(component.find('.container').length).toBe(1);
    });

    describe('Routing', function () {
        it('renders the main page on /', function () {
            const component = shallow(<Page/>);
            expect(component.find(Route).at(0).props().path).toBe(MAINPAGE);
            expect(component.find(Route).at(0).props().component).toBe(MainPage);
        });

        it('has a Route with link to signup', function () {
            const component = shallow(<Page/>);
            expect(component.find(Route).at(1).props().path).toBe(SIGNUP);
            expect(component.find(Route).at(1).props().component).toBe(SignUpContainer);
        });

        it('has a route to dashboard', function () {
            const component = shallow(<Page/>);
            expect(component.find(Route).at(2).props().path).toBe(DASHBOARD);
            expect(component.find(Route).at(2).props().component).toBe(DashboardContainer);
        });
    });
});