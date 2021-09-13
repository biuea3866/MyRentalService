import React from 'react';
import { Link } from 'react-router-dom';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const HeaderBlock = styled.div`
    width: 90%;
    height: 80px;
    display: flex;
    justify-content: flex-end;
    padding: 40px;
`;

const BorderButton = styled.button`
    width: 100px;
    height: 50px;
    border-radius: 30px;
    border: 2px solid ${palette.blue[1]};
    color: ${palette.blue[1]};
    background-color: white;
    font-size: 20px;
    font-weight: bold;
`;

const PostHeader = () => {
    return(
        <HeaderBlock>
            <BorderButton>
                <Link to="/posts/write">
                    글쓰기
                </Link>
            </BorderButton>
        </HeaderBlock>
    );
};

export default PostHeader;