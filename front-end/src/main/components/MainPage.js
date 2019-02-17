import React from 'react';
import {Link} from "react-router-dom";
import {SIGNUP} from "../consts/routes";

class MainPage extends React.Component {
    render() {
        return (
            <div>
                <Link to={SIGNUP}>
                    <button className='btn btn-success'>Sign up</button>
                </Link>
            </div>
        )
    }
}

export default MainPage;