import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const StyledInput = styled.input`
    width: 100%;
    height: 40px;
    border: 2px solid #aaa;
    border-radius: 4px;
    margin: 8px 0;
    outline: none;
    padding: 8px;
    box-sizing: border-box;
    transition: .3s;
    &:focus {
        border-color: ${ palette.blue[2] };
        box-shadow: 0 0 8px 0 ${ palette.blue[2] };
    }
`;

const Input = props => <StyledInput { ...props } />

export default Input;