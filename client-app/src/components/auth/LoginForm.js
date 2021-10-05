import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { withRouter } from 'react-router-dom';
import { 
    changeField,
    info,
    initializeForm,
    login,
} from '../../modules/auth';
import { check } from '../../modules/user';
import AuthForm from './AuthForm';

const LoginForm = ({ history }) => {
    const [error, setError] = useState(null);
    const dispatch = useDispatch();
    const { 
        form, 
        auth, 
        authError,
        user, 
        headers 
    } = useSelector(({ 
        auth, 
        user 
    }) => ({
        form: auth.login,
        auth: auth.auth,
        authError: auth.authError,
        headers: auth.headers,
        user: user.user
    })); 
    // For changing input, handler
    const onChange = e => {
        const { 
            value,
            name 
        } = e.target;

        dispatch(changeField({
            form: 'login',
            key: name,
            value
        }));
    };

    const onSubmit = e => {
        e.preventDefault();

        const {
            email,
            password
        } = form;

        dispatch(login({ 
            email, 
            password 
        }));
    };

    useEffect(() => {
        dispatch(initializeForm('login'));
    }, [dispatch]);

    useEffect(() => {
        if(authError) {
            setError('에러 발생!');
            
            return;
        }

        if(auth) {
            const { userId } = auth;

            dispatch(check(userId));
        }
    }, [dispatch, auth, authError, history]);

    useEffect(() => {
        if(user) {
            try {
                setError(null);

                localStorage.setItem('user', JSON.stringify(user));

                dispatch(initializeForm('auth'));
                
                dispatch(initializeForm('headers'));
                
                history.push('/');
            } catch(e) {
                console.log('localStorage is not working');
            }
        }
    }, [dispatch, user, history]);

    useEffect(() => {
        if(headers) {
            const { 
                userid, 
                token 
            } = headers;

            localStorage.setItem('token', JSON.stringify(token));
            
            dispatch(info(userid));
        }
    }, [dispatch, headers]);

    return (
        <AuthForm type='login'
                  form={ form }
                  onChange={ onChange }
                  onSubmit={ onSubmit }
                  error={ error }
        />
    );
};

export default withRouter(LoginForm);