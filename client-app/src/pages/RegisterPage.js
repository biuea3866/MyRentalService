import React from 'react';
import AuthTemplate from '../components/auth/AuthTemplate';
import RegisterForm from '../components/auth/RegisterForm';
import HeaderTemplate from '../components/common/HeaderTemplate';

const RegisterPage = () => {
    return (
        <>
            <HeaderTemplate />
            <AuthTemplate>
                <RegisterForm />
            </AuthTemplate>
        </>
    );
};

export default RegisterPage;