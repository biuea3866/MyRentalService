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

export const getUser = ({ userId }) => client.get('/auth-service/:userId/getUser', { userId });

export const check = ({ user }) => client.post('/auth-service/check', { user });