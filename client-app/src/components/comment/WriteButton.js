import React from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';
import writeComment from '../../modules/writeComment';

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
        writer,
        postId,
    } = useSelector(({ 
        writeComment,
        user,
        post,
    }) => ({ 
        comment: writeComment.comment,
        writer: user.nickname,
        postId: post.postId
    }));

    const onSubmit = e => {
        e.preventDefault();

        dispatch(writeComment({ postId, comment, writer }));
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