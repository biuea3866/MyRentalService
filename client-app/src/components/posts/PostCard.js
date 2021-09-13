import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const PostCardBlock = styled.div`
    display: flex;
    flex-direction: column;
    background-color: white;
    width: 400px;
    height: 300px;
    margin: 20px;
    box-shadow: 0px 0px 2px 1px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
`;

const CardTitle = styled.div`
    float: left;
    width: 400px;
    height: 50px;
    text-align: center;
`;

const CardImage = styled.img`
    float: left;
    width: 400px;
    height: 200px;
`;

const CardNickname = styled.div`
    float: left;
    width: 400px;
    text-align: center;
`;

const PostCard = ({ item, i }) => {
    return(
        <Link to={ `/posts/post/${item.postId}` }>
            <PostCardBlock>
                <CardImage 
                    src={ item.images[0] }
                />
                <CardTitle>
                    { item.title }
                </CardTitle>
                <CardNickname>
                    { item.nickname }
                </CardNickname>
            </PostCardBlock>
        </Link>
    );
};

export default PostCard;