import React from 'react';

class Navbar extends React.Component {
    render() {
        return (
            <nav className="navbar navbar-expand-lg navbar-light bg-light">
                <a className="navbar-brand" href="/">Endorsement Tracker</a>
                <button className="navbar-toggler" type="button" data-toggle="collapse"
                        data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                        aria-expanded="false" aria-label="Toggle navigation">
                    <span className="navbar-toggler-icon"/>
                </button>
                <div className="collapse navbar-collapse" id="navbarNavAltMarkup">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item active">
                            <a className="nav-item nav-link active" href="/">Home <span
                                className="sr-only">(current)</span></a>
                        </li>
                        <li> <a className="nav-item nav-link active" href="/">Why</a></li>
                        <li> <a className="nav-item nav-link active" href="/">About</a></li>
                    </ul>
                    <ul className="nav navbar-nav navbar-right">
                        Login
                    </ul>
                </div>
            </nav>
        )
    }
}

export default Navbar;