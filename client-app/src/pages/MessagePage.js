import React from 'react';
import HeaderTemplate from '../components/common/HeaderTemplate';
import MessageListContainer from '../components/message/MessageListContainer';
import RoomContainer from '../components/message/RoomContainer';

const MessagePage = () => {
    return(
        <>
            <HeaderTemplate />
            <MessageListContainer />
            <RoomContainer />
        </>
    );
};

export default MessagePage;