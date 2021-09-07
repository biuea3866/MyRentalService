import React from 'react';
import styled, { css } from 'styled-components';
import palette from '../../lib/styles/palettes';

const StyledButton = styled.button`
    width: 100%;
    height: 50px;
    border-radius: 4px;
    border: none;
    margin: 8px 0;
    outline: none;
    padding: 8px;
    box-sizing: border-box;
    transition: .3s;
    background-color: ${palette.blue[1]};
    color: #ffffff;
    font-size: 20px;
    &:hover {
        width: 100%;
        height: 50px;
        border-radius: 4px;
        border: none;
        margin: 8px 0;
        outline: none;
        padding: 8px;
        box-sizing: border-box;
        transition: .3s;
        background-color: ${palette.blue[2]};
        color: #ffffff;
        font-size: 20px;
    }
    ${
        props =>
        props.red &&
        css`
            background: ${ palette.red[0] };
            &: hover {
                background: ${ palette.red[1] };
            }
        `  
    }
`;

const FullButton = props => <StyledButton { ...props } />

export default FullButton;