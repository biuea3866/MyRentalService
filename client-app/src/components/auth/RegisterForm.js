import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { changeField  } from '../../modules/auth';
import AuthForm from './AuthForm';
import { withRouter } from 'react-router-dom';

const RegisterForm = ({ history }) => {
    const dispatch = useDispatch();
    const { form } = useSelector(({ auth }) => ({
        form: auth.register,
    })); 
    // For changing input, handler
    const onChange = e => {
        const { value, name } = e.target;

        dispatch(
            changeField({
                form: 'register',
                key: name,
                value
            })
        );
    };

    // Handler that registers form
    const onSubmit = e => {
        e.preventDefault();

        const { 
            email, 
            password,
            passwordConfirm,
            nickname,
            phoneNumber, 
        } = form;

        if(password !== passwordConfirm) {
            return;
        }
    };

    return (
        <AuthForm
            type='register'
            form={ form }
            onChange={ onChange }
            onSubmit={ onSubmit }
        />
    );
};

export default withRouter(RegisterForm);