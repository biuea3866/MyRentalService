import React from 'react';
import styled from 'styled-components';
import RoomTemplate from './RoomTemplate';
import SendForm from './SendForm';

const Wrap = styled.div`
    padding-top: 150px;
    width: 70%;
    height: 600px;
    overflow-x: hidden;
    overflow-y: auto;
`;

const RoomContainer = () => {
    return(
        <Wrap>
            <RoomTemplate />
            <SendForm />
        </Wrap>
    );
};

export default RoomContainer;