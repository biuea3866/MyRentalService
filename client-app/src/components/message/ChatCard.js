import React from 'react';
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

const ChatCard = () => {
    return(
        <>
            <Card>
                <ReceiverCard>
                    asdsadsadsa
                </ReceiverCard>
                <ReceiverDate>
                    2020-01-01
                </ReceiverDate>
            </Card>
            <Card>
                <SenderCard>
                    asdsadsad
                </SenderCard>
                <SenderDate>
                    2020-01-02
                </SenderDate>
            </Card>
        </>
    );
};

export default ChatCard;