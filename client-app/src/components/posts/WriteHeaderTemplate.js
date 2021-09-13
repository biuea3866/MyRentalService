import React from 'react';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const WriteHeaderBlock = styled.div`
    color: ${ palette.blue[2] };
    width: 60%;
    font-size: 25px;
    font-weight: bold;
    text-align: left;
`;

const WriteHeaderTemplate = () => {
    return(
        <WriteHeaderBlock>
            오늘은 어떤 물건들을<br/>
            빌려주실 건가요?
        </WriteHeaderBlock>
    );
};

export default WriteHeaderTemplate;