import client from './client';
import qs from 'qs';

export const send = ({
    sender,
    receiver,
    content
}) => client.post("/message-service/send", {
    sender,
    receiver,
    content
});

export const getUserList = sender => client.get(`/message-service/user-list/${sender}`);

export const getMessageList = ({
    sender,
    receiver
}) => client.get(`/message-service/message-list?${qs.stringify({ sender, receiver })}`);