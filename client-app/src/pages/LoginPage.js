import React from 'react';
import AuthTemplate from '../components/auth/AuthTemplate';
import LoginForm from '../components/auth/LoginForm';
import HeaderTemplate from '../components/common/HeaderTemplate';

const LoginPage = () => {
    return(
        <>
            <HeaderTemplate />
            <AuthTemplate>
                <LoginForm />
            </AuthTemplate>
        </>
    );
};

export default LoginPage;