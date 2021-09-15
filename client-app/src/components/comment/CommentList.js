import React from 'react';
import styled from 'styled-components';
import CommentItem from './CommentItem';

const ListBlock = styled.div`
    width: 100%;
    display: flex;
    flex-direction: column;
    float: left;
`;

const CommentList = () => {
    const dummyData = [
        {"commentId": "1", "comment": "test-01", "createdAt": "2000-01-01", "writer": "test-01"},
        {"commentId": "2", "comment": "test-02", "createdAt": "2000-01-01", "writer": "test-01"},
        {"commentId": "3", "comment": "test-03", "createdAt": "2000-01-01", "writer": "test-01"},
        {"commentId": "4", "comment": "test-04", "createdAt": "2000-01-01", "writer": "test-01"},
        {"commentId": "5", "comment": "test-05", "createdAt": "2000-01-01", "writer": "test-01"},
        {"commentId": "6", "comment": "test-06", "createdAt": "2000-01-01", "writer": "test-01"},
        {"commentId": "7", "comment": "test-07", "createdAt": "2000-01-01", "writer": "test-01"},
        {"commentId": "8", "comment": "test-08", "createdAt": "2000-01-01", "writer": "test-01"},
    ];
    
    return(
        <ListBlock>
            { 
                dummyData.map((item, i) => {
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