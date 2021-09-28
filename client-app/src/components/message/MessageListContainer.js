import React from 'react';
import styled from 'styled-components';
import SearchBar from './common/InputBar';
import SearchButton from './common/InpuButton';
import MessageCard from './MessageCard';

const Wrap = styled.div`
    float: left;
    padding-top: 150px;
    width: 30%;
    height: 600px;
    overflow-x: hidden;
    overflow-y: auto;
`;

const Header  = styled.div`
    width: 80%;
    display: flex;
    align-items: center;
    justify-content: center;
    padding-left: 1.5rem;
`;

const ListBox = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    flex-direction: column;
    padding-top: 20px;
    width: 100%;
`;

const MessageListContainer = () => {
    const dummyData = [
        { "nickname": "test-01", "createdAt": "2020-01-01", "content": "test-01" },
        { "nickname": "test-02", "createdAt": "2020-01-01", "content": "test-02" },
        { "nickname": "test-03", "createdAt": "2020-01-01", "content": "test-03" },
        { "nickname": "test-04", "createdAt": "2020-01-01", "content": "test-04" },
        { "nickname": "test-05", "createdAt": "2020-01-01", "content": "test-05" },
        { "nickname": "test-06", "createdAt": "2020-01-01", "content": "test-06" },
        { "nickname": "test-07", "createdAt": "2020-01-01", "content": "test-07" },
        { "nickname": "test-08", "createdAt": "2020-01-01", "content": "test-08" },
    ];

    return(
        <Wrap>
            <Header>
                <SearchBar placeholder="닉네임을 입력해주세요" />
                <SearchButton />            
            </Header>
            <ListBox>
                {
                    dummyData.map((item, i) => {
                        return <MessageCard item={ item } 
                                            i={ i }
                               />
                    })
                }
            </ListBox>
        </Wrap>
    );
};

export default MessageListContainer;