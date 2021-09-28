import React from 'react';
import styled from 'styled-components';
import InputBar from './common/InputBar';
import InputButton from './common/InpuButton';

const SendContainer = styled.div`
    width: inherit;
    display: flex;
    align-items: center;
    justify-content: center;
    position: fixed;
    overflow-y: auto;
    overflow-x: hidden;
`;

const StyledInputBar = styled(InputBar)`
    width: 100%;
`;

const SendForm = () => {
    return (
        <SendContainer>
            <StyledInputBar placeholder="메시지를 입력해주세요" />
            <InputButton />            
        </SendContainer>
    );
};

export default SendForm;