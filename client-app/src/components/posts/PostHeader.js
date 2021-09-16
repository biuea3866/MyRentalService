import React, { useState } from 'react';
import { Link } from 'react-router-dom';
import Select from 'react-select';
import styled from 'styled-components';
import palette from '../../lib/styles/palettes';

const HeaderBlock = styled.div`
    display: flex;
    padding: 40px;
    align-items: center;
    justify-content: center;
`;

const ButtonBlock = styled.div`
    disflay: flex;
    width: 40%;
    justify-content: flex-start;
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

const ClassificationBlock = styled.div`
    display: flex;
    width: 40%;
    justify-content: flex-end;
    align-items: center;
`;

const StyledClassification = styled(Select)`
    width: 200px;
`;

const PostHeader = () => {
    const [option, setOption] = useState('');
    const options = [
        { value: 'ALL', label: '전체' },
        { value: 'REQUEST_RENTAL', label: '요청글' },
        { value: 'READY_RENTAL', label: '대여 가능' }
    ];

    const onSelect = (value) => {
        setOption(value);
    };

    return(
        <HeaderBlock>
            <ButtonBlock>
                <BorderButton>
                    <Link to="/posts/write">
                        글쓰기
                    </Link>
                </BorderButton>
            </ButtonBlock>
            <ClassificationBlock>
                <StyledClassification onChange={ onSelect }
                                      options={ options }
                                      value={ option }
                                      placeholder="분류"
                />
            </ClassificationBlock>
        </HeaderBlock>
    );
};

export default PostHeader;