import React from 'react';
import PropTypes from 'prop-types';

class SignUp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {email: '', password: ''}
    }

    setEmail = event => this.setState({email: event.target.value});
    setPassword = event => this.setState({password: event.target.value});
    submitForm = () => this.props.formSubmit(this.state.email, this.state.password);

    render() {
        return (
            <div>
                <div className="card-text">Fix this text</div>
                <br/>
                <form onSubmit={this.submitForm}>
                    <div className="form-group">
                        <input className="form-control email" type="text" placeholder="Email Address"
                               value={this.state.email}
                               onChange={this.setEmail}/>
                    </div>
                    <div className="form-group">
                        <input className="form-control password" type="text" placeholder="Password"
                               value={this.state.password}
                               onChange={this.setPassword}/>
                        <small id="emailHelp" className="form-text text-muted">Nobody likes spam. We don't sell your
                            data.
                        </small>
                    </div>
                    <button className="btn btn-primary w-100"
                            onClick={this.submitForm}
                            type="submit">Create Account
                    </button>
                </form>
            </div>
        )
    }
}

SignUp.propTypes = {
    formSubmit: PropTypes.func
};

export default SignUp;