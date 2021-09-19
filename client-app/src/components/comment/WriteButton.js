import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';
import { write, changeField } from '../../modules/writeComment';

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
    const dispatch = useDispatch();
    const { 
        comment,
        postId,
        writer,
    } = useSelector(({ 
        writeComment,
        post,
        user,
    }) => ({ 
        comment: writeComment.comment,
        postId: post.post.id,
        writer: user.user.nickname,
    }));

    useEffect(() => {
        dispatch(changeField({
            key: 'postId',
            value: postId
        }));
    }, [dispatch, postId]);

    useEffect(() => {
        dispatch(changeField({
            key: 'writer',
            value: writer
        }));
    }, [dispatch, writer]);

    const onSubmit = e => {
        e.preventDefault();
        
        dispatch(write({ postId, comment, writer }));
    };

    return (
        <ButtonBlock>
            <Button onClick={ onSubmit }>
                댓글 달기
            </Button>
        </ButtonBlock>
    );
};

export default WriteButton;