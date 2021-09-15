import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const ButtonBlock = styled.div`
    width: 60px;
    float: left;
`;

const Button = styled.button`
    width: 60px;
    height: 40px;
    border-radius: 4px;
    background-color: ${ palette.blue[1] };
    color: #ffffff;
    outline: none;
    border: none;
    &: hover {
        width: 60px;
        height: 40px;
        border-radius: 4px;
        background-color: ${ palette.blue[2] };
        color: #ffffff;
        outline: none;
        border: none;
    }
`;

const WriteButton = () => {
    return (
        <ButtonBlock>
            <Button>
                댓글 달기
            </Button>
        </ButtonBlock>
    );
};

export default WriteButton;