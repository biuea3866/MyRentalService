import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import palette from '../../lib/styles/palettes';
import FullButton from '../common/FullButton';
import BorderButton from '../common/BorderButton';
import Input from '../common/Input'

const AuthFormBlock = styled.div`
    h3 {
        margin: 0;
        color: ${ palette.gray[8] };
        margin-bottom: 1rem;
    }
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;

const Header = styled.div`
    margin-top: 20px;
    margin-bottom: 30px;
    width: 60%;
    font-size: 25px;
    padding-left: 10%;
    color: ${palette.blue[2]};
    font-weight: bold;
`;

const ErrorMessage = styled.div`
    color: red;
    text-align: center;
    font-size: 14px;
    margin-top: 1rem;
`;

const textMap = {
    login: 'login',
    register: 'register'
};

const AuthForm = ({ 
    type, 
    form, 
    onChange, 
    onSubmit, 
    error 
}) => {
    const text = textMap[type];

    return(
        <AuthFormBlock>
            { text === 'login' ? (
                <>
                    <Header>
                        물건 대여 플랫폼에 <br />
                        오신 것을 환영합니다!
                    </Header>
                    <form onSubmit={ onSubmit }>
                        <Input autoComplete="email"
                               name="email"
                               placeholder="E-mail"
                               onChange={ onChange }
                               value={ form.email }
                        />
                        <Input autoComplete="new-password"
                               name="password"
                               placeholder="Password"
                               type="password"
                               onChange={ onChange }
                               value={ form.password }
                        />
                        { error && <ErrorMessage>{ error }</ErrorMessage>}
                        <FullButton>
                            로그인        
                        </FullButton>
                        <BorderButton>
                            <Link to="/auth/register">
                                회원가입
                            </Link>
                        </BorderButton>
                    </form>
                </>
            ) : (
                <>
                    <Header>
                        물건 대여 플랫폼에 <br />
                        처음 오셨군요!
                    </Header>
                    <form onSubmit={ onSubmit }>
                        <Input autoComplete="email"
                               name="email"
                               placeholder="이메일을 입력해주세요"
                               onChange={ onChange }
                               value={ form.email }
                        />
                        <Input autoComplete="new-password"
                               name="password"
                               placeholder="비밀번호를 입력해주세요"
                               type="password"
                               onChange={ onChange }
                               value={ form.password }
                        />
                        <Input autoComplete="new-password"
                               name="passwordConfirm"
                               placeholder="비밀번호를 재입력해주세요"
                               type="password"
                               onChange={ onChange }
                               value={ form.passwordConfirm }
                        />
                        <Input autoComplete="nickname"
                               name="nickname"
                               placeholder="닉네임을 입력해주세요"
                               onChange={ onChange }
                               value={ form.nickname }
                        />
                        <Input autoComplete="phoneNumber"
                               name="phoneNumber"
                               placeholder="'-'를 제외하고 핸드폰 번호를 입력해주세요"
                               onChange={ onChange }
                               value={ form.phoneNumber }
                        />
                        { error && <ErrorMessage>{ error }</ErrorMessage>}
                        <FullButton>
                            가입하기
                        </FullButton>
                    </form>
                </>
            )}
        </AuthFormBlock>
    );
};

export default AuthForm;