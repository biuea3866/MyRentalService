import React from 'react';
import styled from 'styled-components';
import WriteBar from './WriteBar';
import WriteButton from './WriteButton';

const WriteContainerBlock = styled.div`
    width: 100%;
    display: flex;
    align-items: center;
    margin-bottom: 1.5rem;
`;

const WriteContainer = () => {
    return (
        <WriteContainerBlock>
            <WriteBar />
            <WriteButton />
        </WriteContainerBlock>
    );
};

export default WriteContainer;