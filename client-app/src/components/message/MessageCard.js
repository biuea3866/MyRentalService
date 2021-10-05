import React from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const Card = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    width: 100%;
    height: 80px;
    &:hover {
        background-color: ${palette.gray[2]}
    }
    padding-top: 1rem;
    padding-left: 4rem;
    cursor: pointer;
`;

const Nickname = styled.div`
    width: 100%;
    height: 40px;
`;

const MessageCard = ({ 
    item, 
    i, 
    onClick,
}) => {
    const { nickname } = useSelector(({ user }) => ({ nickname: user.user.nickname }));
    
    return(
        <Card onClick={ onClick }>
            <Nickname>
                { item.receiver === nickname ? item.sender : item.receiver } 님과의 채팅 
            </Nickname>
        </Card>
    );
};

export default MessageCard;