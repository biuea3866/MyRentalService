import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { withRouter } from 'react-router-dom';
import styled from 'styled-components';
import { readChatList } from '../../modules/messageList';
import { changeField } from '../../modules/send';
import RoomTemplate from './RoomTemplate';
import SendForm from './SendForm';

const Wrap = styled.div`
    padding-top: 150px;
    width: 69%;
    height: 600px;
    overflow-x: hidden;
    overflow-y: auto;
`;

const RoomContainer = ({ match }) => {
    const dispatch = useDispatch();
    const {
        sender,
        receiver,
        chatList,
    } = useSelector(({ 
        send,
        messageList,  
        user,
    }) => ({
        sender: user.user.nickname,
        receiver: send.receiver,
        chatList: messageList.chatList
    }));

    useEffect(() => {
        dispatch(readChatList({
            sender,
            receiver,
        }));
    }, [dispatch, sender, receiver]);

    useEffect(() => {
        dispatch(changeField({
            key: 'sender',
            value: sender
        }));
    }, [dispatch, sender]);

    return(
        <Wrap>
            <RoomTemplate chatList={ chatList }/>
            <SendForm />
        </Wrap>
    );
};

export default withRouter(RoomContainer);