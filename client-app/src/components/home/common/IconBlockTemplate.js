import React from 'react';
import styled from 'styled-components';
import { Link } from 'react-router-dom';

const IconBlock = styled.div`
    float: left;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    width: 80px;
    height: 130px;
    text-align: center;
    margin-left: 20px;
    margin-right: 20px;
`;

const Area = styled.img`
    width: 80px;
    height: 80px;
`;

const Text = styled.span`
    width: 80px;
    height: 50px;
`;

const IconBlockTemplate = ({
    to,
    src,
    text
}) => {
    return(
        <Link to={ to }>
            <IconBlock>
                <Area src={ src }>
                </Area>
                <Text>
                    { text }
                </Text>
            </IconBlock>
        </Link>
    );
};

export default IconBlockTemplate;