import React from 'react';
import styled from 'styled-components';
import SearchBar from './common/SearchBar';

const SearchBarBlock = styled.div`
    float: left;
    width: 30%;
`;

const SearchBarTemplate = () => {
    return(
        <SearchBarBlock>
            <SearchBar />
        </SearchBarBlock>
    );
};

export default SearchBarTemplate;