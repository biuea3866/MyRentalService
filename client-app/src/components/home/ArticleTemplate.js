import React from 'react';
import styled from 'styled-components';

const ArticleTemplateBlock = styled.div`
    left: 0;
    top: 0;
    bottom: 0;
    right: 0;
    padding-top: 130px;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
`;

const ArticleBox = styled.div`
    padding: 2rem;
    width: 80%;
    border-radius: 2px;
`;

const ArticleTemplate = ({ children }) => {
    return(
        <ArticleTemplateBlock>
            <ArticleBox>
                { children }
            </ArticleBox>
        </ArticleTemplateBlock>
    );
};

export default ArticleTemplate;