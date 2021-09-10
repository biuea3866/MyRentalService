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

export const getUser = userId => client.get(`/auth-service/${userId}/getUser`, {
    headers: {
        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('token'))   
    }
});

export const check = userId => client.get(`/auth-service/${userId}/check`, {
    headers: {
        'Authorization': 'Bearer ' + JSON.parse(localStorage.getItem('token'))       
    }
});