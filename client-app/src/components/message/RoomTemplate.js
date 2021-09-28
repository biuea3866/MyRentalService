import React from 'react';
import styled from 'styled-components';
import ChatCard from './ChatCard';

const Wrap = styled.div`
    width: 100%;
    height: 560px;
`;

const RoomTemplate = () => {
    return(
        <Wrap>
            <ChatCard />
        </Wrap>
    );
};

export default RoomTemplate;