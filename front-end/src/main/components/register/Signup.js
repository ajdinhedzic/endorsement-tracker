import React from 'react';
import SignUpContainer from "./SignUpContainer";

class SignUp extends React.Component {
    render() {
        return (
            <div>
                <SignUpContainer>
                    <div className="card-text">How does the login process work?</div>
                    <div className="card-text">Simple! When you want to login we'll send a code to your email address.
                        You then verify your identity by entering the code you received. This way there are no passwords
                        to
                        remember.
                    </div>
                    <br/>
                    <form onSubmit={}>
                        <div className="form-group">
                            <input className="form-control email" type="text" placeholder="Email Address"
                                   value={}
                                   onChange={}/>
                            <small id="emailHelp" className="form-text text-muted">Nobody likes spam. We don't sell your
                                data.
                            </small>
                        </div>
                        <button className="btn btn-primary w-100"
                                onClick={}
                                type="submit">Create Account
                        </button>
                    </form>
                </SignUpContainer>
            </div>
        )
    }
}

export default SignUp;