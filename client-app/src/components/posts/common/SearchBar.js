import React from 'react';
import styled from 'styled-components';
import Input from '../../common/Input';

const SearchBarArea = styled.div`
    float: left;
    width: 100%;
`;

const SearchBar = () => {
    return(
        <SearchBarArea>
            <Input name="searchBar"
                   type="text"
                   placeholder="키워드를 입력해주세요"
            />
        </SearchBarArea>
    );
};

export default SearchBar;