import React from 'react';
import {BrowserRouter as Router, Route} from "react-router-dom";
import {MAINPAGE, SIGNUP} from "../consts/routes";
import SignUpContainer from "./register/SignUpContainer";
import MainPage from "./MainPage";

class Page extends React.Component {
    render() {
        return (
            <div className="container">
                <Router>
                    <div>
                        <Route exact path={MAINPAGE} component={MainPage}/>
                        <Route exact path={SIGNUP} component={SignUpContainer}/>
                    </div>
                </Router>
            </div>
        )
    }
}

export default Page;