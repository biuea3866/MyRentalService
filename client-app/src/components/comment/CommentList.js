import React, { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import styled from 'styled-components';
import CommentItem from './CommentItem';

const ListBlock = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    float: left;
`;

const CommentList = ({ comments }) => {
    const dispatch = useDispatch();
    const { success } = useSelector(({ writeComment }) => ({ success: writeComment.success }));
    
    useEffect(() => {
        if(success) {
            window.location.reload();
        }
    }, [dispatch, success]);

    return(
        <ListBlock>
            { 
                comments !== null &&
                comments.map((item, i) => {
                    return (
                        <CommentItem
                            item={ item }
                            i={ i }
                        />   
                    )     
                })
            }
        </ListBlock>
    );
};

export default CommentList;