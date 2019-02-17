import React from "react";
import SiteContainer from "../../main/components/SiteContainer";
import Page from "../../main/components/Page";
import Navbar from "../../main/components/global/Navbar";
import {configure, shallow} from "enzyme";
import Adapter from 'enzyme-adapter-react-16';

describe('SiteContainer Container', function () {
    beforeEach(() => {
        configure({ adapter: new Adapter() });
    });

    it('renders a navbar', () => {
        const component = shallow(<SiteContainer/>);
        expect(component.find(Navbar).length).toBe(1);
    });

    it('renders a page', () => {
        const component = shallow(<SiteContainer/>);
        expect(component.find(Page).length).toBe(1);
    });
});