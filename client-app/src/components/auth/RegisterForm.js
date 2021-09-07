import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { 
    changeField,
    initializeForm, 
    register 
} from '../../modules/auth';
import AuthForm from './AuthForm';
import { withRouter } from 'react-router-dom';

const RegisterForm = ({ history }) => {
    const dispatch = useDispatch();
    const { 
        form, 
        auth, 
        authError,
    } = useSelector(({ 
        auth,
    }) => ({
        form: auth.register,
        auth: auth.auth,
        authError: auth.authError,
    })); 

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
            // setError
            
            return;
        }

        if([
            email, 
            password,
            passwordConfirm,
            nickname,
            phoneNumber,
        ].includes('')) {
            // setError
            return;
        }

        dispatch(register({
            email,
            password,
            nickname,
            phoneNumber,
        }));

        history.push('/');
    };

    useEffect(() => {
        dispatch(initializeForm('register'));
    }, [dispatch]);

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