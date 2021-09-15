import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const ItemBlock = styled.div`
    width: 100%;
    border-bottom: 1px solid ${ palette.gray[2] };
    margin-bottom: 1rem;
    padding-bottom: 0.5rem;
`;

const Header = styled.div`
    float: left;
    width: 100%;
    display: flex;
    align-items: center;
`;

const WriterBlock = styled.div`
    font-size: 1.3rem;
    font-weight: bold;
`;

const CreatedAtBlock = styled.div`
    font-size: 0.8rem;
    margin-left: 15px;
    color: ${ palette.gray[6] }
`;

const Content = styled.div`
    float: left;
    margin-top: 1.5rem;
    margin-bottom: 0.8125rem;
`;

const CommentItem = ({item, i}) => {
    return(
        <ItemBlock>
            <Header>
                <WriterBlock>
                    { item.writer }
                </WriterBlock>
                <CreatedAtBlock>
                    { item.createdAt }
                </CreatedAtBlock>
            </Header>
            <Content>
                { item.comment }
            </Content>
        </ItemBlock>
    );
};

export default CommentItem;