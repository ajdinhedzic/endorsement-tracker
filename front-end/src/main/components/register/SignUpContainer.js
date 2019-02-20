import React from 'react'
import SignUp from "./Signup";
import {connect} from "react-redux";
import {signUpAction} from "../../actions/signUpAction";
import PropTypes from 'prop-types';
import {Redirect} from "react-router-dom";
import {DASHBOARD} from "../../consts/routes";

export class SignUpContainer extends React.Component {
    render() {
        return (
            <div className="container h-100">
                <div className="row h-100 justify-content-center align-items-center">
                    <div className="col-md-5">
                        <div className="card" style={{marginTop: '25%'}}>
                            <div className="card-body">
                                {this.props.hasError && <h1>Error</h1>}
                                <SignUp formSubmit={this.props.signUp} isLoading={this.props.isLoading}/>
                                {this.props.signUpSuccess && <Redirect to={{pathname: DASHBOARD}} />}
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = state => {
    return {
        signUpSuccess: state.login.signUpSuccess,
        hasError: state.login.hasError,
        isLoading: state.login.isLoading
    }
};

const mapDispatchToProps = dispatch => {
    return {
        signUp: (email, password) => dispatch(signUpAction(email, password))
    }
};

SignUpContainer.propTypes = {
    signUpSuccess: PropTypes.bool,
    hasError: PropTypes.bool,
    isLoading: PropTypes.bool,
    signUp: PropTypes.func
};

export default connect(mapStateToProps, mapDispatchToProps)(SignUpContainer)