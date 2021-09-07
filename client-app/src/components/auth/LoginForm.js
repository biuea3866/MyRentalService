import React, { useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { changeField } from '../../modules/auth';
import AuthForm from './AuthForm';

const LoginForm = ({ history }) => {
    const [error, setError] = useState(null);
    const dispatch = useDispatch();
    const { form } = useSelector(({ auth }) => ({
        form: auth.login,
    })); 
    // For changing input, handler
    const onChange = e => {
        const { 
            value,
            name 
        } = e.target;

        dispatch(
            changeField({
                form: 'login',
                key: name,
                value
            })
        );
    };

    const onSubmit = e => {
        // Login Button EventListener
        
        e.preventDefault();
    };

    return (
        <AuthForm
            type='login'
            form={ form }
            onChange={ onChange }
            onSubmit={ onSubmit }
            error={ error }
        />
    );
};

export default withRouter(LoginForm);