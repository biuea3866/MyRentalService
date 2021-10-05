import React from 'react';
import { useSelector } from 'react-redux';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const Card = styled.div`
    width: 100%;
`;

const SenderDate = styled.span`
    float: left;
    width: 98.5%;
    text-align: right;
`;

const ReceiverDate = styled.span`
    float: left;
    width: 100%;
    padding-left: 15px;
`;

const SenderCard = styled.div`
    float: right;
    display: inline-block;
    padding: 20px;
    margin: 10px;
    background-color: ${palette.blue[2]};
    color: white;
    border-radius: 10px;
`;

const ReceiverCard = styled.div`
    float: left;
    display: inline-block;
    padding: 20px;
    margin: 10px;
    background-color: ${palette.gray[2]};
    border-radius: 10px;
`;

const ChatCard = ({ item }) => {
    const { nickname } = useSelector(({ user }) =>  ({ nickname: user.user.nickname }));
    
    return(
        <>
            {
                item.sender === nickname ?     
                <Card>
                    <SenderCard>
                        { item.content }
                    </SenderCard>
                    <SenderDate>
                        { item.createdAt }
                    </SenderDate>
                </Card> :
                <Card>
                    <ReceiverCard>
                        { item.content }
                    </ReceiverCard>
                    <ReceiverDate>
                        { item.createdAt }
                    </ReceiverDate>
                </Card>
            }
        </>
    );
};

export default ChatCard;