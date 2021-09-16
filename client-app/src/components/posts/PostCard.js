import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';
import palette from '../../lib/styles/palettes';

const PostCardBlock = styled.div`
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
    overflow: hidden;
    text-align: left;
    padding-top: 10px;
    padding-left: 10px;
`;

const CardImage = styled.img`
    float: left;
    width: 400px;
    height: 200px;
`;

const CardNickname = styled.div`
    float: left;
    width: 300px;
    text-align: left;
    padding-left: 10px;
`;

const CardDate = styled.div`
    float: left;
    width: 90px;
    color: ${ palette.gray[6] }
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
                <CardDate>
                    { item.createdAt }
                </CardDate>
            </PostCardBlock>
        </Link>
    );
};

export default PostCard;