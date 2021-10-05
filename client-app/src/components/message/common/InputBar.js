import React from 'react';
import styled from 'styled-components';
import Input from '../../common/Input';

const InputBarArea = styled.div`
    float: left;
    width: inherit;
`;

const InputBar = ({ placeholder, onChange, name }) => {
    return(
        <InputBarArea>
            <Input name={ name }
                   type="text"
                   placeholder={ placeholder }
                   onChange={ onChange }
            />
        </InputBarArea>
    );
};

export default InputBar;