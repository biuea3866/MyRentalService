import React from 'react';
import styled from 'styled-components';
import ChatCard from './ChatCard';

const Wrap = styled.div`
    width: 100%;
    height: 560px;
`;

const RoomTemplate = ({ chatList }) => {
    return(
        <Wrap>
            {
                chatList ?
                chatList.map((item, i) => {
                    return <ChatCard item={ item }/>
                }) :
                <></>
            }
        </Wrap>
    );
};

export default RoomTemplate;