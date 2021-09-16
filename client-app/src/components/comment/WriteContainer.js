import React from 'react';
import { useDispatch } from 'react-redux';
import styled from 'styled-components';
import { changeField } from '../../modules/writeComment';
import WriteBar from './WriteBar';
import WriteButton from './WriteButton';

const WriteContainerBlock = styled.div`
    width: 100%;
    display: flex;
    align-items: center;
    margin-bottom: 1.5rem;
`;

const WriteContainer = () => {
    const dispatch = useDispatch();
    const onChange = e => {
        e.preventDefault();

        const { name, value } = e.target;

        dispatch(changeField({
            key: name,
            value
        }));
    };

    return (
        <WriteContainerBlock>
            <WriteBar onChange={ onChange }/>
            <WriteButton />
        </WriteContainerBlock>
    );
};

export default WriteContainer;