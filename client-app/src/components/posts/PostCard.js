import React from 'react';
import styled from 'styled-components';
import { Link, withRouter } from 'react-router-dom';
import palette from '../../lib/styles/palettes';
import no_image from '../../static/no-image.png';

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
    width: 240px;
    text-align: left;
    padding-left: 10px;
`;

const CardDate = styled.div`
    float: left;
    width: 140px;
    color: ${ palette.gray[6] }
`;

const PostCard = ({ item, i }) => {
    return(
        <Link to={ `/posts/post/${ item.id }` }>
            <PostCardBlock>
                <CardImage src={ 
                    item.postType === '빌려줄게요' ?
                    "data:image/png;base64," + item.images[0].filePath :
                    no_image
                } />
                <CardTitle>
                    { item.title }
                </CardTitle>
                <CardNickname>
                    { item.writer }
                </CardNickname>
                <CardDate>
                    { item.createdAt }
                </CardDate>
            </PostCardBlock>
        </Link>
    );
};

export default withRouter(PostCard);