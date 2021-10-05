import React, { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { 
    changeField,
    initializeForm, 
    register,
    checkEmail,
    checkNickname, 
} from '../../modules/auth';
import AuthForm from './AuthForm';
import { withRouter } from 'react-router-dom';

const RegisterForm = ({ history }) => {
    const [error, setError] = useState('');
    const dispatch = useDispatch();
    const { 
        form,
        checkedEmail,
        checkedNickname,
    } = useSelector(({ auth }) => ({
        form: auth.register,
        auth: auth.auth,
        authError: auth.authError,
        checkedEmail: auth.checkedEmail,
        checkedNickname: auth.checkedNickname,
    })); 

    const onChange = e => {
        const { value, name } = e.target;

        dispatch(changeField({
            form: 'register',
            key: name,
            value
        }));
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

        if([
            email, 
            password,
            passwordConfirm,
            nickname,
            phoneNumber,
        ].includes('')) {
            // setError
            setError('입력하지 않은 사항이 있습니다.');

            return;
        }

        if(password !== passwordConfirm) {
            // setError
            setError('비밀번호가 일치하지 않습니다.');

            return;
        }

        if(checkedEmail && checkedNickname) {
            dispatch(register({
                email,
                password,
                nickname,
                phoneNumber,
            }));
            
            dispatch(initializeForm('register'));

            history.push('/');
        }
    };

    useEffect(() => {
        dispatch(initializeForm('register'));
    }, [dispatch]);

    useEffect(() => {
        const { email } = form;

        dispatch(checkEmail(email));

        if(!checkedEmail) {
            setError('이메일 중복!');
        }
    }, [dispatch, checkedEmail, form]);


    useEffect(() => {
        const { nickname } = form;

        dispatch(checkNickname(nickname));

        if(!checkedNickname) {
            setError('닉네임 중복!');
        }
    }, [dispatch, checkedNickname, form]);

    return (
        <AuthForm type='register'
                  form={ form }
                  onChange={ onChange }
                  onSubmit={ onSubmit }
                  error={ error }
        />
    );
};

export default withRouter(RegisterForm);