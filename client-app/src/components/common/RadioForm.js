import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const Radio = styled.div`
    display: flex;
    align-items: center;
    overflow: hidden;
    width: 100%;
    height: 40px;
    label: hover {
        cursor: pointer;
    }
    input: checked + label {
        background-color: ${ palette.blue[2] };
        box-shadow: none;
        color: white;
    }
    label: first-of-type {
        border-radius: 4px 0 0 4px;
    }
    label: last-of-type {
        border-radius: 0 4px 4px 0;
    }
`;

const RadioForm = ({ children }) => {
    return <Radio>{ children }</Radio>
};

export default RadioForm;