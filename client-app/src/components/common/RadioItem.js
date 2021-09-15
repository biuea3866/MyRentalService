import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const RadioItems = styled.input`
    position: absolute !important;
    clip: rect(0, 0, 0, 0);
    height: 1px;
    width: 1px;
    border: 0;
    overflow: hidden;
    checked {
        background-color: white;
        box-shadow: none;
    }
`;

const RadioLabel = styled.label`
    background-color: white;
    color: ${ palette.blue[2] };
    font-size: 14px;
    font-weight: bold;
    text-align: center;
    padding: 8px 16px;
    border: 1px solid ${ palette.blue[2] };
    transition: all 0.1s ease-in-out;
`;

const RadioItem = ({ id, name, value, onChange }) => {
    return(
        <>
            <RadioItems type="radio"
                        id={ id }
                        name={ name }
                        onChange={ onChange }
                        value={ value }
            />
            <RadioLabel for={ id }
                        onChange={ onChange }
                        value={ value }
            >
                { value }
            </RadioLabel>
        </>
    );
};

export default RadioItem;