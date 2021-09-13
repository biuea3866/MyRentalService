import client from './client';

export const login = ({
    email,
    password
}) => client.post('/auth-service/login', {
    email,
    password
});

export const register = ({
    email,
    password,
    nickname,
    phoneNumber
}) => client.post('/auth-service/register', {
    email,
    password,
    nickname,
    phoneNumber
});

export const getUser = userId => client.get(`/auth-service/${userId}/info`, {
    headers: {
        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('token'))   
    }
});

export const check = userId => client.get(`/auth-service/${userId}/check`, {
    headers: {
        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('token'))       
    }
});

export const checkNickname = nickname => client.get(`/auth-service/check/nickname/${nickname}`);


export const checkEmail = email => client.get(`/auth-service/check/email/${email}`);